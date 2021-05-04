package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class LoaderContainer<K : Any, V : AbstractDataLoader<V>> {
    private val map = mutableMapOf<K, V>()
    operator fun get(key: K, unit: V.() -> Unit = {}): V {
        val v = map.computeIfAbsent(key) {
            return@computeIfAbsent generateValue(key)
        }
        v(unit)
        return v
    }

    protected abstract fun generateValue(key: K): V
}