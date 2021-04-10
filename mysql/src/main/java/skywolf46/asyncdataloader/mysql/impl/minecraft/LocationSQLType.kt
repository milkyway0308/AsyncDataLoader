package skywolf46.asyncdataloader.mysql.impl.minecraft

import org.bukkit.Location
import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType
import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult
import skywolf46.asyncdataloader.mysql.impl.DoubleSQLType
import skywolf46.asyncdataloader.mysql.impl.StringSQLType

object LocationSQLType : AbstractSQLType<Location>() {
    override fun assemble(result: SQLResult): Location {
        TODO("Not yet implemented")
    }

    override fun disassemble(insertion: SQLInsertion, data: Location) {
        TODO("Not yet implemented")
    }

    override fun getSQLRequirement(): Array<AbstractSQLType<out Any>> {
        return arrayOf(StringSQLType(60), DoubleSQLType, DoubleSQLType, DoubleSQLType)
    }

    override fun getSQLTypeString(): String {
        return ""
    }

    override fun getTypeName(): String {
        return "LX"
    }
}