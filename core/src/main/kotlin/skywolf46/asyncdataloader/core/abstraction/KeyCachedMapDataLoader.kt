package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class KeyCachedMapDataLoader<K, V> : AbstractDataLoader<KeyCachedMapDataLoader<K, V>>() {
    private val data = mutableMapOf<K, V>()
    private val modified = mutableSetOf<K>()

    operator fun set(k: K, v: V) {
        set(k, v, true)
    }

    fun set(k: K, v: V, save: Boolean = true) {
        data[k] = v
        if (save) {
            modified += k
            reserveSave()
        }
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

    fun cloneMap(): Map<K, V> = HashMap(data)

    fun getModifiedContents(clearCache: Boolean = true): Pair<List<K>, Map<K, V>> {
        val map = mutableMapOf<K, V>()
        val removed = mutableListOf<K>()
        for (x in modified) {
            if (!data.containsKey(x)) {
                removed += x
            } else {
                map[x] = data[x]!!
            }
        }
        if (clearCache) {
            modified.clear()
        }
        return removed to map
    }
}