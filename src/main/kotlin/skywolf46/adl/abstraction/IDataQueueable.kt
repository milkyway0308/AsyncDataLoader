package skywolf46.adl.abstraction

interface IDataQueueable {
    fun <T, F : AbstractDataSnapshot<T>, S : AbstractDataLoader<T, F>> S.runIfLoaded(unit: S.() -> Unit): S
}