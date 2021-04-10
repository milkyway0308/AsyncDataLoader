package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult

object IntSQLType : AbstractSQLType<Int>() {
    override fun assemble(result: SQLResult): Int {
        return result.int()
    }

    override fun disassemble(insertion: SQLInsertion, data: Int) {
       insertion.next(data)
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return emptyArray()
    }

    override fun getSQLTypeString(): String {
        return "INT"
    }

    override fun getTypeName(): String {
        return "I"
    }
}