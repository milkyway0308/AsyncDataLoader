package skywolf46.asyncdataloader.mysql

import org.bukkit.util.Vector
import org.junit.jupiter.api.Test
import skywolf46.asyncdataloader.mysql.data.DatabaseSelector
import skywolf46.asyncdataloader.mysql.util.SQLSelector
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLBases
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft
import skywolf46.asyncdataloader.mysql.initializer.MySQLLoaderInitializer
import skywolf46.asyncdataloader.mysql.test.impl.TestTripleVector
import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.sql.DriverManager
import java.util.*

class Tester {


//    @Test
    fun compareEqualStringTest() {
        println(TestTripleVector().toSQLEqualString("test"))
    }

//    @Test
    fun fullSelectTest() {
        MySQLLoaderInitializer().load()
        val sql = SQLSelector(SQLTable("test"))
            .selectAt("testVector", SQLMinecraft.Vector)
            .compareWith("vec", Vector(3, 3, 3))
        println(sql.getSQLString())
    }

//    @Test
    fun counterTest() {
        println(TestTripleVector().count())
    }

//    @Test
    fun tableTest() {
        val table = SQLTable("test5")
            .construct()
            .with("playerUID", SQLBases.UUID) {
                primary = true
            }
            .with("location", SQLMinecraft.Vector)

        println(table.getSQLString())
    }

//    @Test
    fun injectNow() {
        MySQLLoaderInitializer().load()
        Class.forName("com.mysql.jdbc.Driver")
        val con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1111")
        val db = DatabaseSelector(con, "testDB")
        val table = db.tableOf("tester")
        table.construct()
            .with("playerUID", SQLBases.UUID) {
                primary = true
            }
            .with("location", SQLMinecraft.Vector)
            .create()
    }

    val testUUID = UUID.fromString("8dccd76d-19aa-4f2d-919d-419f15aa3159")
    val testVector = Vector(3, 3, 3)

//    @Test
    fun addValue() {
        MySQLLoaderInitializer().load()
        Class.forName("com.mysql.jdbc.Driver")
        val con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1111")
        val db = DatabaseSelector(con, "testDB")
        val table = db.tableOf("testLevel")
        table.construct()
            .with("playerUID", SQLBases.UUID) {
                primary = true
            }
            .with("level", SQLBases.Int)
            .with("xp", SQLBases.Double)
            .create()
        table.replaceRow(testUUID, 80, 45)
    }

//    @Test
    fun getValue() {
        MySQLLoaderInitializer().load()
        Class.forName("com.mysql.jdbc.Driver")
        val con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1111")
        val db = DatabaseSelector(con, "testDB")
        val table = db.tableOf("tester")
        table.construct()
            .with("playerUID", SQLBases.UUID) {
                primary = true
            }
            .with("location", SQLMinecraft.Vector)
            .create()
        table.select()
            .compareWith("playerUID", testUUID)
            .selectOne("location", SQLMinecraft.Vector) {
                println("Selected $this")
            }
    }


    fun compressionGetTest() {
        MySQLLoaderInitializer().load()
        Class.forName("com.mysql.jdbc.Driver")
        val con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "1111")
        val db = DatabaseSelector(con, "testDB")
        val table = db.tableOf("tester")
        table.construct()
            .with("playerUID", SQLBases.UUID) {
                primary = true
            }
            .create()
    }

}