package skywolf46.asyncdataloader.mysql.abstraction

import skywolf46.asyncdataloader.mysql.util.SQLTable
import java.sql.PreparedStatement

abstract class AbstractQueryable(val table: SQLTable) : ISQLConvertible {
    internal val list = mutableListOf<PreparedStatement.(Int) -> Unit>()


}