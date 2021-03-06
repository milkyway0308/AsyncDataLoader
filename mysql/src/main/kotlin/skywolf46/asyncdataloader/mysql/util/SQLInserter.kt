package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.AbstractQueryable
import skywolf46.asyncdataloader.mysql.abstraction.IBatchAcceptor
import skywolf46.asyncdataloader.mysql.abstraction.IBatchController
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage

class SQLInserter(table: SQLTable) : AbstractQueryable(table) {
    private val lst = mutableListOf<Any>()

    fun with(vararg obj: Any): SQLInserter {
        for (x in obj)
            lst.add(x)
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
            }
                ?: throw IllegalStateException("SQL deserialization for ${x.javaClass.kotlin.qualifiedName} not supported")
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

    fun batchInsert(): IBatchAcceptor {
        val ns = SQLInserter(table)
        return object : IBatchController {
            private var injector: StatementInjector? = null
            override fun accept(vararg any: Any): IBatchAcceptor {
                if (injector == null) {
                    ns.with(*any)
                    injector = StatementInjector(table, "insert ${ns.getSQLString()}")
                }
                injector!!.batch(mutableListOf(*any))
                return this
            }

            override fun finalizeBatch() {
                injector?.finalizeBatch()
            }
        }
    }


    fun batchReplace(): IBatchAcceptor {
        val ns = SQLInserter(table)
        return object : IBatchController {
            private var injector: StatementInjector? = null
            override fun accept(vararg any: Any): IBatchAcceptor {
                if (injector == null) {
                    ns.with(*any)
                    injector = StatementInjector(table, "replace ${ns.getSQLString()}")
                }
                injector!!.batch(mutableListOf(*any))
                return this
            }

            override fun finalizeBatch() {
                injector?.finalizeBatch()
            }
        }
    }

}