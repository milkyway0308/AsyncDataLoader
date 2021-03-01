package skywolf46.adl.abstraction.files.yaml

import org.bukkit.configuration.file.YamlConfiguration
import skywolf46.adl.abstraction.files.AbstractFileBaseDataLoader
import java.io.DataInputStream
import java.io.InputStreamReader

abstract class AbstractYamlBasedDataLoader : AbstractFileBaseDataLoader() {
    final override fun loadData(stream: DataInputStream) {
        val conf = YamlConfiguration()
        conf.load(stream.readUTF())
        loadData(conf)
    }

    abstract fun loadData(yaml: YamlConfiguration)
}