package skywolf46.adl.abstraction.sql.utilbase

import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.bss.api.SQLTable
import java.sql.Connection

abstract class AbstractSQLTableBasedLoader(private val table: String) :
    AbstractDataLoader<Connection, AbstractSQLTableBasedSnapshot>() {
    override fun loadData(data: Connection) {
        loadData(SQLTable.of(data, table))
    }

    abstract fun loadData(table: SQLTable)

}