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
        return lst[cursor++] as Short
    }

    override fun getInt(): Int {
        return lst[cursor++] as Int
    }

    override fun getLong(): Long {
        return lst[cursor++] as Long
    }

    override fun getFloat(): Float {
        return lst[cursor++] as Float
    }

    override fun getDouble(): Double {
        return lst[cursor++] as Double
    }

    override fun getByteArray(): ByteArray {
        return lst[cursor++] as ByteArray
    }

    override fun getByteArrayWithoutFilter(): ByteArray {
        return lst[cursor++] as ByteArray
    }

    override fun getString(): String {
        return lst[cursor++] as String
    }

    override fun appendFilter(filter: IByteFilter): IStatementOutput {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(const: ISQLStructure<T>): T {
        return lst[cursor++] as T
    }

    override fun <T : Any> get(cls: KClass<T>): T {
        return lst[cursor++] as T
    }

    override fun <T : Any> get(cls: Class<T>): T {
        return lst[cursor++] as T
    }

    override fun toResultInjector(): ResultInjector {
        throw IllegalStateException("CachedQueryRow not supports streaming query")
    }

    override fun toStatementOutput(): IStatementOutput {
        return this
    }

    override fun cache(unit: IStatementOutput.() -> Unit): CachedQueryRow {
        TODO("Not yet implemented")
    }

    override fun close() {

    }

}