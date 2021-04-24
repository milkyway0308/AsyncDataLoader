package skywolf46.asyncdataloader.mysql.abstraction

import java.sql.PreparedStatement

interface ISQLStructure<T : Any> {

    fun getMark(): String

    fun deconstruct(baseName: String, data: T): List<Any>

    // TODO change from selection
    fun construct(baseName: String, statement: PreparedStatement)

    fun requires(): List<AbstractSQLStructure<Any>> = listOf()
}