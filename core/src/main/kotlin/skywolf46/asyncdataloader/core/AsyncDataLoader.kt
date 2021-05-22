package skywolf46.asyncdataloader.core

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.init.DataLoaderInitializer

class AsyncDataLoader : JavaPlugin() {
    companion object {
        lateinit var inst: AsyncDataLoader
            private set
    }

    override fun onEnable() {
        inst = this
        Bukkit.getScheduler().scheduleSyncDelayedTask(this) {
            DataLoaderInitializer.initialize()
        }
    }

    override fun onDisable() {
        disableProvider()
    }

    fun disableProvider() {
        AbstractTaskReadyProvider.defaultTask?.finalizeProvider()
        AbstractTaskReadyProvider.defaultTask = null
    }
}