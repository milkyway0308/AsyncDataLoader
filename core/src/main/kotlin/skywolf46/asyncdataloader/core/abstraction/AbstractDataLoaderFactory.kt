package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class AbstractDataLoaderFactory<K, X: Any, V : AbstractDataLoader<X>> {
    abstract fun provide(k: K): V
}