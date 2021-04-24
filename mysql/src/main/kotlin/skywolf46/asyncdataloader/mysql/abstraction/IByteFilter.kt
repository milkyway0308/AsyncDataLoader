package skywolf46.asyncdataloader.mysql.abstraction

interface IByteFilter {
    fun filter(arr: ByteArray, statement: IStatementInput) : ByteArray

    fun filterSync(arr: ByteArray): ByteArray

    fun filter(arr: ByteArray, unit: ByteArray.() -> Unit)
}