package skywolf46.adl.abstraction

import org.bukkit.Bukkit
import skywolf46.adl.AsyncDataLoader
import skywolf46.adl.abstraction.tasks.SchedulerBasedDataSaveTask
import skywolf46.extrautility.util.ifFalse
import skywolf46.extrautility.util.ifTrue
import skywolf46.extrautility.util.runNonNull
import java.util.concurrent.atomic.AtomicBoolean

abstract class AbstractDataLoader<T, F : AbstractDataSnapshot<T>> : IDataQueueable{
    var isLoaded: AtomicBoolean = AtomicBoolean(false)
        private set
    val cache: MutableList<AbstractDataLoader<T, F>.() -> Unit> = ArrayList()
    private var task: AbstractDataTask? = null

    abstract fun loadData(data: T)

    fun asyncLoadAndExecute(data: T, lambda: () -> Void) {
        Bukkit.getScheduler().runTaskAsynchronously(AsyncDataLoader.inst) {
            loadData(data)
            Bukkit.getScheduler().scheduleSyncDelayedTask(AsyncDataLoader.inst) {
                lambda()
            }
        }
    }

    abstract fun snapshot(): F

    fun forceSave(afterRun: () -> Unit) {
        runNonNull(task) {
            stopTimer()
            task = null
        }
        snapshot().saveSnapshotAndRun { afterRun() }
    }

    fun triggerSave(cls: Class<out AbstractDataTask>, time: Int) {
        task = task ?: AsyncDataLoader.inst?.of(cls)?.createSaver(this)?.apply {
            startTimer()
        }
        task?.resetTrigger(time)
    }

    fun triggerSave(time: Int) = triggerSave(SchedulerBasedDataSaveTask::class.java, time)

    fun triggerSave() = triggerSave(60)

    fun destroyTask() {
        task = null
    }

    protected fun runAllCaches() {
        val lst: MutableList<AbstractDataLoader<T, F>.() -> Unit> = ArrayList()
        synchronized(this) {
            lst.addAll(cache)
            cache.clear()
        }
        for (x in lst)
            x(this)
    }

}

@Suppress("UNCHECKED_CAST")
fun <T, F : AbstractDataSnapshot<T>, S : AbstractDataLoader<T, F>> S.runIfLoaded(unit: S.() -> Unit): S {
    synchronized(this) {
        isLoaded.get().ifFalse {
            cache.add(unit as AbstractDataLoader<T, F>.() -> Unit)
        }.ifTrue {
            unit()
        }
    }
    return this
}

