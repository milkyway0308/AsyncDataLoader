package skywolf46.asyncdataloader.mysql.abstraction

import java.sql.PreparedStatement
import kotlin.reflect.KClass

abstract class AbstractSQLStructure<T : Any> constructor(vararg val queryString: String) {
    companion object {
        private val map = mutableMapOf<Any, AbstractSQLStructure<out Any>>()

        fun of(cls: Class<out Any>) = map[cls.kotlin]
        fun register(cls: KClass<Any>, struct: AbstractSQLStructure<Any>) {
            map[cls] = struct
        }
    }

    abstract fun getMark(): String

    protected abstract fun deconstruct(baseName: String, data: T): List<Any>

    protected abstract fun construct(baseName: String, S): Any

    abstract fun requires(): List<AbstractSQLStructure<Any>>

    open fun isProxy(): Boolean {
        return false
    }

    open fun proxy(invoker: IObjectInvoker<PreparedStatement.(Int) -> Unit>, data: T) {
        throw IllegalStateException("Class ${data::class.java} is not proxy instance")
    }
}