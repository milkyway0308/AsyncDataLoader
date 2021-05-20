package skywolf46.asyncdataloader.core.util

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.util.concurrent.ThreadFactoryBuilder
import java.util.concurrent.*

/**
 * Thank you, StackOverFlow!
 */

/**
 * Overrides shutdown() to run outstanding tasks immediately.
 *
 * @author Gili Tzabari
 */
class RunOnShutdownScheduledExecutorService(delegate: ScheduledExecutorService) :
    AbstractExecutorService(), ScheduledExecutorService {
    private val delegate: ScheduledExecutorService
    private var scheduledThreadPoolExecutor: ScheduledThreadPoolExecutor? = null
    private val immediateService: ExecutorService
    private val tasks: ConcurrentMap<Future<*>, Callable<*>> = Maps.newConcurrentMap()
    override fun isShutdown(): Boolean {
        return delegate.isShutdown
    }

    override fun isTerminated(): Boolean {
        return delegate.isTerminated
    }

    @Throws(InterruptedException::class)
    override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
        val before = System.nanoTime()
        if (!delegate.awaitTermination(timeout, unit)) return false
        val after = System.nanoTime()
        val timeLeft = timeout - unit.convert(after - before, TimeUnit.NANOSECONDS)
        return immediateService!!.awaitTermination(timeLeft, unit)
    }

    override fun execute(command: Runnable) {
        delegate.execute(command)
    }

    override fun schedule(command: Runnable, delay: Long, unit: TimeUnit): ScheduledFuture<*> {
        val decorated: CleaningRunnable = CleaningRunnable(command)
        val future: ScheduledFuture<*> = delegate.schedule(decorated, delay, unit)
        decorated.setFuture(future)
        tasks[future] = Executors.callable(command)
        return CleaningScheduledFuture(future)
    }

    override fun <V> schedule(callable: Callable<V>, delay: Long, unit: TimeUnit): ScheduledFuture<V> {
        val decorated: CallableWithFuture<V> = CallableWithFuture<V>(callable)
        val future: ScheduledFuture<V> = delegate.schedule(decorated, delay, unit)
        decorated.setFuture(future)
        tasks[future] = callable
        return CleaningScheduledFuture(future)
    }

    override fun scheduleAtFixedRate(
        command: Runnable, initialDelay: Long, period: Long,
        unit: TimeUnit,
    ): ScheduledFuture<*> {
        val decorated = CleaningRunnable(command)
        val future: ScheduledFuture<*> = delegate.scheduleAtFixedRate(decorated, initialDelay, period, unit)
        decorated.setFuture(future)
        tasks[future] = Executors.callable(command)
        return CleaningScheduledFuture(future)
    }

    override fun scheduleWithFixedDelay(
        command: Runnable, initialDelay: Long, delay: Long,
        unit: TimeUnit,
    ): ScheduledFuture<*> {
        val decorated = CleaningRunnable(command)
        val future: ScheduledFuture<*> = delegate.scheduleWithFixedDelay(decorated, initialDelay, delay, unit)
        decorated.setFuture(future)
        tasks[future] = Executors.callable(command)
        return CleaningScheduledFuture(future)
    }

    @Synchronized
    override fun shutdown() {
        if (delegate.isShutdown) return
        scheduledThreadPoolExecutor?.executeExistingDelayedTasksAfterShutdownPolicy = false
        delegate.shutdown()
        // Users will not be able to cancel() Futures past this point so we're guaranteed that
        // "tasks" will not be modified.
        val outstandingTasks: MutableList<Callable<*>> = Lists.newArrayList()
        for ((future, task) in tasks) {
            if (future.isDone() && future.isCancelled()) {
                // Task called by the underlying executor, not the user. See CleaningScheduledFuture.
                outstandingTasks.add(task)
            }
        }
        tasks.clear()
        if (outstandingTasks.isEmpty()) {
            immediateService!!.shutdown()
            return
        }
        immediateService!!.submit<Void> {
            delegate.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)

            // Execute outstanding tasks only after the delegate executor finishes shutting down
            for (task in outstandingTasks) immediateService.submit(task)
            immediateService.shutdown()
            null
        }
    }

    override fun shutdownNow(): List<Runnable> {
        return delegate.shutdownNow()
    }

    /**
     * A Runnable that removes its future when running.
     */
    private inner class CleaningRunnable(delegate: Runnable) : Runnable {
        private val delegate: Runnable
        private var future: Future<*>? = null

        /**
         * Associates a Future with the runnable.
         *
         * @param future a future
         */
        fun setFuture(future: Future<*>) {
            this.future = future
        }

        override fun run() {
            tasks.remove(future)
            delegate.run()
        }

        /**
         * Creates a new RunnableWithFuture.
         *
         * @param delegate the Runnable to delegate to
         * @throws NullPointerException if delegate is null
         */
        init {
            this.delegate = delegate
        }
    }

    /**
     * A Callable that removes its future when running.
     */
    private inner class CallableWithFuture<V>(private val delegate: Callable<V>) : Callable<V> {
        private var future: Future<V>? = null

        /**
         * Associates a Future with the runnable.
         *
         * @param future a future
         */
        fun setFuture(future: Future<V>) {
            this.future = future
        }

        @Throws(Exception::class)
        override fun call(): V {
            tasks.remove(future)
            return delegate.call()
        }

    }

    /**
     * A ScheduledFuture that removes its future when canceling.
     *
     * This allows us to differentiate between tasks canceled by the user and the underlying
     * executor. Tasks canceled by the user are removed from "tasks".
     *
     * @param <V> The result type returned by this Future
    </V> */
    private inner class CleaningScheduledFuture<V>(delegate: ScheduledFuture<V>) :
        ScheduledFuture<V> {
        private val delegate: ScheduledFuture<V>
        override fun getDelay(unit: TimeUnit?): Long {
            return delegate.getDelay(unit)
        }

        override operator fun compareTo(o: Delayed?): Int {
            return delegate.compareTo(o)
        }

        override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
            val result: Boolean = delegate.cancel(mayInterruptIfRunning)
            if (result) {
                // Tasks canceled by users are removed from "tasks"
                tasks.remove(delegate)
            }
            return result
        }

        override fun isCancelled(): Boolean {
            return delegate.isCancelled()
        }

        override fun isDone(): Boolean {
            return delegate.isDone()
        }

        @Throws(InterruptedException::class, ExecutionException::class)
        override fun get(): V {
            return delegate.get()
        }

        @Throws(InterruptedException::class, ExecutionException::class, TimeoutException::class)
        override operator fun get(timeout: Long, unit: TimeUnit?): V {
            return delegate.get(timeout, unit)
        }

        /**
         * Creates a new MyScheduledFuture.
         *
         * @param delegate the future to delegate to
         * @throws NullPointerException if delegate is null
         */
        init {
            this.delegate = delegate
        }
    }

    /**
     * Creates a new RunOnShutdownScheduledExecutorService.
     *
     * @param delegate the executor to delegate to
     */
    init {
        this.delegate = delegate
        if (delegate is ScheduledThreadPoolExecutor) {
            scheduledThreadPoolExecutor = delegate
            immediateService = Executors.newFixedThreadPool(scheduledThreadPoolExecutor!!.corePoolSize,
                scheduledThreadPoolExecutor!!.threadFactory)
        } else {
            scheduledThreadPoolExecutor = null
            immediateService = Executors.newSingleThreadExecutor(ThreadFactoryBuilder().setNameFormat(
                RunOnShutdownScheduledExecutorService::class.java.name + "-%d").build())
        }
    }
}