package skywolf46.asyncdataloader.file.impl

import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.enums.LoadState
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoadRequester
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader
import skywolf46.asyncdataloader.core.util.SafeDataInput
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

object FileBasedRequester : AbstractDataLoadRequester<File, SafeDataInput<DataInputStream>>() {

    override fun request(param: File): SafeDataInput<DataInputStream>? {
        if (!param.exists())
            return null
        return SafeDataInput(DataInputStream(FileInputStream(param)))
    }


    override fun trigger(
        data: File,
        main: (SafeDataInput<DataInputStream>) -> Unit,
        afterRun: (LoadState) -> Unit,
    ) {
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

fun <T : AbstractDataLoader> T.loadFile(file: File, unit: (SafeDataInput<DataInputStream>) -> Unit) {
    this.loadRequest(FileBasedRequester, file, unit)
}