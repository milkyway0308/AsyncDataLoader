package skywolf46.asyncdataloader.mysql.impl.constructors

import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.util.SQLResult
import java.util.*

sealed class SQLBases<T : Any> : ISQLStructure<T> {

    class String(val size: Int) : SQLBases<kotlin.String>() {
        override fun getMark(): kotlin.String {
            return "S"
        }

        override fun construct(table: IStatementOutput): kotlin.String {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: kotlin.String, statement: IStatementInput) {
            statement.appendString(data)
        }

        override fun getConstructor(): kotlin.String {
            return "VARCHAR($size)"
        }

        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): kotlin.Int {
            return System.identityHashCode(this)
        }

    }


    object Int : SQLBases<kotlin.Int>() {
        override fun getMark(): kotlin.String {
            TODO("Not yet implemented")
        }


        override fun construct(table: IStatementOutput): kotlin.Int {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: kotlin.Int, statement: IStatementInput) {
            statement.appendInt(data)
        }

        override fun getConstructor(): kotlin.String {
            return "INT"
        }

    }

    object Double : SQLBases<kotlin.Double>() {
        override fun getMark(): kotlin.String {
            return "D"
        }

        override fun construct(table: IStatementOutput): kotlin.Double {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: kotlin.Double, statement: IStatementInput) {
            statement.append(data)
        }

        override fun getConstructor(): kotlin.String {
            return "DOUBLE"
        }

    }

    object UUID : SQLBases<java.util.UUID>() {
        override fun getMark(): kotlin.String {
            return "UID"
        }

        override fun construct(table: IStatementOutput): java.util.UUID {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: java.util.UUID, statement: IStatementInput) {
            statement.appendString(data.toString())
        }

        override fun getConstructor(): kotlin.String {
            return "VARCHAR(36)"
        }

    }
}