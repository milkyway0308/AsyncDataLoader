package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult

object FloatSQLType : AbstractSQLType<Float>(){
    override fun assemble(result: SQLResult): Float {
        return result.float()
    }

    override fun disassemble(insertion: SQLInsertion, data: Float) {
        insertion.next(data)
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return emptyArray()
    }

    override fun getSQLTypeString(): String {
        return "FLOAT"
    }

    override fun getTypeName(): String {
        return ""
    }
}