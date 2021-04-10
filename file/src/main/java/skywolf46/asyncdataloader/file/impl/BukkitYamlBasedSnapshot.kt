package skywolf46.asyncdataloader.file.impl

import org.bukkit.configuration.file.YamlConfiguration
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataSnapshot
import java.io.File

abstract class BukkitYamlBasedSnapshot(val file: File) : AbstractDataSnapshot(){
    fun yaml(data: YamlConfiguration.() -> Unit) {
        val conf = YamlConfiguration()
        data(conf)
        conf.save(file)
    }
}