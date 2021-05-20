package skywolf46.asyncdataloader.mysql.impl.constructors

import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput

sealed class SQLBases<T : Any> : ISQLStructure<T> {

    class String(val size: kotlin.Int) : SQLBases<kotlin.String>() {
        override fun getMark(): kotlin.String {
            return "TX"
        }

        override fun construct(table: IStatementOutput): kotlin.String {
            return table.getString()
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

    object Byte : SQLBases<kotlin.Byte>() {
        override fun getMark(): kotlin.String {
            return "B"
        }

        override fun construct(table: IStatementOutput): kotlin.Byte {
            return table.getByte()
        }

        override fun deconstruct(data: kotlin.Byte, statement: IStatementInput) {
            statement.appendByte(data)
        }

        override fun getConstructor(): kotlin.String {
            return "TINYINT"
        }
    }

    object Short : SQLBases<kotlin.Short>() {
        override fun getMark(): kotlin.String {
            return "S"
        }

        override fun construct(table: IStatementOutput): kotlin.Short {
            return table.getShort()
        }

        override fun deconstruct(data: kotlin.Short, statement: IStatementInput) {
            statement.appendShort(data)
        }

        override fun getConstructor(): kotlin.String {
            return "SMALLINT"
        }

    }


    object Int : SQLBases<kotlin.Int>() {
        override fun getMark(): kotlin.String {
            
            return "I"
        }


        override fun construct(table: IStatementOutput): kotlin.Int {
            return table.getInt()
        }

        override fun deconstruct(data: kotlin.Int, statement: IStatementInput) {
            statement.appendInt(data)
        }

        override fun getConstructor(): kotlin.String {
            return "INT"
        }

    }

    object Float : SQLBases<kotlin.Float>() {
        override fun getMark(): kotlin.String {
            return "F"
        }

        override fun construct(table: IStatementOutput): kotlin.Float {
            return table.getFloat()
        }

        override fun deconstruct(data: kotlin.Float, statement: IStatementInput) {
            statement.appendFloat(data)
        }
    }

    object Long : SQLBases<kotlin.Long>() {
        override fun getMark(): kotlin.String {
            return "L"
        }

        override fun construct(table: IStatementOutput): kotlin.Long {
            return table.getLong()
        }

        override fun deconstruct(data: kotlin.Long, statement: IStatementInput) {
            statement.appendLong(data)
        }

        override fun getConstructor(): kotlin.String {
            return "BIGINT"
        }

    }

    object Double : SQLBases<kotlin.Double>() {
        override fun getMark(): kotlin.String {
            return "D"
        }

        override fun construct(table: IStatementOutput): kotlin.Double {
            return table.getDouble()
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
            return java.util.UUID.fromString(table.getString())
        }

        override fun deconstruct(data: java.util.UUID, statement: IStatementInput) {
            statement.appendString(data.toString())
        }

        override fun getConstructor(): kotlin.String {
            return "VARCHAR(36)"
        }
    }

    object ByteArray : SQLBases<kotlin.ByteArray>() {
        override fun getMark(): kotlin.String {
            return "BA"
        }

        override fun construct(table: IStatementOutput): kotlin.ByteArray {
            return table.getByteArray()
        }

        override fun deconstruct(data: kotlin.ByteArray, statement: IStatementInput) {
            statement.appendByteArray(data)
        }

    }
}