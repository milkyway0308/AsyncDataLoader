package skywolf46.asyncdataloader.mysql

import org.bukkit.plugin.java.JavaPlugin
import skywolf46.asyncdataloader.core.AsyncDataLoader

class MySQLDataLoader : JavaPlugin() {
    override fun onDisable() {
        AsyncDataLoader.inst.disableProvider()
    }
}