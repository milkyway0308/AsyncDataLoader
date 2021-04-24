package skywolf46.asyncdataloader.mysql.impl

import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.util.SQLResult

class SQLMinecraft {
    object Vector : ISQLStructure<org.bukkit.util.Vector> {
        override fun getMark(): String {
            return "VEC"
        }

        override fun construct(baseName: String, table: SQLResult): org.bukkit.util.Vector {
            TODO("Not yet implemented")
        }

        override fun deconstruct(data: org.bukkit.util.Vector, statement: IStatementInput) {
            TODO("Not yet implemented")
        }

        override fun requires(): List<ISQLStructure<*>> {
            return listOf(SQLBases.Double, SQLBases.Double, SQLBases.Double)
        }

    }
}