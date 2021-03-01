package skywolf46.adl.abstraction.files

import skywolf46.adl.abstraction.AbstractDataLoader
import skywolf46.adl.abstraction.AbstractDataSnapshot
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

abstract class AbstractFileBaseDataLoader : AbstractDataLoader<File, AbstractDataSnapshot<File>>() {
    override fun loadData(data: File) {
        if (!data.exists())
            return
        loadData(DataInputStream(FileInputStream(data)))
    }

    abstract fun loadData(stream: DataInputStream)
}