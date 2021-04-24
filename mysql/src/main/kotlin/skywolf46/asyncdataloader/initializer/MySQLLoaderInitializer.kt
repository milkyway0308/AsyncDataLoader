package skywolf46.asyncdataloader.initializer

import org.bukkit.Location
import org.bukkit.util.Vector
import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.mysql.impl.DoubleSQLType
import skywolf46.asyncdataloader.mysql.impl.FloatSQLType
import skywolf46.asyncdataloader.mysql.impl.IntSQLType
import skywolf46.asyncdataloader.mysql.impl.UUIDSQLType
import skywolf46.asyncdataloader.mysql.impl.minecraft.LocationSQLType
import skywolf46.asyncdataloader.mysql.impl.minecraft.VectorSQLType
import java.util.*

class MySQLLoaderInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        LocationSQLType.register(Location::class.java)
        VectorSQLType.register(Vector::class.java)

        DoubleSQLType.register(Double::class.java, Double::class.javaPrimitiveType)
        IntSQLType.register(Int::class.java, Int::class.javaPrimitiveType)
        FloatSQLType.register(Float::class.java, Float::class.javaPrimitiveType)
        UUIDSQLType.register(UUID::class.java)

    }
}