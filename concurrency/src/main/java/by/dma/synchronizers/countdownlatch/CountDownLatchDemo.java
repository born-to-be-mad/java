package by.dma.synchronizers.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * CountDownLatch is a synchronizer which allows one Thread to wait for one or more Threads before starting processing.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        int amountOfThreads = 5;
        CountDownLatch latch = new CountDownLatch(amountOfThreads);
        for (int i = 0; i < amountOfThreads; i++) {
            new Thread(new SimpleService("service" + (i + 1), getDelay(), latch)).start();
        }

        try {
            latch.await();
            System.out.println("All services are UP&RUNNING. Starting main application...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getDelay() {
        return 500 + ThreadLocalRandom.current().nextInt(500);
    }

}

class SimpleService implements Runnable {

    private final String name;
    private final int delay;
    private final CountDownLatch latch;

    public SimpleService(String name, int delay, CountDownLatch latch) {
        this.name = name;
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is UP");
        latch.countDown();
    }
}
