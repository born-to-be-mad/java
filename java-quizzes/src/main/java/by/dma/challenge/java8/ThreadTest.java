package by.dma.challenge.java8;

/**
 * TODO
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestThread());
        System.out.println("ThreadTest.START");
        thread.start();
        thread.join();
        System.out.println("ThreadTest.END");
    }
}

class TestThread implements Runnable {
    @Override
    public void run() {
        System.out.println("TestThread.run");
    }


    public void join() {
        System.out.println("TestThread.join");
    }
}
