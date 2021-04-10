package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.data.SQLInsertion
import skywolf46.asyncdataloader.mysql.data.SQLResult

abstract class AbstractSQLType<X : Any> {
    companion object {
        private val data = mutableMapOf<Class<out Any>, AbstractSQLType<out Any>>()

        fun <T : Any> register(cls: Class<T>, type: AbstractSQLType<T>) {
            data[cls] = type
        }

        fun <T : Any> get(cls: Class<T>): AbstractSQLType<T>? {
            return data[cls] as AbstractSQLType<T>?
        }
    }

    abstract fun assemble(result: SQLResult): X

    abstract fun disassemble(insertion: SQLInsertion, data: X)


    abstract fun getSQLRequirement(): Array<AbstractSQLType<out Any>>

    abstract fun getSQLTypeString(): String

    abstract fun getTypeName(): String

    open fun asType(data: X): AbstractSQLType<X> {
        return this
    }


    fun toSQLTypeString(currentPrefix: String): String {
        // If requirement is Zero, just get SQL Type string.
        val req = getSQLRequirement()
        if (req.isEmpty())
            return "$currentPrefix ${getSQLTypeString()}"
        // If not, parse all type string.
        val builder = StringBuilder()
        val typeString = "${currentPrefix}${getTypeName()}"
        req.forEachIndexed { index, x ->
            builder.append("${x.toSQLTypeString("$typeString$index")}, ")
        }
        return builder.toString().let {
            return@let it.substring(0, it.length - 2)
        }
    }

    fun register(vararg cls: Class<out Any>?) {
        for (x in cls) {
            if (x == null)
                continue
            Companion.register(x as Class<Any>, this as AbstractSQLType<Any>)
        }
    }

}