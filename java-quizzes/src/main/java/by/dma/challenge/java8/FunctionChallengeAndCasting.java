package by.dma.challenge.java8;

import java.util.function.Function;

/**
 * How to manipulate Function with Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class FunctionChallengeAndCasting {
    public static void main(String... doYourBest) {
        Function<Integer, Integer> add = x -> x + 2;
        Function<Integer, Integer> sub = x -> x - 2;
        Function<Integer, Integer> div = x -> x / 2;

        Function<Integer, Integer> func = add.andThen(sub).andThen(div);

        System.out.println(func.apply(2));
    }
}
