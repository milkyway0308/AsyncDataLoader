package skywolf46.asyncdataloader.mysql.test.impl

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft

class TestTripleVector : ISQLStructure<List<Vector>> {
    override fun getMark(): String {
        return "TTV"
    }


    override fun deconstruct(data: List<Vector>, statement: IStatementInput) {
        TODO("Not yet implemented")
    }

    override fun requires(): List<ISQLStructure<*>> {
        return listOf(SQLMinecraft.Vector, SQLMinecraft.Vector, SQLMinecraft.Vector)
    }

    override fun construct(table: IStatementOutput): List<Vector> {
        TODO("Not yet implemented")
    }
}