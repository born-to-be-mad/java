package by.dma.notify;

import static by.dma.notify.MyThread.AMOUNT;

/**
 * Demo for {@link Thread#wait} and {@link Thread#notify}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Sum of %s random values: %.2f%n", AMOUNT, thread.getSum());
    }

}

class MyThread extends Thread {
    static final long AMOUNT = 1_000_000L;
    private double sum;

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < AMOUNT; i++) {
                sum += Math.random();
            }
            notify();
        }
    }

    public double getSum() {
        return sum;
    }
}
