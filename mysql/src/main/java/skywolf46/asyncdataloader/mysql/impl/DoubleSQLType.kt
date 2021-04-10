package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult

object DoubleSQLType : AbstractSQLType<Double>() {
    override fun assemble(result: SQLResult): Double {
        return result.double()
    }

    override fun disassemble(insertion: SQLInsertion, data: Double) {
        insertion.next(data)
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return emptyArray()
    }

    override fun getSQLTypeString(): String {
        return "DOUBLE"
    }

    override fun getTypeName(): String {
        return "D"
    }
}