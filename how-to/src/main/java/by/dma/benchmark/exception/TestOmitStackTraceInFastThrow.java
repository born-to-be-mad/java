package by.dma.benchmark.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 *
 * Hint: `use --XX:-OmitStackTraceInFastThrow` VM option to disable the optimization when JVM stops outputting stack trace of
 * the exception which often occurs for performance purpose
 */
public class TestOmitStackTraceInFastThrow {

    public static void main(String[] args) {
        int counter = 0;
        while (true) {
            try {
                Object obj = null;
                /*
                 * If we cause the exception every time(= without this "if" statement), the optimization does not
                 * happen somehow.
                 * So, cause it conditionally.
                 */
                if (counter % 2 == 0) {
                    obj = new Object();
                }
                // Cause NullpointerException
                obj.getClass();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }
}
