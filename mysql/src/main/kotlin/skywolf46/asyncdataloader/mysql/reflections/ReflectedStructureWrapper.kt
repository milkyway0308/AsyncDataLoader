package skywolf46.asyncdataloader.mysql.reflections

import skywolf46.asyncdataloader.mysql.abstraction.ISQLStructure
import skywolf46.asyncdataloader.mysql.abstraction.IStatementInput
import skywolf46.asyncdataloader.mysql.abstraction.IStatementOutput
import skywolf46.asyncdataloader.mysql.annotations.SQLExclude
import java.lang.reflect.Field
import java.lang.reflect.Modifier

class ReflectedStructureWrapper<T : Any>(private val cls: Class<T>, private val mark: String) : ISQLStructure<T> {
    init {
        // Scan annotations.
        val fields = mutableListOf<Field>()
        for (fx in cls.fields) {
            if (!Modifier.isStatic(fx.modifiers)) {
                val annot = fx.getAnnotation(SQLExclude::class.java)
                if (annot != null) {
                    try {
                        fx.isAccessible = true
                        fields.add(fx)
                    } catch (_: Exception) {
                    }
                    continue
                }
            }
        }
    }

    override fun getMark(): String {
        return mark
    }

    override fun construct(table: IStatementOutput): T {
        TODO("Not yet implemented")
    }

    override fun deconstruct(data: T, statement: IStatementInput) {
        TODO("Not yet implemented")
    }
}