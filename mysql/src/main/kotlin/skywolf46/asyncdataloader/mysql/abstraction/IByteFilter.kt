package skywolf46.asyncdataloader.mysql.abstraction

interface IByteFilter {
    fun filterEncode(arr: ByteArray, statement: IStatementInput) : ByteArray
    fun filterEncodeSync(arr: ByteArray, statement: IStatementInput) : ByteArray

    fun filterDecodeSync(arr: ByteArray): ByteArray

    fun filterDecode(arr: ByteArray, unit: ByteArray.() -> Unit)
}