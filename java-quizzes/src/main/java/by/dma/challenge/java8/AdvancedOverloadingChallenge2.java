package by.dma.challenge.java8;

/**
 * Quiz on overloading to learn details about working with Overloading in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class AdvancedOverloadingChallenge2 {
    static String finalResult = "";

    public static void main(String... doYourBest) {
        executeAction(1, true);
        executeAction();
        executeAction(new int[]{1, 2, 3}, 1);
        executeAction(1L);
        executeAction(1);
        executeAction(Double.valueOf(1));

        System.out.println(finalResult);
    }

    static void executeAction(Object o) {
        finalResult += "1";
    }

    static void executeAction(Object... o) {
        finalResult += "2";
    }

    static void executeAction(StackOverflowError... i) {
        finalResult += "3";
    }

    static void executeAction(Long l) {
        finalResult += "4";
    }

    static void executeAction(double d) {
        finalResult += "5";
    }
}
