package by.dma.challenge;

import java.util.Arrays;
import java.util.List;

/**
 * Quiz on Collections.
 *
 * @author dzmitry.marudau
 * @since 2020.5
 */
public class ListChallenge {
    public static void main(final String... doYourBest) {
        final List<String> soldiers = Arrays.asList("Tyrion", "Arya", "John");

        if (soldiers.size() > 3) {
            soldiers.add("Cersei");
        } else {
            soldiers.add("Bran");
        }

        System.out.println(soldiers);
    }
}
