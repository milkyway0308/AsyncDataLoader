package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import java.sql.ResultSet

data class SQLResult(val result: ResultSet, var position: Int = 1, var columnPosition: Int = 0) {
    fun nextRow(): Boolean {
        if (!result.next())
            return false
        position++
        columnPosition = 0
        return true
    }

    fun <X : Any> parse(type: AbstractSQLType<X>): X? {
        return type.assemble(this)
    }

    fun boolean() = result.getBoolean(++columnPosition)
    fun byte() = result.getByte(++columnPosition)
    fun short() = result.getShort(++columnPosition)
    fun int() = result.getInt(++columnPosition)
    fun long() = result.getLong(++columnPosition)
    fun double() = result.getDouble(++columnPosition)
    fun float() = result.getFloat(++columnPosition)
    fun string() = result.getString(++columnPosition)
    fun NString() = result.getNString(++columnPosition)
    fun array() = result.getArray(++columnPosition)
    fun byteArray() = result.getBytes(++columnPosition)
    fun xml() = result.getSQLXML(++columnPosition)
    fun blob() = result.getBlob(++columnPosition)
    fun bigDemical() = result.getBigDecimal(++columnPosition)
    fun date() = result.getDate(++columnPosition)
    fun clob() = result.getClob(++columnPosition)
    fun ref() = result.getRef(++columnPosition)
    fun time() = result.getTime(++columnPosition)
    fun customObject() = result.getObject(++columnPosition)
    fun <T> customObject(cls: Class<T>) = result.getObject(++columnPosition, cls)
    fun timestamp() = result.getTimestamp(++columnPosition)
    fun url() = result.getURL(++columnPosition)


}