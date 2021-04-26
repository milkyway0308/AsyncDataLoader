package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.AbstractQueryable
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import java.sql.Connection

class SQLTable(val connection: Connection?, val tableName: String) {

    @Deprecated("This constructor only allowed for testing")
    internal constructor(str: String) : this(null, str)


    //    fun prepare(sql: ISQLConvertible) = connection?.prepareStatement(sql.toSQLString())
    fun construct(): ConnectedConstructor {
        return ConnectedConstructor(this)
    }

    fun insert(): SQLInserter {
        return SQLInserter(this)
    }

    fun select(): SQLSelector {
        return SQLSelector(this)
    }

    fun insertRow(vararg args: Any) {
        insert().with(*args).insert()
    }
    
    fun replaceRow(vararg args: Any) {
        insert().with(*args).replace()
    }

    class ConnectedConstructor(table: SQLTable) : AbstractQueryable(table) {
        private val lst = mutableListOf<ColumnData>()
        fun with(name: String, type: ISQLStructure<*>, unit: ColumnData.() -> Unit = {}): ConnectedConstructor {
            val data = ColumnData(name, type)
            unit(data)
            lst += data
            return this
        }

        fun create(): SQLTable {
            table.connection!!.prepareStatement(getSQLString()).execute()
            return table
        }


        override fun getSQLString(): String {
            val sb = StringBuilder("create table if not exists ${table.tableName} (")
            for (x in lst) {
                sb.append(x).append(", ")
            }
            sb.delete(sb.length - 2, sb.length)
            sb.append(");")
            return sb.toString()
        }

        override fun getSQLObjects(): List<Any> {
            return emptyList()
        }
    }

    class ColumnData(val name: String, val type: ISQLStructure<*>) {
        var primary = false
        var nullable = false
        override fun toString(): String {
            val sb = StringBuilder()
            if (primary)
                sb.append(" primary key")
            if (!nullable)
                sb.append(" not null")
            return type.toSQLTableString(name, sb.toString().trim()).first
        }
    }
}
