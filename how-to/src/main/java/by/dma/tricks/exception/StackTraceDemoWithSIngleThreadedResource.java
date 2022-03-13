package by.dma.tricks.exception;

/**
 * Detects When a Single-Threaded Resource is Accessed Concurrently Between Threads.
 *
 * @implNote The resource was used by two threads with their names, however, you can also see where in the stack they
 * were used to determine the possible cause.
 */
public class StackTraceDemoWithSIngleThreadedResource {

    static class SingleThreadedResource {

        private StackTrace usedHere;
        private Thread usedByThread;

        public void use() {
            checkMultithreadedAccess();
        }

        private void checkMultithreadedAccess() {
            if (usedHere == null || usedByThread == null) {
                usedHere = new StackTrace("First used here");
                usedByThread = Thread.currentThread();
            } else if (Thread.currentThread() != usedByThread) {
                throw new IllegalStateException(
                        "Used two threads " + Thread.currentThread() + " and " + usedByThread,
                        usedHere);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SingleThreadedResource str = new SingleThreadedResource();
        final Thread thread = new Thread(() -> str.use(), "Resource user");
        thread.start();
        thread.join();

        str.use();
    }
}
