package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.util.StatementInjector

interface IStatementInput {
    fun appendByte(x: Byte)
    fun appendShort(x: Short)
    fun appendInt(x: Int)
    fun appendLong(x: Long)
    fun appendFloat(x: Float)
    fun appendDouble(x: Double)
    fun appendString(x: String)
    fun appendByteArray(arr: ByteArray)

    @Deprecated("Deprecated for normal use - Filters only")
    fun appendByteArrayWithoutFilter(arr: ByteArray)
    fun appendArray(arr: Array<Any?>)
    fun appendNonNullArray(arr: Array<Any>)
    fun append(x: Any?)

    /**
     * Cancel task execution.
     * When method called, method will be remove statement from SQL thread.
     * If cancelled, cursor will not move.
     */
    fun cancelTask(runAfter: IStatementInput.() -> Unit = {})

    /**
     * Revoke task execution.
     * When method called, method will return statement to SQL thread.
     */
    fun revokeTask(unit: IStatementInput.() -> Unit = {})

    @Deprecated("Reset cancel status. Internal use only")
    fun resetCancellation()

    /**
     * Remove filter from filtering list.
     */
    fun ignoreFilter(filter: IByteFilter): IStatementInput

    /**
     * Reset ignore state of filter.
     */
    fun resetFilter(filter: IByteFilter): IStatementInput

    /**
     * Register filter.
     * IByteFilter only invokes when write a byte array.
     */
    fun appendFilter(filter: IByteFilter): IStatementInput

    /**
     * Start sync with statement.
     */
    fun trySync()

    /**
     * End sync with statement.
     */
    fun endSync()

    /**
     * Self cast as [StatementInjector]
     */
    fun asStatement(): StatementInjector

    /**
     * Self cast as [IStatementInput].
     */
    fun asStatementInput(): IStatementInput

    /**
     * Reset statement.
     * After reset, statement will be remake.
     */
    fun reset(unit: IStatementInput.() -> Unit)

    fun getSQL(): String

    fun execute(obj: List<Any>)
}
