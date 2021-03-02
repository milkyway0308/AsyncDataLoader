package skywolf46.adl.abstraction

abstract class AbstractDataSnapshot<X>(protected val target: X) {
    fun saveSnapshot() {
        saveSnapshotAndRun { }
    }

    abstract fun saveSnapshotAndRun(unit: () -> Unit)
}