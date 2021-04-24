package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.util.SQLResult

interface ISQLStructure<T : Any> {

    fun getMark(): String

    fun getConstructor(): String = ""

    fun construct(baseName: String, table: SQLResult): T

    // TODO change from selection
    fun deconstruct(data: T, statement: IStatementInput)

    fun requires(): List<ISQLStructure<*>> = listOf()

    fun uncast(): ISQLStructure<Any> {
        return this as ISQLStructure<Any>
    }

    fun toSQLTableString(baseName: String): Pair<String, Int> {
        return toSQLTableString(baseName, -1)
    }

    // as SQLTableString
    fun toSQLTableString(baseName: String, index: Int): Pair<String, Int> {
        val base = if (index == -1) baseName else "${baseName}${getMark()}"
        if (requires().isEmpty())
            return "$baseName${getMark()}" to 1
        var total = 0
        val sb = StringBuilder()
        var indx = 0
        for (x in requires()) {
            val xl = x.toSQLTableString("$base$indx", indx++)
            sb.append(xl.first)
            if (!x.getConstructor().isEmpty())
                sb.append(" ${x.getConstructor()}")
            sb.append(", ")
            total += xl.second
        }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString() to total
    }

    fun toSQLEqualString(baseName: String): Pair<String, Int> {
        return toSQLCompareString(baseName, "= ?", -1)
    }

    // as SQLTableString
    fun toSQLCompareString(baseName: String, selector: String, index: Int): Pair<String, Int> {
        val base = if (index == -1) baseName else "${baseName}${getMark()}"
        if (requires().isEmpty())
            return "$baseName${getMark()}" to 1
        var total = 0
        val sb = StringBuilder()
        for ((indx, x) in requires().withIndex()) {
            val xl = x.toSQLCompareString("$base$indx", selector, indx)
            sb.append(xl.first).append(" $selector and ")
            total += xl.second
        }
        sb.delete(sb.length - (6 + selector.length), sb.length)
        return sb.toString() to total
    }
}