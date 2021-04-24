package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure

// All value to private, cannot be modified
class SQLWaiter(private val table: SQLTable, private val sql: String, private val objes: List<Pair<Any, ISQLStructure<Any>>>) {
    fun query(): SQLResult {
        // Exception when null
        val st = table.connection!!.prepareStatement(sql)
        for (x in objes) {
            x.second.construct()
        }
    }

    // No additionqal return on inject
    fun inject() {

    }
}