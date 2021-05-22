package skywolf46.asyncdataloader.mysql.initializer

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.mysql.data.DatabaseSelector
import skywolf46.asyncdataloader.mysql.data.SQLAccount
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLBases
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.sql.Connection
import java.util.*
import kotlin.collections.HashSet


class MySQLLoaderInitializer : AbstractDataLoaderInitializer() {
    companion object {
        var targetFile = File("mySQL.properties")
        internal lateinit var account: SQLAccount
        var pool: HikariDataSource? = null
        private val initialized = mutableMapOf<Connection, DatabaseSelector>()
        fun isInitialized(): Boolean {
            return pool != null
        }

        fun connection(): DatabaseSelector {
            val conn = pool!!.connection
            return initialized.computeIfAbsent(conn) {
                return@computeIfAbsent DatabaseSelector(conn, "AsyncDataLoader")
            }
        }
    }

    override fun load() {
        println("AsyncDataLoader - MySQL | Initializing...")
        println("AsyncDataLoader - MySQL | Registering Structures..")
        SQLStructureStorage.register(SQLBases.Int, Int::class)
        SQLStructureStorage.register(SQLBases.String(10000), String::class)
        SQLStructureStorage.register(SQLBases.Double, Double::class)
        SQLStructureStorage.register(SQLBases.UUID, UUID::class)
        try {
            Class.forName("org.bukkit.Bukkit")
            SQLStructureStorage.register(SQLMinecraft.Vector, Vector::class)
            SQLStructureStorage.register(SQLMinecraft.Location, Location::class)
            SQLStructureStorage.register(SQLMinecraft.ItemStack, ItemStack::class)
        } catch (_: Throwable) {
            // Ignored exception
        }
        println("AsyncDataLoader - MySQL | Loading configuration...")
        with(targetFile) {
            val property = Properties()
            property["url"] = "jdbc:mysql://localhost:3306/?useSSL=false"
            property["user"] = "root"
            property["password"] = "1111"
            if (!exists()) {
                if (parentFile != null)
                    parentFile.mkdirs()
                createNewFile()
                property.store(FileOutputStream(this), "MySQL - JDBC")
            }
            property.load(FileInputStream(this))
            account =
                SQLAccount(property["url"].toString(), property["user"].toString(), property["password"].toString())
        }
        println("AsyncDataLoader - MySQL | Creating SQL thread pool...")
        try {
            val config = HikariConfig()
            config.jdbcUrl = account.url
            config.username = account.id
            config.password = account.password
            config.driverClassName = "com.mysql.jdbc.Driver"
            config.addDataSourceProperty("cachePrepStmts", "true")
            config.addDataSourceProperty("prepStmtCacheSize", "250")
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
            config.addDataSourceProperty("useServerPrepStmts", "true")
            config.addDataSourceProperty("useLocalSessionState", "true")
            config.addDataSourceProperty("rewriteBatchedStatements", "true")
            config.addDataSourceProperty("cacheResultSetMetadata", "true")
            config.addDataSourceProperty("cacheServerConfiguration", "true")
            config.addDataSourceProperty("elideSetAutoCommits", "true")
            config.addDataSourceProperty("maintainTimeStats", "false")
            config.addDataSourceProperty("server.ssl.enabled", "false")
            pool = HikariDataSource(config)
            println("AsyncDataLoader - MySQL | SQL connected sucessfully.")
        } catch (e: Exception) {
            System.err.println("AsyncDataLoader - MySQL | SQL Connection failed! Check setting in file \"mysql.properties\" and retry.")
            e.printStackTrace()
        }

    }
}