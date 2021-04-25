package skywolf46.asyncdataloader.mysql.storage

import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import kotlin.reflect.KClass

object SQLStructureStorage {
    private val map = mutableMapOf<KClass<Any>, ISQLStructure<Any>>()

    fun register(struct: ISQLStructure<*>, vararg cls: KClass<*>) {
        for (x in cls)
            map[x as KClass<Any>] = struct.uncast()
    }

    fun get(cls: KClass<Any>): ISQLStructure<Any>? {
        return map[cls]
    }
}