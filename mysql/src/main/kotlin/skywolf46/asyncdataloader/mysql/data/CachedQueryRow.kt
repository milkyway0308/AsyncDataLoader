package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.IByteFilter
import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.util.ResultInjector
import kotlin.reflect.KClass

class CachedQueryRow : IStatementOutput {
    private var cursor = 0
    internal val lst: MutableList<Any> = mutableListOf()
    fun discover() {
        for (i in 0 until lst.size) {
            if (lst[i] is FilterReady)
                lst[i] = (lst[i] as FilterReady).discover()
        }
    }

    override fun getByte(): Byte {
        return lst[cursor++] as Byte
    }

    override fun getShort(): Short {
        TODO("Not yet implemented")
    }

    override fun getInt(): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(): Float {
        TODO("Not yet implemented")
    }

    override fun getDouble(): Double {
        TODO("Not yet implemented")
    }

    override fun getByteArray(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun getByteArrayWithoutFilter(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun appendFilter(filter: IByteFilter): IStatementOutput {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(const: ISQLStructure<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(cls: KClass<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(cls: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun toResultInjector(): ResultInjector {
        throw IllegalStateException("CachedQueryRow not supports streaming query")
    }

    override fun toStatementOutput(): IStatementOutput {
        return this
    }

    override fun close() {
        TODO("Not yet implemented")
    }

}