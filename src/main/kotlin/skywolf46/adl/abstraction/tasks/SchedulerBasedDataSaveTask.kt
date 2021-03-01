package skywolf46.adl.abstraction.tasks

import org.bukkit.Bukkit
import skywolf46.adl.AsyncDataLoader
import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.adl.abstraction.AbstractDataSnapshot
import skywolf46.adl.abstraction.AbstractDataTask

class SchedulerBasedDataSaveTask(snapshot: AbstractDataLoader<*,*>) :
    AbstractDataTask(
        snapshot
    ) {
    private var taskID = Int.MIN_VALUE

    override fun startTimer() {
        if (taskID != Int.MIN_VALUE) {
            return
        }
        taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(AsyncDataLoader.inst) {
            if (leftTime.decrementAndGet() <= 0) {
                stopTimer()
                triggerAsync()
            }
        }
    }

    override fun trigger() {
        snapshot.destroyTask()
        snapshot.snapshot().saveSnapshot()
    }

    override fun triggerAsync() {
        snapshot.destroyTask()
        val snap = snapshot.snapshot()
        Bukkit.getScheduler().runTaskAsynchronously(AsyncDataLoader.inst) {
            snap.saveSnapshot()
        }
    }

    override fun stopTimer() {
        if (taskID == Int.MIN_VALUE) {
            Bukkit.getScheduler().cancelTask(taskID)
            taskID = Int.MIN_VALUE
        }
    }
}