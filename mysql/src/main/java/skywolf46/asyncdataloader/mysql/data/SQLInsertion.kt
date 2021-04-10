package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLPrepare
import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.abstraction.ISQLConvertible
import skywolf46.asyncdataloader.mysql.util.SQLTable

class SQLInsertion(table: SQLTable) : AbstractSQLPrepare(table), ISQLConvertible {
    fun <X : Any> write(data: X): SQLInsertion {
        AbstractSQLType.get(data::class.java)?.let {
            val type = (it as AbstractSQLType<X>)
            type.disassemble(this@SQLInsertion, data)
        } ?: throw IllegalStateException("Failed to parse class ${data::class.java.name} : SQL Type not registered")
        return this
    }


    override fun toSQLString(): String {
        val builder = StringBuilder("INSERT INTO ${table.tableName} VALUES (")
        for (x in lst) {
            builder.append("?, ")
        }
        for (i in 0..1)
            builder.deleteCharAt(builder.length - 1)
        builder.append(");")
        return builder.toString()
    }


    override fun flush(): SQLTable {

        return table
    }
}