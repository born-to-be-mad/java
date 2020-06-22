package by.dma.challenge.java8;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * How a variable behaves into a Lambda method implementation.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class JokerCrazinessChallenger {
    static int jokerCraziness = 0;

    public static void main(String... doYourBest) {
        Supplier<Integer> supplier = () -> jokerCraziness++;

        Consumer<Integer> consumer = (batmanAttack) ->
                System.out.println(batmanAttack + jokerCraziness++);

        System.out.println(jokerCraziness + supplier.get());
        consumer.accept(1);
    }
}
