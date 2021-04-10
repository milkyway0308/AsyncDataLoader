package skywolf46.asyncdataloader.core.abstraction.loader

import skywolf46.asyncdataloader.core.abstraction.AbstractDataProvider
import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.enums.LoadState
import kotlin.reflect.KClass

abstract class AbstractDataLoadRequester<X : Any, V : Any> {
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

    abstract fun trigger(data: X, main: (V) -> Unit, afterRun: (LoadState) -> Unit = {})

    open fun triggerAsync(
        taskReady: AbstractTaskReadyProvider,
        data: X,
        main: (V) -> Unit,
        afterRun: (LoadState) -> Unit = {},
    ) {
        taskReady.run {
            trigger(data, main, afterRun)
        }
    }

    fun triggerAsync(data: X, main: (V) -> Unit, afterRun: (LoadState) -> Unit = {}) {
        AbstractTaskReadyProvider.defaultTask?.run {
            triggerAsync(this, data, main, afterRun)
        } ?: throw IllegalStateException("No default AbstractTaskReadyProvider exists.")
    }

}