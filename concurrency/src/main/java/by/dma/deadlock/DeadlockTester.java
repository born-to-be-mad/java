package by.dma.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Checks deadlocks.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DeadlockTester {
    public static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

    public void checkThatNoDeadLocks(Runnable runnable) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("testDeadlockGroup");
        Thread runner = new Thread(threadGroup, runnable, "deadlockChecker");
        runner.start();

        while (runner.isAlive()) {
            runner.join(100);

            Thread[] threads = new Thread[threadGroup.activeCount()];
            threadGroup.enumerate(threads);

            long[] deadlockedThreadIds = THREAD_MX_BEAN.findDeadlockedThreads();
            if (deadlockedThreadIds != null) {
                for (long deadlockedThreadId : deadlockedThreadIds) {
                    for (Thread thread : threads) {
                        if (thread.getId() == deadlockedThreadId) {
                            throw new DeadlockError(thread);
                        }
                    }
                }

            }
        }
    }
}
