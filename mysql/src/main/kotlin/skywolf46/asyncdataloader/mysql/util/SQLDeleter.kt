package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.*
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage

class SQLDeleter(table: SQLTable) : AbstractQueryable(table) {
    private val sqlCompare: MutableList<Triple<ISQLStructure<Any>, String, Any>> = mutableListOf()
    private var querySize = 0


    @Suppress("DuplicatedCode")
    override fun getSQLString(): String {
        val sb = StringBuilder("delete from ${table.tableName} where")
        for (x in sqlCompare) {
            sb.append("${x.first.toSQLEqualString(x.second).first}, ")
        }
        sb.delete(sb.length - 2, sb.length)
        sb.append(";")
        return sb.toString()
    }

    override fun getSQLObjects(): List<Any> {
        return sqlCompare.map { x -> x.third }
    }

    fun compareWith(target: String, data: Any): SQLDeleter {
        val structure = SQLStructureStorage.get(data.javaClass.kotlin)
        structure?.let {
            sqlCompare.add(Triple(structure, target, data))
        } ?: throw IllegalStateException("SQL deserialization for ${data.javaClass.name} not supported")
        return this
    }


    fun delete(unit: () -> Unit = {}): StatementInjector {
        val inject = StatementInjector(table, getSQLString())
        inject.asStatementInput().execute(getSQLObjects().toMutableList(), unit)
        return inject
    }

    fun batch(): ISQLController {
        val ns = SQLDeleter(table)
        return object : ISQLController {
            private var injector: StatementInjector? = null

            override fun accept(vararg pair: Pair<String, Any>): ISQLCompare {
                if (injector == null) {
                    for ((x, y) in pair)
                        ns.compareWith(x, y)
                    injector = StatementInjector(table, ns.getSQLString())
                }
                injector!!.batch(mutableListOf(*pair.map { (_, y) -> y }.toTypedArray()))
                return this
            }

            override fun finalizeBatch() {
                injector?.finalizeBatch()
            }

        }
    }


}