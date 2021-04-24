package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractQueryable
import skywolf46.asyncdataloader.mysql.abstraction.ISQLConvertible
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.util.SQLTable

class SQLSelector(table: SQLTable) : AbstractQueryable(table) {
    private val sqlList: MutableList<ISQLConvertible> = mutableListOf()
    private val sqlString = mutableListOf<ISQLConvertible>()


    override fun getSQLString(): String {

    }

    override fun getSQLObjects(): List<Any> {
        TODO("Not yet implemented")
    }

    fun finalizeSQL(vararg selector: Pair<String, ISQLStructure<out Any>>) {
        // TODO Finalize SQL!
        // after invoke, return callable query

        // Make to string

        val lastSQL = getSQLString()

    }

}