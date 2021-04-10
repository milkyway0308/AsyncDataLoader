package skywolf46.asyncdataloader.mysql.data

import skywolf46.asyncdataloader.mysql.abstraction.AbstractSQLType

class SQLObjectiveDescription<T : Any>(
    val name: String,
    val target: AbstractSQLType<T>,
    var isPrimary: Boolean = false,
    var isNullable: Boolean = false,
    var default: T? = null
)