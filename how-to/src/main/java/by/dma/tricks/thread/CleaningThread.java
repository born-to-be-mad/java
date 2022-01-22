package by.dma.tricks.thread;

import java.lang.ref.WeakReference;

/**
 * Cleaning via ThreadLocals.
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
        WeakReference[] table = getThreadLocalReferences(thread);
        if (table != null) {
            for (WeakReference reference : table) {
                Object key = reference != null ? reference.get() : null;
                System.out.println("cleaning...");
            }
        }
    }

    private static WeakReference[] getThreadLocalReferences(Thread thread) {
        // TODO get thread locals
        return null;
    }
}
