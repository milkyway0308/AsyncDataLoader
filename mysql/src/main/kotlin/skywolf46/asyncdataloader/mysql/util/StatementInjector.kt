package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.IByteFilter
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.storage.SQLStructureStorage
import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Date
import java.util.*
import java.util.concurrent.locks.ReentrantLock

class StatementInjector(val table: SQLTable, private val sql: String) : PreparedStatement, IStatementInput {
    private var currentCursor = 1
    private var isWarned = false
    private var filters = mutableListOf<IByteFilter>()
    private var ignoredFilter = mutableListOf<IByteFilter>()
    var main: PreparedStatement? = null


    override fun <T : Any?> unwrap(iface: Class<T>?): T {
        return main!!.unwrap(iface)
    }

    override fun isWrapperFor(iface: Class<*>?): Boolean {
        return main!!.isWrapperFor(iface)
    }

    override fun close() {
        main!!.close()
    }

    override fun executeQuery(): ResultSet {
        return main!!.executeQuery()
    }

    override fun executeQuery(sql: String?): ResultSet {
        return main!!.executeQuery(sql)
    }

    override fun executeUpdate(): Int {
        return main!!.executeUpdate()
    }

    override fun executeUpdate(sql: String?): Int {
        return main!!.executeUpdate(sql)
    }

    override fun executeUpdate(sql: String?, autoGeneratedKeys: Int): Int {
        return main!!.executeUpdate(sql, autoGeneratedKeys)
    }

    override fun executeUpdate(sql: String?, columnIndexes: IntArray?): Int {
        return main!!.executeUpdate(sql, columnIndexes)
    }

    override fun executeUpdate(sql: String?, columnNames: Array<out String>?): Int {
        return main!!.executeUpdate(sql, columnNames)
    }

    override fun getMaxFieldSize(): Int {
        return main!!.maxFieldSize
    }

    override fun setMaxFieldSize(max: Int) {
        main!!.maxFieldSize = max
    }

    override fun getMaxRows(): Int {
        return main!!.maxRows
    }

    override fun setMaxRows(max: Int) {
        main!!.maxRows = max
    }

    override fun setEscapeProcessing(enable: Boolean) {
        main!!.setEscapeProcessing(enable)
    }

    override fun getQueryTimeout(): Int {
        return main!!.queryTimeout
    }

    override fun setQueryTimeout(seconds: Int) {
        main!!.queryTimeout = seconds
    }

    override fun cancel() {
        main!!.cancel()
    }

    override fun getWarnings(): SQLWarning {
        return main!!.warnings
    }

    override fun clearWarnings() {
        main!!.clearWarnings()
    }

    override fun setCursorName(name: String?) {
        main!!.setCursorName(name)
    }

    override fun execute(): Boolean {
        return main!!.execute()
    }

    override fun execute(sql: String?): Boolean {
        return main!!.execute(sql)
    }

    override fun execute(sql: String?, autoGeneratedKeys: Int): Boolean {
        return main!!.execute(sql, autoGeneratedKeys)
    }

    override fun execute(sql: String?, columnIndexes: IntArray?): Boolean {
        return main!!.execute(sql, columnIndexes)
    }

    override fun execute(sql: String?, columnNames: Array<out String>?): Boolean {
        return main!!.execute(sql, columnNames)
    }

    override fun getResultSet(): ResultSet {
        return main!!.resultSet
    }

    override fun getUpdateCount(): Int {
        return main!!.updateCount
    }

    override fun getMoreResults(): Boolean {
        return main!!.moreResults
    }

    override fun getMoreResults(current: Int): Boolean {
        return main!!.getMoreResults(current)
    }

    override fun setFetchDirection(direction: Int) {
        main!!.fetchDirection = direction
    }

    override fun getFetchDirection(): Int {
        return main!!.fetchDirection
    }

    override fun setFetchSize(rows: Int) {
        main!!.fetchSize = rows
    }

    override fun getFetchSize(): Int {
        return main!!.fetchSize
    }

    override fun getResultSetConcurrency(): Int {
        return main!!.resultSetConcurrency
    }

    override fun getResultSetType(): Int {
        return main!!.resultSetType
    }

    override fun addBatch() {
        main!!.addBatch()
    }

    override fun addBatch(sql: String?) {
        main!!.addBatch(sql)
    }

    override fun clearBatch() {
        main!!.clearBatch()
    }

    override fun executeBatch(): IntArray {
        return main!!.executeBatch()
    }

    override fun getConnection(): Connection {
        return main!!.connection
    }

    override fun getGeneratedKeys(): ResultSet {
        return main!!.generatedKeys
    }

    override fun getResultSetHoldability(): Int {
        return main!!.resultSetHoldability
    }

    override fun isClosed(): Boolean {
        return main!!.isClosed
    }

    override fun setPoolable(poolable: Boolean) {
        main!!.isPoolable = poolable
    }

    override fun isPoolable(): Boolean {
        return main!!.isPoolable
    }

    override fun closeOnCompletion() {
        main!!.closeOnCompletion()
    }

    override fun isCloseOnCompletion(): Boolean {
        return main!!.isCloseOnCompletion
    }

    override fun setNull(parameterIndex: Int, sqlType: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNull(parameterIndex, sqlType)
    }

    override fun setNull(parameterIndex: Int, sqlType: Int, typeName: String?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNull(parameterIndex, sqlType, typeName)
    }

    override fun setBoolean(parameterIndex: Int, x: Boolean) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBoolean(parameterIndex, x)
    }

    override fun setByte(parameterIndex: Int, x: Byte) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setByte(parameterIndex, x)
    }

    override fun setShort(parameterIndex: Int, x: Short) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setShort(parameterIndex, x)
    }

    override fun setInt(parameterIndex: Int, x: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setInt(parameterIndex, x)
    }

    override fun setLong(parameterIndex: Int, x: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setLong(parameterIndex, x)
    }

    override fun setFloat(parameterIndex: Int, x: Float) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setFloat(parameterIndex, x)
    }

    override fun setDouble(parameterIndex: Int, x: Double) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setDouble(parameterIndex, x)
    }

    override fun setBigDecimal(parameterIndex: Int, x: BigDecimal?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBigDecimal(parameterIndex, x)
    }

    override fun setString(parameterIndex: Int, x: String?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setString(parameterIndex, x)
    }

    override fun setBytes(parameterIndex: Int, x: ByteArray?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBytes(parameterIndex, x)
    }

    override fun setDate(parameterIndex: Int, x: Date?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setDate(parameterIndex, x)
    }

    override fun setDate(parameterIndex: Int, x: Date?, cal: Calendar?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setDate(parameterIndex, x, cal)
    }

    override fun setTime(parameterIndex: Int, x: Time?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setTime(parameterIndex, x)
    }

    override fun setTime(parameterIndex: Int, x: Time?, cal: Calendar?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setTime(parameterIndex, x, cal)
    }

    override fun setTimestamp(parameterIndex: Int, x: Timestamp?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setTimestamp(parameterIndex, x)
    }

    override fun setTimestamp(parameterIndex: Int, x: Timestamp?, cal: Calendar?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setTimestamp(parameterIndex, x, cal)
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?, length: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setAsciiStream(parameterIndex, x, length)
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setAsciiStream(parameterIndex, x, length)
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setAsciiStream(parameterIndex, x)
    }

    override fun setUnicodeStream(parameterIndex: Int, x: InputStream?, length: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setUnicodeStream(parameterIndex, x, length)
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?, length: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBinaryStream(parameterIndex, x, length)
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBinaryStream(parameterIndex, x, length)
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBinaryStream(parameterIndex, x)
    }

    override fun clearParameters() {
        main!!.clearParameters()
    }

    override fun setObject(parameterIndex: Int, x: Any?, targetSqlType: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setObject(parameterIndex, x, targetSqlType)
    }

    override fun setObject(parameterIndex: Int, x: Any?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setObject(parameterIndex, x)
    }

    override fun setObject(parameterIndex: Int, x: Any?, targetSqlType: Int, scaleOrLength: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setObject(parameterIndex, x, targetSqlType, scaleOrLength)
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?, length: Int) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setCharacterStream(parameterIndex, reader, length)
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setCharacterStream(parameterIndex, reader, length)
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setCharacterStream(parameterIndex, reader)
    }

    override fun setRef(parameterIndex: Int, x: Ref?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setRef(parameterIndex, x)
    }

    override fun setBlob(parameterIndex: Int, x: Blob?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBlob(parameterIndex, x)
    }

    override fun setBlob(parameterIndex: Int, inputStream: InputStream?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBlob(parameterIndex, inputStream, length)
    }

    override fun setBlob(parameterIndex: Int, inputStream: InputStream?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setBlob(parameterIndex, inputStream)
    }

    override fun setClob(parameterIndex: Int, x: Clob?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setClob(parameterIndex, x)
    }

    override fun setClob(parameterIndex: Int, reader: Reader?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setClob(parameterIndex, reader, length)
    }

    override fun setClob(parameterIndex: Int, reader: Reader?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setClob(parameterIndex, reader)
    }

    override fun setArray(parameterIndex: Int, x: java.sql.Array?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setArray(parameterIndex, x)
    }

    override fun getMetaData(): ResultSetMetaData {
        return main!!.metaData
    }

    override fun setURL(parameterIndex: Int, x: URL?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setURL(parameterIndex, x)
    }

    override fun getParameterMetaData(): ParameterMetaData {
        return main!!.parameterMetaData
    }

    override fun setRowId(parameterIndex: Int, x: RowId?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setRowId(parameterIndex, x)
    }

    override fun setNString(parameterIndex: Int, value: String?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNString(parameterIndex, value)
    }

    override fun setNCharacterStream(parameterIndex: Int, value: Reader?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNCharacterStream(parameterIndex, value, length)
    }

    override fun setNCharacterStream(parameterIndex: Int, value: Reader?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNCharacterStream(parameterIndex, value)
    }

    override fun setNClob(parameterIndex: Int, value: NClob?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNClob(parameterIndex, value)
    }

    override fun setNClob(parameterIndex: Int, reader: Reader?, length: Long) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNClob(parameterIndex, reader, length)
    }

    override fun setNClob(parameterIndex: Int, reader: Reader?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setNClob(parameterIndex, reader)
    }

    override fun setSQLXML(parameterIndex: Int, xmlObject: SQLXML?) {
        if (currentCursor != parameterIndex && !isWarned) {
            isWarned = true
            println("Warning: Cursor declaration without auto increment can cause data invalidation")
        }
        main!!.setSQLXML(parameterIndex, xmlObject)
    }

    override fun appendByte(x: Byte) {
        setByte(currentCursor, x)
        currentCursor++
    }

    override fun appendShort(x: Short) {
        setShort(currentCursor, x)
        currentCursor++
    }

    override fun appendInt(x: Int) {
        setInt(currentCursor, x)
        currentCursor++
    }

    override fun appendLong(x: Long) {
        setLong(currentCursor, x)
        currentCursor++
    }

    override fun appendFloat(x: Float) {
        setFloat(currentCursor, x)
        currentCursor++
    }

    override fun appendDouble(x: Double) {
        setDouble(currentCursor, x)
        currentCursor++
    }

    override fun appendString(x: String) {
        setString(currentCursor, x)
        currentCursor++
    }

    private var isCancelled = false
    private val lock = ReentrantLock(true)
    private val runList = mutableListOf<IStatementInput.() -> Unit>()

    override fun appendByteArray(arr: ByteArray) {
        if (filters.size == ignoredFilter.size) {
            appendByteArrayWithoutFilter(arr)
            return
        }
        var array = arr
        for (x in filters) {
            if (ignoredFilter.contains(x))
                continue
            array = x.filterEncode(array, this)
            if (isCancelled) {
                val runner = mutableListOf<IStatementInput.() -> Unit>()
                runner.addAll(runList)
                runList.clear()
                for (y in runner)
                    y(this)
                break
            }
        }
        ignoredFilter.clear()
        appendByteArrayWithoutFilter(array)
    }

    override fun appendByteArrayWithoutFilter(arr: ByteArray) {
        setBytes(currentCursor++, arr)
    }

    override fun appendArray(arr: Array<Any?>) {
        TODO("Not yet implemented")
    }

    override fun appendNonNullArray(arr: Array<Any>) {
        TODO("Not yet implemented")
    }

    override fun append(x: Any?) {
        if (x == null) {
            return
        }
        val structure = SQLStructureStorage.get(x.javaClass.kotlin)
        structure?.deconstruct(x, this)
            ?: throw IllegalStateException("SQL deserialization for ${x.javaClass.name} not supported")
    }

    override fun cancelTask(runAfter: IStatementInput.() -> Unit) {
        lock.tryLock()
        isCancelled = true
        runList.add(runAfter)
        lock.unlock()
    }

    override fun revokeTask(unit: IStatementInput.() -> Unit) {
        while (index < objs.size) {
            if (isCancelled)
                return
            append(objs[index++])
        }
        unit(this)
        if (!isBatch) {
            revoker?.let {
                executeQuery().run {
                    try {
                        it(ResultInjector(this))
                    } catch (_: Exception) {
                    }
                    if (!isClosed)
                        close()
                }
            } ?: run {
                execute()
                if (!isClosed)
                    close()
            }
            finalizer?.invoke()
        } else {
            // Batch not support revoker
            addBatch()
        }
    }

    // TODO remove it
    override fun resetCancellation() {
        TODO("Not yet implemented")
    }


    override fun ignoreFilter(filter: IByteFilter): IStatementInput {
        if (!ignoredFilter.contains(filter))
            ignoredFilter.add(filter)
        return this
    }

    override fun resetFilter(filter: IByteFilter): IStatementInput {
        if (ignoredFilter.contains(filter))
            ignoredFilter.remove(filter)
        return this
    }

    override fun appendFilter(filter: IByteFilter): IStatementInput {
        this.filters.add(filter)
        return this
    }

    override fun trySync() {
        lock.tryLock()
    }

    override fun endSync() {
        lock.unlock()
    }

    override fun asStatement(): StatementInjector {
        return this
    }

    override fun asStatementInput(): IStatementInput {
        return this
    }

    override fun reset(unit: IStatementInput.() -> Unit): IStatementInput {
        main = table.connection!!.prepareStatement(sql)
        return this
    }

    override fun resetIfNotExists(unit: IStatementInput.() -> Unit): IStatementInput {
        if (main == null)
            reset(unit)
        return this
    }

    override fun getSQL(): String {
        return sql
    }

    private var isBatch = false
    private var index = 0
    private var objs = mutableListOf<Any>()
    private var revoker: (IStatementOutput.() -> Unit)? = null
    private var finalizer: (() -> Unit)? = null
    override fun executeQuery(obj: MutableList<Any>, unit: IStatementOutput.() -> Unit) {
        reset { }
        isBatch = false
        index = 0
        objs = obj
        revoker = unit
        revokeTask()
    }

    override fun execute(obj: MutableList<Any>, unit: (() -> Unit)?) {
        reset { }
        isBatch = false
        index = 0
        objs = obj
        revoker = null
        finalizer = unit
        revokeTask()
    }

    override fun batch(obj: MutableList<Any>) {
        resetIfNotExists { }
        isBatch = true
        index = 0
        objs = obj
        revoker = null
        revokeTask()
    }

    override fun finalizeBatch() {
        executeBatch()
    }


}