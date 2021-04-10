package skywolf46.asyncdataloader.core.util

object ClassReflectUtil {
    inline fun iterateParent(cls: Class<out Any>, includeAny: Boolean = false, iterator: Class<out Any>.() -> Unit){
        var next = cls
        while (next != Any::class.java) {
            iterator(next)
            next = next.superclass
        }
        if (includeAny)
            iterator(next)
    }
}