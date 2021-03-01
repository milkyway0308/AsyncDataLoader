package skywolf46.adl

import org.bukkit.plugin.java.JavaPlugin
import skywolf46.adl.abstraction.AbstractDataTask
import skywolf46.adl.abstraction.AbstractDataTaskFactory

class AsyncDataLoader : JavaPlugin() {
    companion object {
        var inst: AsyncDataLoader? = null
            private set
        private val data: MutableMap<Class<out AbstractDataTask>, AbstractDataTaskFactory<*>> = HashMap()
    }

    override fun onEnable() {
        inst = this
    }

    fun of(cls: Class<out AbstractDataTask>) = data[cls]

    fun register(fac: AbstractDataTaskFactory<*>) = data.put(fac.getTargetClass(), fac)

}