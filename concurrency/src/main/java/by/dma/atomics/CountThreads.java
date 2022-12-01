package by.dma.atomics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Creates thread in a loop and then parks it, which means the thread gets disabled for further use, but it certainly does
 * the system call and allocates memory. It keeps creating threads until it cannot create anymore, and then it throws
 * an exception.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class CountThreads {

    public static void main(String[] args) {
        var counter = new AtomicInteger();
        while (true) {
            // Thread.startVirtualThread: project Loomm 4+ millions threads(run with the option `--source 18
            // --enable-preview`)
            new Thread(() -> {
                int count = counter.incrementAndGet();
                System.out.println("thread count = " + count);
                LockSupport.park();
            }).start();
        }
    }
}
