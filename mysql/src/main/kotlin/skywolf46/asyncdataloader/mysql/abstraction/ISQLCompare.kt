package skywolf46.asyncdataloader.mysql.abstraction

interface ISQLCompare {
    fun accept(vararg pair: Pair<String, Any>) : ISQLCompare
}