package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.data.CachedQueryRow
import skywolf46.asyncdataloader.mysql.util.ResultInjector
import java.io.Closeable
import kotlin.reflect.KClass

interface IStatementOutput : Closeable {
    fun getByte(): Byte
    fun getShort(): Short
    fun getInt(): Int
    fun getLong(): Long
    fun getFloat(): Float
    fun getDouble(): Double
    fun getByteArray(): ByteArray
    fun getByteArrayWithoutFilter(): ByteArray
    fun getString(): String
    fun appendFilter(filter: IByteFilter): IStatementOutput

    fun <T : Any> get(const: ISQLStructure<T>): T?

    fun <T : Any> get(cls: KClass<T>): T?

    fun <T : Any> get(cls: Class<T>): T?

    fun toResultInjector(): ResultInjector

    fun toStatementOutput(): IStatementOutput


    fun cache(unit: IStatementOutput.() -> Unit): CachedQueryRow
}