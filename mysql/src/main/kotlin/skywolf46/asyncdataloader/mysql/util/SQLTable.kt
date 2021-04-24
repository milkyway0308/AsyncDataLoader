package skywolf46.asyncdataloader.mysql.util

import skywolf46.asyncdataloader.mysql.abstraction.ISQLConvertible
import java.sql.Connection

class SQLTable(val connection: Connection?, val tableName: String) {

    @Deprecated("This constructor only allowed for testing")
    internal constructor(str: String) : this(null, str)

    fun prepare(sql: ISQLConvertible) = connection?.prepareStatement(sql.toSQLString())


}

fun Connection.from(tableName: String) = SQLTable(this, tableName)