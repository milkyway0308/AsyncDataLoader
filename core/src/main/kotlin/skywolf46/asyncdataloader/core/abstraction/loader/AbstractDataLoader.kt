package skywolf46.asyncdataloader.core.abstraction.loader

import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.IDataQueueable
import skywolf46.asyncdataloader.core.abstraction.data.DataCounter
import skywolf46.asyncdataloader.core.abstraction.enums.LoadState
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

abstract class AbstractDataLoader<T : Any> : IDataQueueable<T> {
    var task: DataCounter<AbstractDataLoader<T>>? = null
    protected val loaded = AtomicReference(LoadState.READY)
    private val cache: MutableList<(Any) -> Unit> = mutableListOf()

    abstract fun snapshot(): AbstractDataSnapshot

    operator fun invoke(unit: T.() -> Unit) {
        runIfLoaded(unit)
    }

    fun <K : Any, X : Any> loadRequest(loader: AbstractDataLoadRequester<K, X>, data: K, loadTask: X.() -> Unit) {
        loader.trigger(data, main = loadTask) {
            loaded.set(it)
            runQueue()
        }
    }

    fun <K : Any, X : Any> asyncLoadRequest(loader: AbstractDataLoadRequester<K, X>, data: K, loadTask: X.() -> Unit) {
        loader.triggerAsync(data, main = loadTask) {
            loaded.set(it)
            runQueue()
        }
    }

    fun reserveSave(
        taskReady: AbstractTaskReadyProvider? = AbstractTaskReadyProvider.defaultTask,
        saveCountdown: Int = 60,
    ) {
        task?.reset(saveCountdown) ?: run {
            task = DataCounter(AtomicInteger(saveCountdown), this)
            taskReady?.registerTask(task!! as DataCounter<AbstractDataLoader<Any>>) ?: run {
                throw IllegalStateException("No default AbstractTaskReadyProvider exists.")
            }
        }
    }

    fun forceSave() {
        snapshot().trigger()
    }


    fun forceAsync() {
        AbstractTaskReadyProvider.defaultTask?.doAsync {
            forceSave()
        } ?: run {
            throw IllegalStateException("No default AbstractTaskReadyProvider exists.")
        }
    }

    override fun runIfLoaded(unit: T.() -> Unit) {
        synchronized(loaded) {
            if (loaded.get() != LoadState.READY) {
                unit(this@AbstractDataLoader as T)
            } else {
                cache.add(unit as (Any) -> Unit)
            }
        }
    }

    override fun runQueue() {
        val data: MutableList<(Any) -> Unit> = mutableListOf()
        synchronized(loaded) {
            data.addAll(cache)
            cache.clear()
        }
        data.forEach {
            it.invoke(this@AbstractDataLoader)
        }

    }

    override fun isCaching(): Boolean {
        return loaded.get() == LoadState.READY
    }

    override fun getState(): LoadState {
        return loaded.get()
    }


}