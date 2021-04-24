package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.ISQLConvertible

class PlainTextSQLHolder(private val sql: String) : ISQLConvertible {
    override fun getSQLString(): String {
        return sql
    }

    override fun getSQLObjects(): List<Any> {
        return mutableListOf()
    }
}