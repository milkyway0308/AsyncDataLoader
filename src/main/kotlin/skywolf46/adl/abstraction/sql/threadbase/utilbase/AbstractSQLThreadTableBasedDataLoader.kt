package skywolf46.adl.abstraction.sql.threadbase.utilbase

import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.adl.abstraction.sql.utilbase.AbstractSQLTableBasedSnapshot
import skywolf46.bss.api.SQLTable
import skywolf46.bss.threads.SQLThread
import java.sql.Connection

abstract class AbstractSQLThreadTableBasedDataLoader (private val table: String) :
    AbstractDataLoader<SQLThread, AbstractSQLThreadTableBasedSnapshot>() {
    override fun loadData(data: SQLThread) {
        data.addTask {
            loadData(SQLTable.of(it, table))
        }
    }

    abstract fun loadData(table: SQLTable)

}