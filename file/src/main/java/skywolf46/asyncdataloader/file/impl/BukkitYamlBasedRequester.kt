package skywolf46.asyncdataloader.file.impl

import org.bukkit.configuration.file.YamlConfiguration
import skywolf46.asyncdataloader.core.abstraction.enums.LoadState
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoadRequester
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader
import java.io.File

object BukkitYamlBasedRequester : AbstractDataLoadRequester<File, YamlConfiguration>() {

    override fun request(param: File): YamlConfiguration? {
        if (!param.exists())
            return null
        return YamlConfiguration.loadConfiguration(param)
    }

    override fun trigger(data: File, main: (YamlConfiguration) -> Unit, afterRun: (LoadState) -> Unit) {
        try {
            request(data).let {
                it?.run {
                    afterRun(LoadState.LOADED)
                } ?: {
                    afterRun(LoadState.EMPTY_LOAD)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            afterRun(LoadState.LOAD_FAIL)
        }
    }

}

fun <T : AbstractDataLoader> T.loadBukkitYaml(file: File, unit: YamlConfiguration.() -> Unit) {
    this.loadRequest(BukkitYamlBasedRequester, file, unit)
}