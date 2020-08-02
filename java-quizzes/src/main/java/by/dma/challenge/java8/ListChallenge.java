package by.dma.challenge.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Quiz on Collections.
 * Arrays are objects in Java. Variables store references instead of the object.
 * When we pass an object reference to a method we are changing the object that is in the heap of the memory.
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
