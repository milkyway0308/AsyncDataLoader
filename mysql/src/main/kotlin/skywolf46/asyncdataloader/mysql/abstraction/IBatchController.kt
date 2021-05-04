package skywolf46.asyncdataloader.mysql.abstraction

interface IBatchController : IBatchAcceptor {
    fun finalizeBatch()
}