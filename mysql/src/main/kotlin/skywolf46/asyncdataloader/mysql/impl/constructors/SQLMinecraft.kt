package skywolf46.asyncdataloader.mysql.impl.constructors

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput

class SQLMinecraft {
    object Vector : ISQLStructure<org.bukkit.util.Vector> {
        override fun getMark(): String {
            return "VEC"
        }

        override fun construct(table: IStatementOutput): org.bukkit.util.Vector {
            return Vector(table.getDouble(), table.getDouble(), table.getDouble())
        }

        override fun deconstruct(data: org.bukkit.util.Vector, statement: IStatementInput) {
            statement.appendDouble(data.x)
            statement.appendDouble(data.y)
            statement.appendDouble(data.z)
        }

        override fun requires(): List<ISQLStructure<*>> {
            return listOf(SQLBases.Double, SQLBases.Double, SQLBases.Double)
        }
    }
}