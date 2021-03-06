package skywolf46.asyncdataloader.core.impl

import org.bukkit.Bukkit
import skywolf46.asyncdataloader.core.AsyncDataLoader
import skywolf46.asyncdataloader.core.abstraction.AbstractTaskReadyProvider
import skywolf46.asyncdataloader.core.abstraction.data.DataCounter
import skywolf46.asyncdataloader.core.abstraction.loader.AbstractDataLoader

@Deprecated("Unstable shutdown")
object MinecraftTask : AbstractTaskReadyProvider() {
    override fun doAsync(snapshot: (Boolean) -> Unit) {
        Bukkit.getScheduler().runTaskAsynchronously(AsyncDataLoader.inst) {
            snapshot(false)
        }
    }

    override fun registerTask(provider: DataCounter<AbstractDataLoader<Any>>) {
        provider.cancelTask()
        val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(
            AsyncDataLoader.inst, {
                if (provider.isExpired()) {
                    provider.currentTask = null
                    val snapshotReady = provider.data.snapshot()
                    provider.data.task = null
                    doAsync {
                        snapshotReady.trigger(false)
                    }
                }
            }, 20L, 20L
        )
        provider.currentTask = {
            Bukkit.getScheduler().cancelTask(id)
        }
    }

    override fun finalizeProvider() {

    }
}