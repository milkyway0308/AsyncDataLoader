package skywolf46.adl.abstraction.sql.threadbase.utilbase

import skywolf46.adl.abstraction.AbstractDataSnapshot
import skywolf46.bss.api.SQLTable
import skywolf46.bss.threads.SQLThread

abstract class AbstractSQLThreadTableBasedSnapshot(val table: String, target: SQLThread) :
    AbstractDataSnapshot<SQLThread>(target) {
    final override fun saveSnapshotAndRun(unit: () -> Unit) {
        target.addTask {
            saveSnapshot(SQLTable.of(it, table))
            unit()
        }
    }

    abstract fun saveSnapshot(table: SQLTable)
}