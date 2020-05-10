package by.dma.challenge.java6;

/**
 * Quiz on Exception:  how Exceptions behave when variables are being assigned and there is an error between them.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ExceptionValueChallenge {
    static int kratos, zeus, hades;

    public static void main(String... doYourBest) {
        try {
            invokeAGod(kratos = 1, lightningOfZeus(zeus = 2), hades = 3);
        } catch (Exception e) {
            System.out.println(kratos + " " + zeus + " " + hades);
        }
    }

    static int lightningOfZeus(int i) throws Exception {
        throw new StackOverflowError("Wowww!");
    }

    static int invokeAGod(int a, int b, int c) {
        return a + b + c;
    }
}
