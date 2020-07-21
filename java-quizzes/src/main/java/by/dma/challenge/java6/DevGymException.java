package by.dma.challenge.java6;

/**
 * Working of Exception Handling in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class DevGymException {
    private static NullPointerException nullPointerException;

    public static void main(String[] args) {
        try {
            double infinity = 2 / 0.0; //line #9
            throw nullPointerException;
        } catch (Exception e) {
            System.out.println("Inside catch block");
            System.out.println(e);
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Inside finally block");
        }
    }
}
