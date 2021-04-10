package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult
import java.util.*

object UUIDSQLType : AbstractSQLType<UUID>() {
    override fun assemble(result: SQLResult): UUID {
        return UUID.fromString(result.string())
    }

    override fun disassemble(insertion: SQLInsertion, data: UUID) {
        insertion.next(data.toString())
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return emptyArray()
    }

    override fun getSQLTypeString(): String {
        return "VARCHAR(36)"
    }

    override fun getTypeName(): String {
        return "UID"
    }
}