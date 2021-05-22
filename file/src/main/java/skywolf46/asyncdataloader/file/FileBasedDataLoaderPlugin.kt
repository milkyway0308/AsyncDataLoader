package skywolf46.asyncdataloader.file

import org.bukkit.plugin.java.JavaPlugin
import skywolf46.asyncdataloader.core.AsyncDataLoader

class FileBasedDataLoaderPlugin : JavaPlugin() {
    override fun onDisable() {
        AsyncDataLoader.inst.disableProvider()
    }
}