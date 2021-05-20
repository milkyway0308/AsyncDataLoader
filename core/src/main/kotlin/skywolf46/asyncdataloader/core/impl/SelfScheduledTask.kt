package skywolf46.asyncdataloader.core.impl

import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.data.DataCounter
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader
import skywolf46.asyncdataloader.core.util.RunOnShutdownScheduledExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

object SelfScheduledTask : AbstractTaskReadyProvider() {
    private val pool = RunOnShutdownScheduledExecutorService(Executors.newScheduledThreadPool(60))
    var isClosing = AtomicBoolean(false)

    override fun doAsync(snapshot: (Boolean) -> Unit) {
        pool.submit {
            snapshot(false)
        }
    }

    override fun registerTask(provider: DataCounter<AbstractDataLoader<Any>>) {
        provider.cancelTask()
        val cancelled = AtomicBoolean(false)
        provider.currentTask = {
            cancelled.set(true)
        }
        scheduleRepeat(cancelled, provider)
    }

    override fun finalizeProvider() {
        println("AsyncDataLoader-Core | Finalizing task provider..")
        isClosing.set(true)

        pool.shutdown()
        println("AsyncDataLoader-Core | Task provider finalized.")
    }

    fun scheduleRepeat(cancelled: AtomicBoolean, provider: DataCounter<AbstractDataLoader<Any>>) {
        if (cancelled.get())
            return
        if (isClosing.get() || provider.isExpired()) {
            provider.currentTask = null
            val snapshotReady = provider.data.snapshot()
            provider.data.task = null
            snapshotReady.trigger(isClosing.get())
            cancelled.set(true)
        } else {
            pool.schedule(ShutdownSafeRunnable(isClosing, {
                scheduleRepeat(cancelled, provider)
                println("Scheduled.")
            }) {
                println("Force save.")
                provider.data.forceSave()
            }, 1, TimeUnit.SECONDS)
        }
    }

}