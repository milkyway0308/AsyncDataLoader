package skywolf46.asyncdataloader.core.util

import java.io.DataInput

class SafeDataInput<T>(val input: T) : DataInput, AutoCloseable where T : DataInput, T : AutoCloseable {
    var isClosed: Boolean = false
        private set


    override fun readFully(b: ByteArray) {
        input.readFully(b)
    }

    override fun readFully(b: ByteArray, off: Int, len: Int) {
        input.readFully(b, off, len)
    }

    override fun skipBytes(n: Int): Int {
        return input.skipBytes(n)
    }

    override fun readBoolean(): Boolean {
        return input.readBoolean()
    }

    override fun readByte(): Byte {
        return input.readByte()
    }

    override fun readUnsignedByte(): Int {
        return input.readUnsignedByte()
    }

    override fun readShort(): Short {
        return input.readShort()
    }

    override fun readUnsignedShort(): Int {
        return input.readUnsignedShort()
    }

    override fun readChar(): Char {
        return input.readChar()
    }

    override fun readInt(): Int {
        return input.readInt()
    }

    override fun readLong(): Long {
        return input.readLong()
    }

    override fun readFloat(): Float {
        return input.readFloat()
    }

    override fun readDouble(): Double {
        return input.readDouble()
    }

    override fun readLine(): String {
        return input.readLine()
    }

    override fun readUTF(): String {
        return input.readUTF()
    }

    override fun close() {
        if (isClosed)
            return
        isClosed = true
        input.close()
    }


}