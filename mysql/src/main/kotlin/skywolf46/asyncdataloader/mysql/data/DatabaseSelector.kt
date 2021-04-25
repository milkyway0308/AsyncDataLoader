package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.sql.Connection

class DatabaseSelector(val conn: Connection, database: String) {
    init {
        conn.prepareStatement("create database if not exists $database;")?.execute()
        conn.prepareStatement("use $database;")?.execute()

    }

    fun tableOf(table: String) = SQLTable(conn, table)
}