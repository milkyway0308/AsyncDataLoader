package skywolf46.asyncdataloader.mysql.impl.filter

import com.github.luben.zstd.Zstd
import skywolf46.asyncdataloader.mysql.abstraction.IByteFilter
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import java.util.concurrent.Executors

typealias ZstdFilter = ZstdByteFilter

// Default compression = Level 0
// Compression over 200 bytes
class ZstdByteFilter(val level: Int = 0, val compressionByteTarget: Int = 200) : IByteFilter {
    companion object {
        private val scheduler = Executors.newCachedThreadPool()
    }

    override fun filter(arr: ByteArray, statement: IStatementInput): ByteArray {
        if (arr.size < compressionByteTarget) {
            val lx = arr.toMutableList()
            lx.add(0, 0.toByte())
            return lx.toByteArray()
        } else {
            statement.cancelTask {
                scheduler.submit {
                    val ax = Zstd.compress(arr, level)
                    val lx = arr.toMutableList()
                    lx.add(0, 1.toByte())
                    statement.revokeTask {
                        ignoreFilter(this@ZstdByteFilter)
                        appendByteArray(ax)
                    }
                }
            }
        }
        return ByteArray(0)
    }

    override fun filter(arr: ByteArray, unit: ByteArray.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun filterSync(arr: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }
}