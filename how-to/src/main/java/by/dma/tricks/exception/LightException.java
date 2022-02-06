package by.dma.tricks.exception;

/**
 * Light Exception without stacktrace.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
public class LightException  extends Exception {

    public LightException() {
        this(null, null);
    }

    public LightException(String message, Throwable cause) {
        super(message, cause, true, false);
    }
}
