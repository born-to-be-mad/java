package by.dma.tricks.cleaning;

/**
 * Cleaning ThreadLocals.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class CleaningThread extends Thread {
    public CleaningThread(Runnable target) {
        super(target);
    }

    public CleaningThread(Runnable target, String name) {
        super(target, name);
    }

    @Override
    public void run() {
        try {
            super.run();
        } finally {
            performCleanup(this);
        }
    }

    public static void performCleanup(Thread thread) {
        System.out.println("Cleaning threadLocals/table");
    }
}
