package by.dma.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * See {@code CyclicBarrier}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CyclicBarrierCounter {
    private int count;

    public CyclicBarrierCounter(int count) {
        this.count = count;
    }

    public boolean callTwiceInSameThread() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrier.await();
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("cyclicBarrier.getNumberWaiting()" + cyclicBarrier.getNumberWaiting());
        return cyclicBarrier.isBroken();
    }

}
