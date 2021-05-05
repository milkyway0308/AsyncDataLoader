package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.data.DataCounter
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

abstract class AbstractTaskReadyProvider {
    companion object {
        var defaultTask: AbstractTaskReadyProvider? = null
    }

    // If true, system is finalizing.
    abstract fun doAsync(snapshot: (Boolean) -> Unit)
    abstract fun registerTask(provider: DataCounter<AbstractDataLoader<Any>>)
    abstract fun finalizeProvider()
}