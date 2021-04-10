package skywolf46.asyncdataloader.file.impl

import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataSnapshot
import skywolf46.asyncdataloader.core.util.SafeDataOutput
import skywolf46.asyncdataloader.file.provider.SafeOutputProvider
import java.io.DataOutputStream
import java.io.File

abstract class FileBasedSnapshot(val file: File) : AbstractDataSnapshot() {

    fun stream(data: SafeDataOutput<DataOutputStream>.() -> Unit) {
        val stream = SafeOutputProvider.request(file)
        stream?.let {
            data(it)
            stream.close()
        }
    }
}