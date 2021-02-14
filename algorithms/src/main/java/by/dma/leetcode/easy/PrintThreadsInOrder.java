package by.dma.leetcode.easy;

import java.util.concurrent.Semaphore;

/**
 * There are three threads being fired asynchronously.
 * The same instance of this class will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure
 * that second() is executed after first(), and third() is executed after second().
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class PrintThreadsInOrder {
    private final Semaphore firstFinished = new Semaphore(0);
    private final Semaphore secondFinished = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstFinished.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstFinished.acquire();
        printSecond.run();
        secondFinished.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondFinished.acquire();
        printThird.run();
    }

    public static void main(String[] args) {
        PrintThreadsInOrder printThreadsInOrder = new PrintThreadsInOrder();
        new Thread(() -> {
            try {
                printThreadsInOrder.third(() -> System.out.println("third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                printThreadsInOrder.second(() -> System.out.println("second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                printThreadsInOrder.first(() -> System.out.println("first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
