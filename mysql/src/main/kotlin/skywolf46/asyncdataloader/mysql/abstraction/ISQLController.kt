package skywolf46.asyncdataloader.mysql.abstraction

interface ISQLController : ISQLCompare {
    fun finalizeBatch()
}