package skywolf46.asyncdataloader.core.init

import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.impl.SelfScheduledTask

class TaskProviderInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        println("AsyncDataLoader - Core | Looking for default Async task provider...")
        println("AsyncDataLoader - Core | Registering SelfScheduledTask for default provider.")
        AbstractTaskReadyProvider.defaultTask = SelfScheduledTask
        try {
            Class.forName("org.bukkit.Bukkit")
            return
        } catch (_: Exception) {
            println("AsyncDataLoader - Core | org.bukkit.Bukkit not found! Adding shutdown hook.")
            Runtime.getRuntime().addShutdownHook(Thread {
                AbstractTaskReadyProvider.defaultTask?.finalizeProvider()
            })
        }
        println("AsyncDataLoader - Core | Failed to register async task provider.")
    }

}