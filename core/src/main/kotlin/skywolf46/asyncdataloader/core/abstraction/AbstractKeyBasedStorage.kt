package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class AbstractKeyBasedStorage<K, X : Any, V : AbstractDataLoader<X>> : AbstractDataLoaderFactory<K, X, V>() {
    private val map = mutableMapOf<K, V>()

    operator fun get(k: K): V {
        return map.computeIfAbsent(k) { provide(k) }
    }

    fun ready(k: K, unit: X.() -> Unit) {
        get(k).runIfLoaded {
            unit(this)
        }
    }
}