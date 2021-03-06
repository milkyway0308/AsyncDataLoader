package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.*
import skywolf46.asyncdataloader.mysql.initializer.MySQLLoaderInitializer
import java.sql.Connection

class SQLTable(val tableName: String, val connection: Connection? = MySQLLoaderInitializer.connection().conn) {

    @Deprecated("This constructor only allowed for testing")
    internal constructor(str: String) : this(str, null)

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

    fun select(unit: SQLSelector.() -> Unit): SQLSelector {
        val sel = select()
        unit(sel)
        return sel
    }

    fun delete(): SQLDeleter {
        return SQLDeleter(this)
    }

    fun delete(unit: SQLDeleter.() -> Unit): SQLDeleter {
        val del = delete()
        unit(del)
        return del
    }

    fun insertRow(vararg args: Any) {
        insert().with(*args).insert()
    }

    fun replaceRow(vararg args: Any) {
        insert().with(*args).replace()
    }

    fun deleteRow(vararg arg: Pair<String, Any>, unit: () -> Unit = {}) {
        delete().run {
            for ((x, y) in arg)
                compareWith(x, y)
            delete(unit)
        }
    }

    fun deleteBatch(unit: ISQLCompare.() -> Unit) {
        val batch = delete().batch()
        unit(batch)
        (batch as IBatchController).finalizeBatch()
    }

    fun insertBatch(unit: IBatchAcceptor.() -> Unit) {
        val batch = insert().batchInsert()
        unit(batch)
        (batch as IBatchController).finalizeBatch()
    }


    fun replaceBatch(unit: IBatchAcceptor.() -> Unit) {
        val batch = insert().batchReplace()
        unit(batch)
        (batch as IBatchController).finalizeBatch()
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
