package by.dma.synchronizers;

import java.util.LinkedList;
import java.util.Random;

/**
 * Low-level synchronization via wait/notify.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class LowLevelSynchronizationDemo {
    public static void main(String[] args) {
        final Processor processor = new Processor();
        Thread produce = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consume = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        produce.start();
        consume.start();

        try {
            produce.join();
            consume.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("program finished!");
    }
}

class Processor {
    private static final int LIMIT = 10;

    private final LinkedList<Integer> list = new LinkedList<>();
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
            Thread.sleep(500);
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.printf("List size = %d;", list.size());
                Integer removedValue = list.removeFirst();
                System.out.printf("\t removed first value = %d%n", removedValue);
                lock.notify();
            }
            Thread.sleep(200 + random.nextInt(800));
        }
    }
}
