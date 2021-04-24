package skywolf46.asyncdataloader.mysql.impl.constructors

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.util.SQLResult

class VectorConstructor : ISQLStructure<Vector>{
    override fun getMark(): String {
        return "VEC"
    }

    override fun construct(baseName: String, table: SQLResult): Vector {
        TODO("Not yet implemented")
    }

    override fun deconstruct(data: Vector, statement: IStatementInput) {
        TODO("Not yet implemented")
    }
}