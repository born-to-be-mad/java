package by.dma.tricks.exception;

import java.util.Map;

/**
 * Demo for {@link StackTrace}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class StackTraceDemo implements AutoCloseable {
    private transient volatile StackTrace createdHere = new StackTrace("Created here");
    private transient volatile StackTrace closedHere;
    private int closed;

    public static void main(String[] args) {
        new StackTraceDemo().dumpThreads(new Exception());
    }

    // Report where threads are still running
    private void dumpThreads(Exception ae) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
            Thread thread = threadEntry.getKey();
            StringBuilder stringBuilder = new StringBuilder()
                    .append("\n========= THREAD DUMP =========\n")
                    .append("Thread is still running")
                    .append(thread);
            //Jvm.trimStackTrace(stringBuilder, threadEntry.getValue())
            //StackTrace stackTrace = ThreadDump.createdHereFor(thread);
            StackTrace stackTrace = StackTrace.forThread(thread);
            StackTrace st = new StackTrace(thread.toString(), stackTrace);
            st.setStackTrace(threadEntry.getValue());
            ae.addSuppressed(st);
        }
    }

    private void resourceLeak() {
        throw new IllegalStateException("Resource leak", createdHere);
    }

    public void throwExceptionIfClosed() throws IllegalStateException {
        if (closed != 0) {
            throw new IllegalStateException("Closed", closedHere);
        }
    }

    @Override
    public void close() throws Exception {
        closedHere = new StackTrace("Closed here");
    }
}
