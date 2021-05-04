package skywolf46.asyncdataloader.mysql.abstraction

interface IBatchAcceptor {
    fun accept(vararg any: Any) : IBatchAcceptor

}