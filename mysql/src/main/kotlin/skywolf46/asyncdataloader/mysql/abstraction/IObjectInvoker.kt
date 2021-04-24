package skywolf46.asyncdataloader.mysql.abstraction

interface IObjectInvoker<T: Any> {
    fun invoke(data: T)
}