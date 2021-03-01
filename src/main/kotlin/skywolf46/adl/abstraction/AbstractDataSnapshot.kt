package skywolf46.adl.abstraction

abstract class AbstractDataSnapshot<X>(protected val target: X) {
    abstract fun saveSnapshot()
}