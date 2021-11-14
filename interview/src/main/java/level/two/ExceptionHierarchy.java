package level.two;

import java.io.FileNotFoundException;

/**
 * What is the output?
 *
 * @impNote Result:
 * Started:
 * Exception
 * finallyInternal
 * finallyExternal
 * Error:JVMError
 *
 * What is on the line A?
 */
public class ExceptionHierarchy {

    private static String s = "Started:\n";

    public static void main(String... args) {
        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            try {
                try {
                    throw new FileNotFoundException();
                } catch (RuntimeException ex) {
                    s += "RuntimeException\n";
                }
                throw new NullPointerException();
            } catch (Exception x) {
                s += "Exception\n";
            } finally {
                s += "finallyInternal\n";
            }
        } finally {
            s += "finallyExternal\n";
            try {
                throw new VirtualMachineError("JVMError") { // Line A
                };
            } catch (Error error) {
                s += "Error:" + error.getMessage();
            }
        }

        System.out.println(s);
    }

}
