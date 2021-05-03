package skywolf46.asyncdataloader.mysql.impl.constructors

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.util.BukkitUtil

class SQLMinecraft {
    object Location : ISQLStructure<org.bukkit.Location> {
        override fun getMark(): String {
            return "LOC"
        }

        override fun construct(table: IStatementOutput): org.bukkit.Location {
            return Location(Bukkit.getWorld(table.getString()), table.getDouble(), table.getDouble(), table.getDouble())
        }

        override fun deconstruct(data: org.bukkit.Location, statement: IStatementInput) {
            statement.appendString(data.world.name)
            statement.appendDouble(data.x)
            statement.appendDouble(data.y)
            statement.appendDouble(data.z)
        }
    }

    object ItemStack : ISQLStructure<org.bukkit.inventory.ItemStack> {
        override fun getMark(): String {
            return "ITM"
        }

        override fun construct(table: IStatementOutput): org.bukkit.inventory.ItemStack {
            return BukkitUtil.fromByte(table.getByteArray())!!
        }

        override fun deconstruct(data: org.bukkit.inventory.ItemStack, statement: IStatementInput) {
            statement.appendByteArray(BukkitUtil.toByte(data)!!)
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