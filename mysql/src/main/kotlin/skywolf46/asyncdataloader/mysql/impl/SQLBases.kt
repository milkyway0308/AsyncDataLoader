package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLStructure

sealed class SQLBases<T : Any>(vararg selector : kotlin.String) : AbstractSQLStructure<T>(*selector) {

    object String : SQLBases<kotlin.String>() {
        override fun getMark(): kotlin.String {
            TODO("Not yet implemented")
        }

        override fun deconstruct(baseName: kotlin.String, data: kotlin.String): List<Any> {
            TODO("Not yet implemented")
        }

        override fun construct(baseName: kotlin.String, S: ???): Any {
            TODO("Not yet implemented")
        }

        override fun requires(): List<AbstractSQLStructure<Any>> {
            TODO("Not yet implemented")
        }

    }
}