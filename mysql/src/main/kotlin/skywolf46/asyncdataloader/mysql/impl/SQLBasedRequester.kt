package skywolf46.asyncdataloader.mysql.impl

import org.bukkit.configuration.file.YamlConfiguration
import skywolf46.asyncdataloader.core.abstraction.enums.LoadState
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoadRequester
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader
import skywolf46.asyncdataloader.mysql.initializer.MySQLLoaderInitializer
import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.io.File
import java.sql.Connection

class SQLBasedRequester(private val conn: Connection) : AbstractDataLoadRequester<String, SQLTable>() {

    override fun request(param: String): SQLTable {
        return SQLTable(conn, param)
    }

    override fun trigger(data: String, main: (SQLTable) -> Unit, afterRun: (LoadState) -> Unit) {
        try {
            main(request(data))
            afterRun(LoadState.LOADED)
        } catch (e: Exception) {
            e.printStackTrace()
            afterRun(LoadState.LOAD_FAIL)
        }
    }
}

fun <T : AbstractDataLoader<*>> T.loadSQL(
    connection: Connection = MySQLLoaderInitializer.pool!!.connection,
    table: String,
    unit: SQLTable.() -> Unit,
) {
    this.loadRequest(SQLBasedRequester(connection), table, unit)
}


fun <T : AbstractDataLoader<*>> T.loadSQLAsync(
    connection: Connection = MySQLLoaderInitializer.pool!!.connection,
    table: String,
    unit: SQLTable.() -> Unit,
) {
    this.asyncLoadRequest(SQLBasedRequester(connection), table, unit)
}


fun <T : AbstractDataLoader<*>> T.loadSQL(table: String, unit: SQLTable.() -> Unit) {
    this.loadSQL(table, unit)
}


fun <T : AbstractDataLoader<*>> T.loadSQLAsync(table: String, unit: SQLTable.() -> Unit) {
    this.loadSQLAsync(table, unit)
}
