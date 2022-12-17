package by.dma.synchronizers.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CyclicBarrier allows a number of threads to wait on each other.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        int amountOfThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(amountOfThreads);
        for (int i = 0; i < amountOfThreads; i++) {
            new Thread(new SimpleService("service" + (i + 1), getDelay(), barrier)).start();
        }

        System.out.println(Thread.currentThread().getName() + " has finished");
    }

    private static int getDelay() {
        return 500 + ThreadLocalRandom.current().nextInt(500);
    }

}

class SimpleService extends Thread {

    private final String name;
    private final int duration;
    private final CyclicBarrier barrier;

    public SimpleService(String name, int duration, CyclicBarrier barrier) {
        super(name);
        this.name = name;
        this.duration = duration;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.println(name + " is UP. Waiting for barrier.");
            barrier.await();
            System.out.println(name + " continue running.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

