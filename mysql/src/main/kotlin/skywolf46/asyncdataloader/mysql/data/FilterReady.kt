package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.IByteFilter

class FilterReady(private val filter: List<IByteFilter>, private var arr: ByteArray) {

    fun discover(): ByteArray {

        return arr
    }

}