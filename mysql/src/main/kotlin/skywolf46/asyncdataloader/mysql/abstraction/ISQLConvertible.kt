package skywolf46.asyncdataloader.mysql.abstraction

// TODO IMPORTANT: Make it reusable

interface ISQLConvertible {
    fun getSQLString() : String

    fun getSQLObjects(): List<Any>
}