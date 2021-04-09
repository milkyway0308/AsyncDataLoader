package skywolf46.asyncdataloader.core

import org.bukkit.plugin.java.JavaPlugin
import skywolf46.asyncdataloader.core.init.DataLoaderInitializer

class AsyncDataLoader : JavaPlugin() {
    override fun onEnable() {
        DataLoaderInitializer.initialize()
    }
}