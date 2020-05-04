package by.dma.challenge;

/**
 * Quiz on String: how to change a String value and will learn how object references work.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class StringChallenge {
    public static void main(String... doYourBest) {
        var jedi = "masterYoda";

        changeString(jedi);

        System.out.println(jedi);
    }

    static String changeString(String jedi) {
        jedi.replace(jedi, "darthVader");

        return jedi;
    }
}
