package by.dma.tricks.exception;

/**
 * Container holds the stack trace. It is useful for monitoring and reporting.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class StackTrace extends Throwable {
    public StackTrace() {
        this("stack trace");
    }

    public StackTrace(String message) {
        this(message, null);
    }

    public StackTrace(String message, Throwable cause) {
        super(message + " on " + Thread.currentThread().getName(), cause);
    }

    public static StackTrace forThread(Thread thread) {
        StackTrace stackTrace = new StackTrace(thread.toString());
        stackTrace.setStackTrace(thread.getStackTrace());
        return stackTrace;
    }
}
