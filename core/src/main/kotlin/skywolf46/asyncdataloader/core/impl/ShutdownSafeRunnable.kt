package skywolf46.asyncdataloader.core.impl

import java.util.concurrent.atomic.AtomicBoolean

class ShutdownSafeRunnable(
    private val isShutdowning: AtomicBoolean,
    private val runnable: Runnable,
    private val shutdown: Runnable,
) : Runnable {
    override fun run() {
        if (isShutdowning.get())
            runShutdown()
        else
            runnable.run()
    }

    fun runShutdown() {
        shutdown.run()
    }

}