package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult

class StringSQLType(val size: Int) : AbstractSQLType<String>() {
    override fun assemble(result: SQLResult): String {
        return result.string()
    }

    override fun disassemble(insertion: SQLInsertion, data: String) {
        insertion.next(data)
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return emptyArray()
    }

    override fun getSQLTypeString(): String {
        return "VARCHAR($size)"
    }

    override fun getTypeName(): String {
        return "ST"
    }
}