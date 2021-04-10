package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.abstraction.ISQLConvertible
import skywolf46.asyncdataloader.mysql.util.SQLTable

class SQLTableBuilder(val table: SQLTable, var pointer: Int = 0) : ISQLConvertible {
    private val targetList = mutableListOf<SQLObjectiveDescription<out Any>>()


    fun <X : Any> addColumn(
        name: String,
        data: Class<X>,
        variables: SQLObjectiveDescription<out X>.() -> Unit = {},
    ): SQLTableBuilder {
        val type = AbstractSQLType.get(data)
        if (type != null) {
            targetList += SQLObjectiveDescription(name, type).apply(variables)
            return this
        }
        throw IllegalStateException("Cannot parse class ${data.simpleName} : Not registered as SQL constructor")
    }


    fun <X : Any> addColumn(
        name: String,
        data: X,
        variables: SQLObjectiveDescription<out X>.() -> Unit = {},
    ): SQLTableBuilder {
        return addColumn(name, data::class.java, variables)
    }

    fun <X : Any> addColumnType(
        name: String,
        data: AbstractSQLType<X>,
        variables: SQLObjectiveDescription<out X>.() -> Unit = {},
    ): SQLTableBuilder {
        targetList += SQLObjectiveDescription<X>(name, data).apply(variables)
        return this
    }

    override fun toSQLString(): String {
        val sb = StringBuilder("CREATE TABLE IF NOT EXISTS ${table.tableName} (")
        for (x in targetList) {
            sb.append(x.target.toSQLTypeString(x.name))
            if(!x.isNullable)
                sb.append(" NOT NULL")
            if (x.isPrimary)
                sb.append(" PRIMARY KEY")
            if(x.default != null)
                sb.append(" DEFAULT ?")
            sb.append(", ")
        }
        sb.deleteCharAt(sb.length - 1)
        sb.deleteCharAt(sb.length - 1)
        sb.append(");")
        return sb.toString()
    }

    fun flush(): SQLTable {

        return table
    }
}