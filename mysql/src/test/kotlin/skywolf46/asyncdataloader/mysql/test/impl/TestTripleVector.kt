package skywolf46.asyncdataloader.mysql.test.impl

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.impl.SQLMinecraft
import skywolf46.asyncdataloader.mysql.util.SQLResult

class TestTripleVector : ISQLStructure<List<Vector>> {
    override fun getMark(): String {
        return "TTV"
    }

    override fun construct(baseName: String, table: SQLResult): List<Vector> {
        TODO("Not yet implemented")
    }

    override fun deconstruct(data: List<Vector>, statement: IStatementInput) {
        TODO("Not yet implemented")
    }

    override fun requires(): List<ISQLStructure<*>> {
        return listOf(SQLMinecraft.Vector, SQLMinecraft.Vector, SQLMinecraft.Vector)
    }
}