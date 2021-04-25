package skywolf46.asyncdataloader.mysql.abstraction

interface ISQLStructure<T : Any> {

    fun getMark(): String

    fun getConstructor(): String = ""

    fun construct(table: IStatementOutput): T

    // TODO change from selection
    fun deconstruct(data: T, statement: IStatementInput)

    fun requires(): List<ISQLStructure<*>> = listOf()

    fun isProxy() = requires().isEmpty()

    fun uncast(): ISQLStructure<Any> {
        return this as ISQLStructure<Any>
    }

    fun toSQLTableString(baseName: String, additional: String): Pair<String, Int> {
        return toSQLTableString(baseName, additional, -1)
    }

    // as SQLTableString
    fun toSQLTableString(baseName: String, additional: String, index: Int): Pair<String, Int> {
        val base = if (index == -1) baseName else "${baseName}${getMark()}"
        if (requires().isEmpty())
            return "$baseName${if (index == -1) "" else getMark()}${if (getConstructor().isEmpty()) "" else " ${getConstructor()}"} ${additional}" to 1
        var total = 0
        val sb = StringBuilder()
        var indx = 0
        for (x in requires()) {
            val xl = x.toSQLTableString("$base$indx", additional, indx++)
            sb.append(xl.first)
            sb.append(", ")
            total += xl.second
        }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString() to total
    }

    fun toSQLSelectString(baseName: String): Pair<String, Int> {
        return toSQLSelectString(baseName, -1)
    }

    fun toSQLSelectString(baseName: String, index: Int): Pair<String, Int> {
        val base = if (index == -1) baseName else "${baseName}${getMark()}"
        if (requires().isEmpty())
            return "$baseName${getMark()}" to 1
        var total = 0
        val sb = StringBuilder()
        var indx = 0
        for (x in requires()) {
            val xl = x.toSQLSelectString("$base$indx", indx++)
            sb.append(xl.first)
            sb.append(", ")
            total += xl.second
        }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString() to total
    }

    fun toSQLEqualString(baseName: String): Pair<String, Int> {
        println("Base: $baseName")
        println("ToSQL: ${toSQLCompareString(baseName, "= ?", -1)}")
        return toSQLCompareString(baseName, "= ?", -1)
    }

    // as SQLTableString
    fun toSQLCompareString(baseName: String, selector: String, index: Int): Pair<String, Int> {
        if (requires().isEmpty())
            return if (index == -1)
                "$baseName $selector" to 1
            else
                "$baseName${getMark()} $selector" to 1
        val base = if (index == -1) baseName else "${baseName}${getMark()}"
        var total = 0
        val sb = StringBuilder()
        for ((indx, x) in requires().withIndex()) {
            val xl = x.toSQLCompareString("$base$indx", selector, indx)
            sb.append(xl.first).append(" $selector and ")
            total += xl.second
        }
        sb.delete(sb.length - 5, sb.length)
        return sb.toString() to total
    }


    /**
     * Iterate, and give all objective count.
     */
    fun count(): Int {
        if (isProxy())
            return 1
        return requires().map { x -> x.count() }.reduce { acc, i -> acc + i }
    }
}