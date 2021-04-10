package skywolf46.asyncdataloader.core.abstraction.data

import java.util.concurrent.atomic.AtomicInteger

data class DataCounter<X : Any>(val counter: AtomicInteger = AtomicInteger(60), val data: X) {
    var currentTask: (() -> Unit)? = null
    fun reset(time: Int) {
        counter.set(time)
    }

    fun isExpired(): Boolean {
        return counter.decrementAndGet() <= 0
    }

    fun isTaskRegistered() = currentTask != null

    fun cancelTask() {
        currentTask?.invoke()
        currentTask = null
    }
}