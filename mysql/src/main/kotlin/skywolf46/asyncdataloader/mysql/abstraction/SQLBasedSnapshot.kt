package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataSnapshot
import skywolf46.asyncdataloader.mysql.initializer.MySQLLoaderInitializer
import skywolf46.asyncdataloader.mysql.util.SQLTable

abstract class SQLBasedSnapshot() : AbstractDataSnapshot() {
    fun sql(table: String, unit: SQLTable.() -> Unit) {
        unit(SQLTable(MySQLLoaderInitializer.pool.connection, table))
    }
}