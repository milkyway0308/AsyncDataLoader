package skywolf46.adl.abstraction.sql.utilbase

import skywolf46.adl.abstraction.sql.AbstractSQLBasedSnapshot
import skywolf46.bss.api.SQLTable
import java.sql.Connection

abstract class AbstractSQLTableBasedSnapshot(private val table: String, target: Connection) : AbstractSQLBasedSnapshot(
    target
) {
    override fun saveSnapshotAndRun(unit: () -> Unit) {
        saveSnapshot(SQLTable.of(target, table))
        unit()
    }

    abstract fun saveSnapshot(table: SQLTable)
}