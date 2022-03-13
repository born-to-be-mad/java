package by.dma.tricks.exception;

/**
 * Container that captures the stack trace.
 * It is useful for monitoring and reporting.
 *
 * @author dzmitry.marudau
 * @implNote Creating a StackTrace has a significant impact on the thread and possibly the JVM. However, it is easily
 * turned off using a control flag such as a system property and replaced with a null value.
 * <pre>{@code
 *     createdHere = Jvm.isResourceTracing()
 *               ? new StackTrace(getClass().getName() + " created here")
 *               : null;
 * }</pre>
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
        if (thread == null) {return null;}
        StackTrace stackTrace = new StackTrace(thread.toString());
        StackTraceElement[] stackTraceElements = thread.getStackTrace();
        int start = 0;
        if (stackTraceElements.length > 2) {
            if (stackTraceElements[0].isNativeMethod()) {
                start++;
            }
        }
        if (start > 0) {
            StackTraceElement[] ste2 = new StackTraceElement[stackTraceElements.length - start];
            System.arraycopy(stackTraceElements, start, ste2, 0, ste2.length);
            stackTraceElements = ste2;
        }

        stackTrace.setStackTrace(stackTraceElements);
        //stackTrace.setStackTrace(thread.getStackTrace());
        return stackTrace;
    }
}
