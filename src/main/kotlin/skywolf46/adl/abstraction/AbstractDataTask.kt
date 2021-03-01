package skywolf46.adl.abstraction

import java.util.concurrent.atomic.AtomicInteger

abstract class AbstractDataTask(protected val snapshot: AbstractDataLoader<*, *>) {
    protected val leftTime: AtomicInteger = AtomicInteger(60)

    fun resetTrigger(time: Int) = leftTime.set(time)

    abstract fun startTimer()

    abstract fun stopTimer()

    abstract fun trigger()

    abstract fun triggerAsync()
}