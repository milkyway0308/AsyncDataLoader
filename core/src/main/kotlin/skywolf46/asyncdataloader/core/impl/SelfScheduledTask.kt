package skywolf46.asyncdataloader.core.impl

import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.data.DataCounter
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

object SelfScheduledTask : AbstractTaskReadyProvider() {
    private val pool = Executors.newScheduledThreadPool(60)
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
        pool.shutdownNow().forEach {
            it.run()
        }
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
        } else {
            pool.scheduleAtFixedRate({
                scheduleRepeat(cancelled, provider)
            }, 1, 1, TimeUnit.SECONDS)
        }

    }

}