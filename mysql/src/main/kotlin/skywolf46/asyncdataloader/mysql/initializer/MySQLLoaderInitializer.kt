package skywolf46.asyncdataloader.mysql.initializer

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLBases
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import java.util.*


class MySQLLoaderInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        SQLStructureStorage.register(SQLBases.Int, Int::class)
        SQLStructureStorage.register(SQLBases.Double, Double::class)
        SQLStructureStorage.register(SQLBases.UUID, UUID::class)
        SQLStructureStorage.register(SQLMinecraft.Vector, Vector::class)
    }
}