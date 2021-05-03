package skywolf46.asyncdataloader.mysql.initializer

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.bukkit.util.Vector
import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.mysql.data.SQLAccount
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLBases
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*


class MySQLLoaderInitializer : AbstractDataLoaderInitializer() {
    companion object {
        var targetFile = File("mySQL.properties")
        lateinit var account: SQLAccount
        lateinit var pool: HikariDataSource
    }

    override fun load() {
        println("AsyncDataLoader - MySQL | Initializing...")
        println("AsyncDataLoader - MySQL | Registering Structures..")
        SQLStructureStorage.register(SQLBases.Int, Int::class)
        SQLStructureStorage.register(SQLBases.Double, Double::class)
        SQLStructureStorage.register(SQLBases.UUID, UUID::class)
        SQLStructureStorage.register(SQLMinecraft.Vector, Vector::class)
        println("AsyncDataLoader - MySQL | Loading configuration...")
        with(targetFile) {
            val property = Properties()
            property["url"] = "jdbc:mysql://localhost:3306/"
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
        val config = HikariConfig()
        config.jdbcUrl = account.url
        config.username = account.id
        config.password = account.password
        config.driverClassName = "com.mysql.jdbc.Driver"
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
        try {
            pool = HikariDataSource(config)
        } catch (e: Exception) {
            System.err.println("AsyncDataLoader - MySQL | SQL Connection failed! Check setting in file \"mysql.properties\" and retry.")
        }

    }
}