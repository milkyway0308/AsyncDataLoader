package skywolf46.asyncdataloader.mysql.annotations

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class SQLMultiTable(val value: String, vararg val foreignPrimary: String)