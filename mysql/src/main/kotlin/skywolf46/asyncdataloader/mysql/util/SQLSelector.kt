package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.AbstractQueryable
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage

class SQLSelector(table: SQLTable) : AbstractQueryable(table) {
    private val sqlSelect: MutableList<Pair<ISQLStructure<Any>, String>> = mutableListOf()
    private val sqlCompare: MutableList<Triple<ISQLStructure<Any>, String, Any>> = mutableListOf()
    private var querySize = 0


    override fun getSQLString(): String {
        // ...Our query history start from here
        val sb = StringBuilder("select ")
        if (sqlSelect.size <= 0) {
            throw IllegalStateException("Illegal selection: No element selected")
        }
        for (x in sqlSelect) {
            sb.append("${x.first.toSQLSelectString(x.second).first}, ")
        }
        sb.delete(sb.length - 2, sb.length)
        sb.append(" from ${table.tableName}")
        if (sqlCompare.size != 0) {
            sb.append(" where ")
            for (x in sqlCompare) {
                sb.append("${x.first.toSQLEqualString(x.second).first}, ")
            }
            sb.delete(sb.length - 2, sb.length)
            sb.append(";")
        }
        return sb.toString()
    }

    override fun getSQLObjects(): List<Any> {
        return sqlCompare.map { x -> x.third }
    }

    fun compareWith(target: String, data: Any): SQLSelector {
        val structure = SQLStructureStorage.get(data.javaClass.kotlin)
        structure?.let {
            sqlCompare.add(Triple(structure, target, data))
        } ?: throw IllegalStateException("SQL deserialization for ${data.javaClass.name} not supported")
        return this
    }

    fun selectAt(target: String, structure: ISQLStructure<*>): SQLSelector {
        sqlSelect.add(structure.uncast() to target)
        return this
    }

    fun <T : Any> selectOne(target: Pair<String, ISQLStructure<T>>, unit: T?.() -> Unit): StatementInjector {
        sqlSelect.clear()
        selectAt(target.first, target.second)
        val inject = StatementInjector(table, getSQLString())
        inject.asStatementInput().executeQuery(getSQLObjects().toMutableList()) {
            unit(if (toResultInjector().hasValue) get(target.second)!! else null)
        }
        return inject
    }


    fun <T : Any> selectAll(target: Pair<String, ISQLStructure<T>>, unit: List<T>?.() -> Unit): StatementInjector {
        sqlSelect.clear()
        selectAt(target.first, target.second)
        val inject = StatementInjector(table, getSQLString())
        inject.asStatementInput().executeQuery(getSQLObjects().toMutableList()) {
            val lst = mutableListOf<T>()
            while (toResultInjector().next()) {
                lst.add(get(target.second)!!)
            }
            unit(lst)
        }
        return inject
    }


    fun <T : Any, X : Any> selectOneKey(
        key: Pair<String, ISQLStructure<T>>,
        value: Pair<String, ISQLStructure<X>>,
        unit: Pair<T, X>?.() -> Unit,
    ): StatementInjector {
        sqlSelect.clear()
        selectAt(key.first, key.second)
        selectAt(value.first, value.second)
        val inject = StatementInjector(table, getSQLString())
        inject.asStatementInput().executeQuery(getSQLObjects().toMutableList()) {
            unit(if (toResultInjector().hasValue) get(key.second)!! to get(value.second)!! else null)
        }
        return inject
    }


    fun <T : Any, X : Any> selectAllKey(
        key: Pair<String, ISQLStructure<T>>,
        value: Pair<String, ISQLStructure<X>>,
        unit: List<Pair<T, X>>?.() -> Unit,
    ): StatementInjector {
        sqlSelect.clear()
        selectAt(key.first, key.second)
        selectAt(value.first, value.second)
        val inject = StatementInjector(table, getSQLString())
        inject.asStatementInput().executeQuery(getSQLObjects().toMutableList()) {
            val lst = mutableListOf<Pair<T, X>>()
            while (toResultInjector().next()) {
                lst.add(get(key.second)!! to get(value.second)!!)
            }
            unit(lst)
        }
        return inject
    }

}