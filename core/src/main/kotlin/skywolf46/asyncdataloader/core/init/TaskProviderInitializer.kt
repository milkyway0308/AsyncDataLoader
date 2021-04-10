package skywolf46.asyncdataloader.core.init

import skywolf46.asyncdataloader.core.abstraction.AbstractDataLoaderInitializer
import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.impl.MinecraftTask

class TaskProviderInitializer : AbstractDataLoaderInitializer() {
    override fun load() {
        println("AsyncDataLoader - Core | Looking for default Async task provider...")
        try {
            Class.forName("org.bukkit.Bukkit")
            println("AsyncDataLoader - Core | Minecraft detected! Registering MinecraftTask for default provider.")
            AbstractTaskReadyProvider.defaultTask = MinecraftTask
            return
        } catch (_: Exception) {

        }
        println("AsyncDataLoader - Core | Failed to register async task provider.")
    }

}