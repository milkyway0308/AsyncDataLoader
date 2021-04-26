package skywolf46.asyncdataloader.mysql.impl.constructors

import org.bukkit.Location
import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput

class SQLMinecraft {
    object Location : ISQLStructure<org.bukkit.Location> {
        override fun getMark(): String {
            TODO("Not yet implemented")
        }

        override fun construct(table: IStatementOutput): org.bukkit.Location {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: org.bukkit.Location, statement: IStatementInput) {
            TODO("Not yet implemented")
        }

    }
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