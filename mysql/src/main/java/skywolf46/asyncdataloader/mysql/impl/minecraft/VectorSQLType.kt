package skywolf46.asyncdataloader.mysql.impl.minecraft

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult
import skywolf46.asyncdataloader.mysql.impl.DoubleSQLType

object VectorSQLType : AbstractSQLType<Vector>() {
    override fun assemble(result: SQLResult): Vector {
        return Vector(result.double(), result.double(), result.double())
    }

    override fun disassemble(insertion: SQLInsertion, data: Vector) {
        insertion.next(data.x)
        insertion.next(data.y)
        insertion.next(data.z)
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return arrayOf(DoubleSQLType, DoubleSQLType, DoubleSQLType)
    }

    override fun getSQLTypeString(): String {
        return ""
    }

    override fun getTypeName(): String {
        return "MV3"
    }
}