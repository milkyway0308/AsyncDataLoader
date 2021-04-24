package skywolf46.asyncdataloader.mysql

import org.bukkit.util.Vector
import org.junit.jupiter.api.Test
import skywolf46.asyncdataloader.initializer.MySQLLoaderInitializer
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLSelection
import skywolf46.asyncdataloader.mysql.data.SQLTableBuilder
import skywolf46.asyncdataloader.mysql.impl.UUIDSQLType
import skywolf46.asyncdataloader.mysql.impl.minecraft.VectorSQLType
import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.util.*

class Tester {

    @Test
    fun tableCreationTest() {
        MySQLLoaderInitializer().load()
        val build = SQLTableBuilder(SQLTable("TEST"))
        build
            .addColumnType("PlayerUID", UUIDSQLType) {
                isPrimary = true
            }
            .addColumnType("Loc", VectorSQLType)
        println(build.toSQLString())
    }

    @Test
    fun tableInsertionTest() {
        MySQLLoaderInitializer().load()
        SQLInsertion(SQLTable("TEST"))
            .write(UUID.randomUUID())
            .write(Vector(0.0, 5.0, 0.0))
            .toSQLString().run {
                println(this)
            }
    }

    @Test
    fun tableSelectTest() {
        MySQLLoaderInitializer().load()
        SQLSelection(SQLTable("TEST"), "test1", "test2")
            .equal("test") {
                next()
            }
            .toSQLString().run {
                println(this)
            }
    }
}