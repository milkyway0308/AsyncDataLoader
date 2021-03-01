package skywolf46.adl.abstraction.factory

import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.adl.abstraction.AbstractDataTaskFactory
import skywolf46.adl.abstraction.tasks.SchedulerBasedDataSaveTask

class SchedulerBasedDataTaskFactory : AbstractDataTaskFactory<SchedulerBasedDataSaveTask>() {
    override fun getTargetClass(): Class<SchedulerBasedDataSaveTask> = SchedulerBasedDataSaveTask::class.java

    override fun createSaver(snap: AbstractDataLoader<*, *>): SchedulerBasedDataSaveTask =
        SchedulerBasedDataSaveTask(snap)
}