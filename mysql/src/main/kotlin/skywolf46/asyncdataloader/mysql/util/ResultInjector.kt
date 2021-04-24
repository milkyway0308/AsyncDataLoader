package skywolf46.asyncdataloader.mysql.util

import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Array
import java.sql.Date
import java.util.*

class ResultInjector(private val main: ResultSet) : ResultSet {
    override fun <T : Any?> unwrap(iface: Class<T>?): T {
        return main.unwrap(iface)
    }

    override fun isWrapperFor(iface: Class<*>?): Boolean {
        return main.isWrapperFor(iface)
    }

    override fun close() {
        main.close()
    }

    override fun next(): Boolean {
        return main.next()
    }

    override fun wasNull(): Boolean {
        return main.wasNull()
    }

    override fun getString(columnIndex: Int): String {
        return main.getString(columnIndex)
    }

    override fun getString(columnLabel: String?): String {
        return main.getString(columnLabel)
    }

    override fun getBoolean(columnIndex: Int): Boolean {
        return main.getBoolean(columnIndex)
    }

    override fun getBoolean(columnLabel: String?): Boolean {
        return main.getBoolean(columnLabel)
    }

    override fun getByte(columnIndex: Int): Byte {
        return main.getByte(columnIndex)
    }

    override fun getByte(columnLabel: String?): Byte {
        return main.getByte(columnLabel)
    }

    override fun getShort(columnIndex: Int): Short {
        return main.getShort(columnIndex)
    }

    override fun getShort(columnLabel: String?): Short {
        return main.getShort(columnLabel)
    }

    override fun getInt(columnIndex: Int): Int {
        return main.getInt(columnIndex)
    }

    override fun getInt(columnLabel: String?): Int {
        return main.getInt(columnLabel)
    }

    override fun getLong(columnIndex: Int): Long {
        return main.getLong(columnIndex)
    }

    override fun getLong(columnLabel: String?): Long {
        return main.getLong(columnLabel)
    }

    override fun getFloat(columnIndex: Int): Float {
        return main.getFloat(columnIndex)
    }

    override fun getFloat(columnLabel: String?): Float {
        return main.getFloat(columnLabel)
    }

    override fun getDouble(columnIndex: Int): Double {
        return main.getDouble(columnIndex)
    }

    override fun getDouble(columnLabel: String?): Double {
        return main.getDouble(columnLabel)
    }

    override fun getBigDecimal(columnIndex: Int, scale: Int): BigDecimal {
        return main.getBigDecimal(columnIndex, scale)
    }

    override fun getBigDecimal(columnLabel: String?, scale: Int): BigDecimal {
        return main.getBigDecimal(columnLabel, scale)
    }

    override fun getBigDecimal(columnIndex: Int): BigDecimal {
        return main.getBigDecimal(columnIndex)
    }

    override fun getBigDecimal(columnLabel: String?): BigDecimal {
        return main.getBigDecimal(columnLabel)
    }

    override fun getBytes(columnIndex: Int): ByteArray {
        return main.getBytes(columnIndex)
    }

    override fun getBytes(columnLabel: String?): ByteArray {
        return main.getBytes(columnLabel)
    }

    override fun getDate(columnIndex: Int): Date {
        return main.getDate(columnIndex)
    }

    override fun getDate(columnLabel: String?): Date {
        return main.getDate(columnLabel)
    }

    override fun getDate(columnIndex: Int, cal: Calendar?): Date {
        return main.getDate(columnIndex, cal)
    }

    override fun getDate(columnLabel: String?, cal: Calendar?): Date {
        return main.getDate(columnLabel, cal)
    }

    override fun getTime(columnIndex: Int): Time {
        return main.getTime(columnIndex)
    }

    override fun getTime(columnLabel: String?): Time {
        return main.getTime(columnLabel)
    }

    override fun getTime(columnIndex: Int, cal: Calendar?): Time {
        return main.getTime(columnIndex, cal)
    }

    override fun getTime(columnLabel: String?, cal: Calendar?): Time {
        return main.getTime(columnLabel, cal)
    }

    override fun getTimestamp(columnIndex: Int): Timestamp {
        return main.getTimestamp(columnIndex)
    }

    override fun getTimestamp(columnLabel: String?): Timestamp {
        return main.getTimestamp(columnLabel)
    }

    override fun getTimestamp(columnIndex: Int, cal: Calendar?): Timestamp {
        return main.getTimestamp(columnIndex, cal)
    }

    override fun getTimestamp(columnLabel: String?, cal: Calendar?): Timestamp {
        return main.getTimestamp(columnLabel, cal)
    }

    override fun getAsciiStream(columnIndex: Int): InputStream {
        return main.getAsciiStream(columnIndex)
    }

    override fun getAsciiStream(columnLabel: String?): InputStream {
        return main.getAsciiStream(columnLabel)
    }

    override fun getUnicodeStream(columnIndex: Int): InputStream {
        return main.getUnicodeStream(columnIndex)
    }

    override fun getUnicodeStream(columnLabel: String?): InputStream {
        return main.getUnicodeStream(columnLabel)
    }

    override fun getBinaryStream(columnIndex: Int): InputStream {
        return main.getBinaryStream(columnIndex)
    }

    override fun getBinaryStream(columnLabel: String?): InputStream {
        return main.getBinaryStream(columnLabel)
    }

    override fun getWarnings(): SQLWarning {
        return main.warnings
    }

    override fun clearWarnings() {
        main.clearWarnings()
    }

    override fun getCursorName(): String {
        return main.cursorName
    }

    override fun getMetaData(): ResultSetMetaData {
        return main.metaData
    }

    override fun getObject(columnIndex: Int): Any {
        return main.getObject(columnIndex)
    }

    override fun getObject(columnLabel: String?): Any {
        return main.getObject(columnLabel)
    }

    override fun getObject(columnIndex: Int, map: MutableMap<String, Class<*>>?): Any {
        return main.getObject(columnIndex, map)
    }

    override fun getObject(columnLabel: String?, map: MutableMap<String, Class<*>>?): Any {
        return main.getObject(columnLabel, map)
    }

    override fun <T : Any?> getObject(columnIndex: Int, type: Class<T>?): T {
        return main.getObject(columnIndex, type)
    }

    override fun <T : Any?> getObject(columnLabel: String?, type: Class<T>?): T {
        return main.getObject(columnLabel, type)
    }

    override fun findColumn(columnLabel: String?): Int {
        return main.findColumn(columnLabel)
    }

    override fun getCharacterStream(columnIndex: Int): Reader {
        return main.getCharacterStream(columnIndex)
    }

    override fun getCharacterStream(columnLabel: String?): Reader {
        return main.getCharacterStream(columnLabel)
    }

    override fun isBeforeFirst(): Boolean {
        return main.isBeforeFirst
    }

    override fun isAfterLast(): Boolean {
        return main.isAfterLast
    }

    override fun isFirst(): Boolean {
        return main.isFirst
    }

    override fun isLast(): Boolean {
        return main.isLast
    }

    override fun beforeFirst() {
        return main.beforeFirst()
    }

    override fun afterLast() {
        return main.afterLast()
    }

    override fun first(): Boolean {
        return main.first()
    }

    override fun last(): Boolean {
        return main.last()
    }

    override fun getRow(): Int {
        return main.row
    }

    override fun absolute(row: Int): Boolean {
        return main.absolute(row)
    }

    override fun relative(rows: Int): Boolean {
        return main.relative(rows)
    }

    override fun previous(): Boolean {
        return main.previous()
    }

    override fun setFetchDirection(direction: Int) {
        main.fetchDirection = direction
    }

    override fun getFetchDirection(): Int {
        return main.fetchDirection
    }

    override fun setFetchSize(rows: Int) {
        main.fetchSize = rows
    }

    override fun getFetchSize(): Int {
        return main.fetchSize
    }

    override fun getType(): Int {
        return main.type
    }

    override fun getConcurrency(): Int {
        return main.concurrency
    }

    override fun rowUpdated(): Boolean {
        return main.rowUpdated()
    }

    override fun rowInserted(): Boolean {
        return main.rowInserted()
    }

    override fun rowDeleted(): Boolean {
        return main.rowDeleted()
    }

    override fun updateNull(columnIndex: Int) {
        main.updateNull(columnIndex)
    }

    override fun updateNull(columnLabel: String?) {
        main.updateNull(columnLabel)
    }

    override fun updateBoolean(columnIndex: Int, x: Boolean) {
        main.updateBoolean(columnIndex, x)
    }

    override fun updateBoolean(columnLabel: String?, x: Boolean) {
        main.updateBoolean(columnLabel, x)
    }

    override fun updateByte(columnIndex: Int, x: Byte) {
        main.updateByte(columnIndex, x)
    }

    override fun updateByte(columnLabel: String?, x: Byte) {
        main.updateByte(columnLabel, x)
    }

    override fun updateShort(columnIndex: Int, x: Short) {
        main.updateShort(columnIndex, x)
    }

    override fun updateShort(columnLabel: String?, x: Short) {
        main.updateShort(columnLabel, x)
    }

    override fun updateInt(columnIndex: Int, x: Int) {
        main.updateInt(columnIndex, x)
    }

    override fun updateInt(columnLabel: String?, x: Int) {
        main.updateInt(columnLabel, x)
    }

    override fun updateLong(columnIndex: Int, x: Long) {
        main.updateLong(columnIndex, x)
    }

    override fun updateLong(columnLabel: String?, x: Long) {
        main.updateLong(columnLabel, x)
    }

    override fun updateFloat(columnIndex: Int, x: Float) {
        main.updateFloat(columnIndex, x)
    }

    override fun updateFloat(columnLabel: String?, x: Float) {
        main.updateFloat(columnLabel, x)
    }

    override fun updateDouble(columnIndex: Int, x: Double) {
        main.updateDouble(columnIndex, x)
    }

    override fun updateDouble(columnLabel: String?, x: Double) {
        main.updateDouble(columnLabel, x)
    }

    override fun updateBigDecimal(columnIndex: Int, x: BigDecimal?) {
        main.updateBigDecimal(columnIndex, x)
    }

    override fun updateBigDecimal(columnLabel: String?, x: BigDecimal?) {
        main.updateBigDecimal(columnLabel, x)
    }

    override fun updateString(columnIndex: Int, x: String?) {
        main.updateString(columnIndex, x)
    }

    override fun updateString(columnLabel: String?, x: String?) {
        main.updateString(columnLabel, x)
    }

    override fun updateBytes(columnIndex: Int, x: ByteArray?) {
        main.updateBytes(columnIndex, x)
    }

    override fun updateBytes(columnLabel: String?, x: ByteArray?) {
        main.updateBytes(columnLabel, x)
    }

    override fun updateDate(columnIndex: Int, x: Date?) {
        main.updateDate(columnIndex, x)
    }

    override fun updateDate(columnLabel: String?, x: Date?) {
        main.updateDate(columnLabel, x)
    }

    override fun updateTime(columnIndex: Int, x: Time?) {
        main.updateTime(columnIndex, x)
    }

    override fun updateTime(columnLabel: String?, x: Time?) {
        main.updateTime(columnLabel, x)
    }

    override fun updateTimestamp(columnIndex: Int, x: Timestamp?) {
        main.updateTimestamp(columnIndex, x)
    }

    override fun updateTimestamp(columnLabel: String?, x: Timestamp?) {
        main.updateTimestamp(columnLabel, x)
    }

    override fun updateAsciiStream(columnIndex: Int, x: InputStream?, length: Int) {
        main.updateAsciiStream(columnIndex, x, length)
    }

    override fun updateAsciiStream(columnLabel: String?, x: InputStream?, length: Int) {
        main.updateAsciiStream(columnLabel, x, length)
    }

    override fun updateAsciiStream(columnIndex: Int, x: InputStream?, length: Long) {
        main.updateAsciiStream(columnIndex, x, length)
    }

    override fun updateAsciiStream(columnLabel: String?, x: InputStream?, length: Long) {
        main.updateAsciiStream(columnLabel, x, length)
    }

    override fun updateAsciiStream(columnIndex: Int, x: InputStream?) {
        main.updateAsciiStream(columnIndex, x)
    }

    override fun updateAsciiStream(columnLabel: String?, x: InputStream?) {
        main.updateAsciiStream(columnLabel, x)
    }

    override fun updateBinaryStream(columnIndex: Int, x: InputStream?, length: Int) {
        main.updateBinaryStream(columnIndex, x, length)
    }

    override fun updateBinaryStream(columnLabel: String?, x: InputStream?, length: Int) {
        main.updateBinaryStream(columnLabel, x, length)
    }

    override fun updateBinaryStream(columnIndex: Int, x: InputStream?, length: Long) {
        main.updateBinaryStream(columnIndex, x, length)
    }

    override fun updateBinaryStream(columnLabel: String?, x: InputStream?, length: Long) {
        main.updateBinaryStream(columnLabel, x, length)
    }

    override fun updateBinaryStream(columnIndex: Int, x: InputStream?) {
        main.updateBinaryStream(columnIndex, x)
    }

    override fun updateBinaryStream(columnLabel: String?, x: InputStream?) {
        main.updateBinaryStream(columnLabel, x)
    }

    override fun updateCharacterStream(columnIndex: Int, x: Reader?, length: Int) {
        main.updateCharacterStream(columnIndex, x, length)
    }

    override fun updateCharacterStream(columnLabel: String?, reader: Reader?, length: Int) {
        main.updateCharacterStream(columnLabel, reader, length)
    }

    override fun updateCharacterStream(columnIndex: Int, x: Reader?, length: Long) {
        main.updateCharacterStream(columnIndex, x, length)
    }

    override fun updateCharacterStream(columnLabel: String?, reader: Reader?, length: Long) {
        main.updateCharacterStream(columnLabel, reader, length)
    }

    override fun updateCharacterStream(columnIndex: Int, x: Reader?) {
        main.updateCharacterStream(columnIndex, x)
    }

    override fun updateCharacterStream(columnLabel: String?, reader: Reader?) {
        main.updateCharacterStream(columnLabel, reader)
    }

    override fun updateObject(columnIndex: Int, x: Any?, scaleOrLength: Int) {
        main.updateObject(columnIndex, x, scaleOrLength)
    }

    override fun updateObject(columnIndex: Int, x: Any?) {
        main.updateObject(columnIndex, x)
    }

    override fun updateObject(columnLabel: String?, x: Any?, scaleOrLength: Int) {
        main.updateObject(columnLabel, x, scaleOrLength)
    }

    override fun updateObject(columnLabel: String?, x: Any?) {
        main.updateObject(columnLabel, x)
    }

    override fun insertRow() {
        main.insertRow()
    }

    override fun updateRow() {
        main.updateRow()
    }

    override fun deleteRow() {
        main.deleteRow()
    }

    override fun refreshRow() {
        main.refreshRow()
    }

    override fun cancelRowUpdates() {
        main.cancelRowUpdates()
    }

    override fun moveToInsertRow() {
        main.moveToInsertRow()
    }

    override fun moveToCurrentRow() {
        main.moveToCurrentRow()
    }

    override fun getStatement(): Statement {
        return main.statement
    }

    override fun getRef(columnIndex: Int): Ref {
        return main.getRef(columnIndex)
    }

    override fun getRef(columnLabel: String?): Ref {
        return main.getRef(columnLabel)
    }

    override fun getBlob(columnIndex: Int): Blob {
        return main.getBlob(columnIndex)
    }

    override fun getBlob(columnLabel: String?): Blob {
        return main.getBlob(columnLabel)
    }

    override fun getClob(columnIndex: Int): Clob {
        return main.getClob(columnIndex)
    }

    override fun getClob(columnLabel: String?): Clob {
        return main.getClob(columnLabel)
    }

    override fun getArray(columnIndex: Int): Array {
        return main.getArray(columnIndex)
    }

    override fun getArray(columnLabel: String?): Array {
        return main.getArray(columnLabel)
    }

    override fun getURL(columnIndex: Int): URL {
        return main.getURL(columnIndex)
    }

    override fun getURL(columnLabel: String?): URL {
        return main.getURL(columnLabel)
    }

    override fun updateRef(columnIndex: Int, x: Ref?) {
        return main.updateRef(columnIndex, x)
    }

    override fun updateRef(columnLabel: String?, x: Ref?) {
        return main.updateRef(columnLabel, x)
    }

    override fun updateBlob(columnIndex: Int, x: Blob?) {
        return main.updateBlob(columnIndex, x)
    }

    override fun updateBlob(columnLabel: String?, x: Blob?) {
        return main.updateBlob(columnLabel, x)
    }

    override fun updateBlob(columnIndex: Int, inputStream: InputStream?, length: Long) {
        main.updateBlob(columnIndex, inputStream, length)
    }

    override fun updateBlob(columnLabel: String?, inputStream: InputStream?, length: Long) {
        return main.updateBlob(columnLabel, inputStream, length)
    }

    override fun updateBlob(columnIndex: Int, inputStream: InputStream?) {
        return main.updateBlob(columnIndex, inputStream)
    }

    override fun updateBlob(columnLabel: String?, inputStream: InputStream?) {
        return main.updateBlob(columnLabel, inputStream)
    }

    override fun updateClob(columnIndex: Int, x: Clob?) {
        main.updateClob(columnIndex, x)
    }

    override fun updateClob(columnLabel: String?, x: Clob?) {
        main.updateClob(columnLabel, x)
    }

    override fun updateClob(columnIndex: Int, reader: Reader?, length: Long) {
        main.updateClob(columnIndex, reader, length)
    }

    override fun updateClob(columnLabel: String?, reader: Reader?, length: Long) {
        main.updateClob(columnLabel, reader, length)
    }

    override fun updateClob(columnIndex: Int, reader: Reader?) {
        main.updateClob(columnIndex, reader)
    }

    override fun updateClob(columnLabel: String?, reader: Reader?) {
        main.updateClob(columnLabel, reader)
    }

    override fun updateArray(columnIndex: Int, x: Array?) {
        main.updateArray(columnIndex, x)
    }

    override fun updateArray(columnLabel: String?, x: Array?) {
        main.updateArray(columnLabel, x)
    }

    override fun getRowId(columnIndex: Int): RowId {
        return main.getRowId(columnIndex)
    }

    override fun getRowId(columnLabel: String?): RowId {
        return main.getRowId(columnLabel)
    }

    override fun updateRowId(columnIndex: Int, x: RowId?) {
        main.updateRowId(columnIndex, x)
    }

    override fun updateRowId(columnLabel: String?, x: RowId?) {
        main.updateRowId(columnLabel, x)
    }

    override fun getHoldability(): Int {
        return main.holdability
    }

    override fun isClosed(): Boolean {
        return main.isClosed
    }

    override fun updateNString(columnIndex: Int, nString: String?) {
        main.updateNString(columnIndex, nString)
    }

    override fun updateNString(columnLabel: String?, nString: String?) {
        main.updateNString(columnLabel, nString)
    }

    override fun updateNClob(columnIndex: Int, nClob: NClob?) {
        main.updateNClob(columnIndex, nClob)
    }

    override fun updateNClob(columnLabel: String?, nClob: NClob?) {
        main.updateNClob(columnLabel, nClob)
    }

    override fun updateNClob(columnIndex: Int, reader: Reader?, length: Long) {
        main.updateNClob(columnIndex, reader, length)
    }

    override fun updateNClob(columnLabel: String?, reader: Reader?, length: Long) {
        main.updateNClob(columnLabel, reader, length)
    }

    override fun updateNClob(columnIndex: Int, reader: Reader?) {
        main.updateNClob(columnIndex, reader)
    }

    override fun updateNClob(columnLabel: String?, reader: Reader?) {
        main.updateNClob(columnLabel, reader)
    }

    override fun getNClob(columnIndex: Int): NClob {
        return main.getNClob(columnIndex)
    }

    override fun getNClob(columnLabel: String?): NClob {
        return main.getNClob(columnLabel)
    }

    override fun getSQLXML(columnIndex: Int): SQLXML {
        return main.getSQLXML(columnIndex)
    }

    override fun getSQLXML(columnLabel: String?): SQLXML {
        return main.getSQLXML(columnLabel)
    }

    override fun updateSQLXML(columnIndex: Int, xmlObject: SQLXML?) {
        return main.updateSQLXML(columnIndex, xmlObject)
    }

    override fun updateSQLXML(columnLabel: String?, xmlObject: SQLXML?) {
        return main.updateSQLXML(columnLabel, xmlObject)
    }

    override fun getNString(columnIndex: Int): String {
        return main.getNString(columnIndex)
    }

    override fun getNString(columnLabel: String?): String {
        return main.getNString(columnLabel)
    }

    override fun getNCharacterStream(columnIndex: Int): Reader {
        return main.getNCharacterStream(columnIndex)
    }

    override fun getNCharacterStream(columnLabel: String?): Reader {
        return main.getNCharacterStream(columnLabel)
    }

    override fun updateNCharacterStream(columnIndex: Int, x: Reader?, length: Long) {
        main.updateNCharacterStream(columnIndex, x, length)
    }

    override fun updateNCharacterStream(columnLabel: String?, reader: Reader?, length: Long) {
        main.updateNCharacterStream(columnLabel, reader, length)
    }

    override fun updateNCharacterStream(columnIndex: Int, x: Reader?) {
        main.updateNCharacterStream(columnIndex, x)
    }

    override fun updateNCharacterStream(columnLabel: String?, reader: Reader?) {
        main.updateNCharacterStream(columnLabel, reader)
    }
}