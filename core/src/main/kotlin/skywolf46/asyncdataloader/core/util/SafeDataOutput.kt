package skywolf46.asyncdataloader.core.util

import java.io.DataOutput

class SafeDataOutput<T>(val dataOutput: T) : DataOutput, AutoCloseable where T : DataOutput, T : AutoCloseable {
    var isClosed = false
        private set

    override fun write(b: Int) {
        dataOutput.write(b)
    }

    override fun write(b: ByteArray) {
        dataOutput.write(b)
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        dataOutput.write(b, off, len)
    }

    override fun writeBoolean(v: Boolean) {
        dataOutput.writeBoolean(v)
    }

    override fun writeByte(v: Int) {
        dataOutput.writeByte(v)
    }

    override fun writeShort(v: Int) {
        dataOutput.writeShort(v)
    }

    override fun writeChar(v: Int) {
        dataOutput.writeChar(v)
    }

    override fun writeInt(v: Int) {
        dataOutput.writeInt(v)
    }

    override fun writeLong(v: Long) {
        dataOutput.writeLong(v)
    }

    override fun writeFloat(v: Float) {
        dataOutput.writeFloat(v)
    }

    override fun writeDouble(v: Double) {
        dataOutput.writeDouble(v)
    }

    override fun writeBytes(s: String) {
        dataOutput.writeBytes(s)
    }

    override fun writeChars(s: String) {
        dataOutput.writeChars(s)
    }

    override fun writeUTF(s: String) {
        dataOutput.writeUTF(s)
    }

    override fun close() {
        if (isClosed)
            return
        isClosed = true
        dataOutput.close()
    }

}