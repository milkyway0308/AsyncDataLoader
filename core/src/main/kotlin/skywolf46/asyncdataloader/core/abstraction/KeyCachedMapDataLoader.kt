package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class KeyCachedMapDataLoader<K, V> : AbstractDataLoader<KeyCachedMapDataLoader<K, V>>() {
    private val data = mutableMapOf<K, V>()
    private val modified = mutableSetOf<K>()

    operator fun set(k: K, v: V) {
        modified += k
        data[k] = v
        reserveSave()
    }

    fun remove(k: K) {
        modified += k
    }

    operator fun get(k: K) = data[k]

    fun keyset() = data.keys

    fun values() = data.values

    fun getModified(clearCache: Boolean = true): HashSet<K> {
        val map = HashSet(modified)
        if (clearCache) {
            modified.clear()
        }
        return map
    }

    fun getModifiedContents(clearCache: Boolean = true): Map<K, V?> {
        val map = mutableMapOf<K, V?>()
        for (x in getModified())
            map[x] = data[x]
        return map
    }
}