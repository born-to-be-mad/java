package by.dma.deadlock;

/**
 * Deadlock Exception.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DeadlockError extends InterruptedException {
    public DeadlockError(Thread thread) {
        super(thread.getName());
        String msg = String.format("Deadlock identified on id=%s, name=%s [%s]%n",
                                   thread.getId(), thread.getName(), thread.getThreadGroup());
        System.out.println(msg);
    }
}
