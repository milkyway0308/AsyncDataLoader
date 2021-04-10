package skywolf46.asyncdataloader.core.abstraction

import kotlin.reflect.KClass

abstract class AbstractDataProvider<X : Any, V : Any> {
    companion object {
        private val cache = mutableMapOf<KClass<AbstractDataProvider<Any, Any>>, AbstractDataProvider<Any, Any>>()
        fun <X : Any, V : Any> request(cls: KClass<AbstractDataProvider<X, V>>, params: X): V {
            return cache.computeIfAbsent(cls as KClass<AbstractDataProvider<Any, Any>>) {
                cls.objectInstance ?: cls.java.newInstance()
            }.request(params) as V
        }

        protected fun <X : Any, V : Any> register(item: AbstractDataProvider<X, V>) {
            cache[item::class as KClass<AbstractDataProvider<Any, Any>>] = item as AbstractDataProvider<Any, Any>
        }
    }

    open fun request(param: X): V? {
        return null
    }

    fun register() {
        Companion.register(this)
    }
}

