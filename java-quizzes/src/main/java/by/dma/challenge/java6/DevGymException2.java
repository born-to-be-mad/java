package by.dma.challenge.java6;

/**
 * Quiz to learn the cycle of an Exception through, try, catch and finally.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class DevGymException2 {
    private static IllegalArgumentException illegalArgument;

    public static void main(String... doYourBest) {
        try {
            double exceptionNumber = 2 / 0.0;
            System.out.println("exceptionNumber");

            throw illegalArgument;
        } catch (NullPointerException nullPointer) {
            System.out.println("NullPointer");
        } catch (IllegalArgumentException illegalArgument) {
            System.out.println("IllegalArgument");
        } catch (Exception e) {
            System.out.println("Exception");
        } finally {
            System.out.println("Finally");
        }
    }
}
