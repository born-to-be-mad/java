package by.dma.challenge.java6;

/**
 * Quiz on Polymorphism about method overridingю
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class TestPoly implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new TestPoly());
        t.start();
    }

    public void run() throws ArithmeticException {
        int x = 0;
        System.out.println(x / 10);
    }
}
