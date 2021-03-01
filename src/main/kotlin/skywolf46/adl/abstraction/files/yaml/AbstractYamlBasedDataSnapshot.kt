package skywolf46.adl.abstraction.files.yaml

import org.bukkit.configuration.file.YamlConfiguration
import skywolf46.adl.abstraction.files.AbstractFileBasedSnapshot
import java.io.DataOutputStream
import java.io.File

abstract class AbstractYamlBasedDataSnapshot(fx: File) : AbstractFileBasedSnapshot(fx) {
    final override fun save(stream: DataOutputStream) {
        val conf = YamlConfiguration()
        save(conf)
        stream.writeUTF(conf.saveToString())
    }

    abstract fun save(conf: YamlConfiguration)
}