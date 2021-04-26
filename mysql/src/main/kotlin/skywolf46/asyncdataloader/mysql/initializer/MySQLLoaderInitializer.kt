package skywolf46.asyncdataloader.mysql.initializer

import org.bukkit.util.Vector
import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLBases
import skywolf46.asyncdataloader.mysql.impl.constructors.SQLMinecraft
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import java.util.*


class MySQLLoaderInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        println("AsyncDataLoader - MySQL | Initializing...")
        println("AsyncDataLoader - MySQL | Registering Structures..")
        SQLStructureStorage.register(SQLBases.Int, Int::class)
        SQLStructureStorage.register(SQLBases.Double, Double::class)
        SQLStructureStorage.register(SQLBases.UUID, UUID::class)
        SQLStructureStorage.register(SQLMinecraft.Vector, Vector::class)
        println("AsyncDataLoader - MySQL | Creating SQL thread pool...")

        try {

        } catch (e: Exception) {
            System.err.println("AsyncDataLoader - MySQL | SQL Connection failed! Check setting in file \"mysql.properties\" and retry.")
        }

    }
}