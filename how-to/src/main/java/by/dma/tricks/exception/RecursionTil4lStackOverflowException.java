package by.dma.tricks.exception;

/**
 * The depth of recursive calls till StackOverflowError is different due to C1/C2 optimization.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
public class RecursionTil4lStackOverflowException {

    private static int depth;

    static void recursion() {
        depth++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (StackOverflowError err) {
            System.out.println("depth = " + depth);
        }

    }

}
