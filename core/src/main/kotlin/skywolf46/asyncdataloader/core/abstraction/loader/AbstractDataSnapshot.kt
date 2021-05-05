package skywolf46.asyncdataloader.core.abstraction.loader

import skywolf46.asyncdataloader.core.abstraction.AbstractDataProvider
import kotlin.reflect.KClass

abstract class AbstractDataSnapshot() {
    abstract fun trigger(isFinalizing: Boolean)
}