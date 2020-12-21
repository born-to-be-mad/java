package by.dma.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer-Consumer Demo via BlockingQueue.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class BlockingQueueDemo {
    private static final BlockingQueue<Integer> QUEUE = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    private static void produce() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            QUEUE.put(random.nextInt(100));
        }
    }

    private static void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(10);
            if (random.nextInt(10) == 0) {
                Integer value = QUEUE.take();
                System.out.printf("Queue size: %d\tConsumed value %d%n", QUEUE.size(), value);
            }
        }
    }
}
