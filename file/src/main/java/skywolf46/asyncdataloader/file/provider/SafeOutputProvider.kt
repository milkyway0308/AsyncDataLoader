package skywolf46.asyncdataloader.file.provider

import skywolf46.asyncdataloader.core.abstraction.AbstractDataProvider
import skywolf46.asyncdataloader.core.util.SafeDataOutput
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream

object SafeOutputProvider : AbstractDataProvider<File, SafeDataOutput<DataOutputStream>>() {
    override fun request(param: File): SafeDataOutput<DataOutputStream>? {
        if (!param.exists()) {
            if (param.parentFile != null)
                param.parentFile.mkdirs()
            param.createNewFile()
        }
        return SafeDataOutput(DataOutputStream(FileOutputStream(param)))
    }
}