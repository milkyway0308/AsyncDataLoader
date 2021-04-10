package skywolf46.asyncdataloader.file.provider

import skywolf46.asyncdataloader.core.abstraction.AbstractDataProvider
import skywolf46.asyncdataloader.core.util.SafeDataInput
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

object SafeInputProvider : AbstractDataProvider<File, SafeDataInput<DataInputStream>>() {
    override fun request(param: File): SafeDataInput<DataInputStream>? {
        if (!param.exists())
            return null
        return SafeDataInput(DataInputStream(FileInputStream(param)))
    }

}