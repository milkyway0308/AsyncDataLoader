package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractQueryable
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import skywolf46.asyncdataloader.mysql.util.SQLTable
import skywolf46.asyncdataloader.mysql.util.StatementInjector

class SQLInserter(table: SQLTable) : AbstractQueryable(table) {
    private val lst = mutableListOf<Any>()
    fun with(obj: Any): SQLInserter {
        lst += obj
        return this
    }

    override fun getSQLString(): String {
        val sb = StringBuilder("into ${table.tableName} values (")
        for (x in lst) {
            val structure = SQLStructureStorage.get(x.javaClass.kotlin)
            structure?.let {
                for (i in 0 until structure.count()) {
                    sb.append("?, ")
                }
            } ?: throw IllegalStateException("SQL deserialization for ${x.javaClass.name} not supported")
        }
        sb.delete(sb.length - 2, sb.length)
        sb.append(");")
        return sb.toString()
    }

    override fun getSQLObjects(): List<Any> {
        return lst
    }

    fun insert(): StatementInjector {
        val injector = StatementInjector(table, "insert ${getSQLString()}")
        injector.asStatementInput().execute(lst)
        return injector
    }


    fun replace(): StatementInjector {
        val injector = StatementInjector(table, "replace ${getSQLString()}")
        injector.asStatementInput().execute(lst)
        return injector
    }
}