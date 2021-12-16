package by.dma.interview.deadlocks;

/**
 * Shortest Obscure Java Deadlock.
 */
public class ShortestDeadlock {

    static {
        try {
            var t = new Thread(() -> {});
            t.start();
            t.join();
        } catch (Exception e) {}
    }

    public static void main(String... args) {}
}
