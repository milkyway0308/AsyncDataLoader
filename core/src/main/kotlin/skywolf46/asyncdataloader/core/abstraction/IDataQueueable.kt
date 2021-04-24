package skywolf46.asyncdataloader.core.abstraction

import skywolf46.asyncdataloader.core.abstraction.enums.LoadState

interface IDataQueueable<T: Any> {
    fun runIfLoaded(unit: (T) -> Unit)

    fun runQueue()

    fun isCaching(): Boolean

    fun getState() : LoadState
}