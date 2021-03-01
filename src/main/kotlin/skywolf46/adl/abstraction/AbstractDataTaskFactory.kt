package skywolf46.adl.abstraction

abstract class AbstractDataTaskFactory<T : AbstractDataTask> {
    abstract fun getTargetClass(): Class<T>
    abstract fun createSaver(snap: AbstractDataLoader<*, *>): T


}