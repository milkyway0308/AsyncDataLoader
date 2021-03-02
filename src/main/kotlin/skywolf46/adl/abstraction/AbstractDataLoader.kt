package skywolf46.adl.abstraction

import org.bukkit.Bukkit
import skywolf46.adl.AsyncDataLoader
import skywolf46.adl.abstraction.tasks.SchedulerBasedDataSaveTask
import skywolf46.extrautility.util.nonNull
import skywolf46.extrautility.util.runNonNull

abstract class AbstractDataLoader<T, F : AbstractDataSnapshot<T>> {
    protected var task: AbstractDataTask? = null
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

    fun forceSave(afterRun: ()-> Unit){
        runNonNull(task){
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

}