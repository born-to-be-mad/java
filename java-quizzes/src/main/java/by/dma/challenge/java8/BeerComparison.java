package by.dma.challenge.java8;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Quiz on Lambda Case BeerComparison: to learn in details about how functional interfaces work with Java.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class BeerComparison {
    public static void main(String... doYourBest) {
        Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> number -> number > pivot;

        Supplier<Integer> moeSupplier = () -> {
            return 5;
        };
        Consumer<Object> homerConsumer = System.out::println;

        UnaryOperator<Integer> carlOperator = (c) -> Integer.valueOf(c);
        BinaryOperator<Integer> barneyOperator = Integer::sum;

        var beersSum = carlOperator.apply(5) + barneyOperator.apply(2, 2);

        System.out.println(moeSupplier.get());
        System.out.println(beersSum);
        homerConsumer.accept(isGreaterThan.apply(moeSupplier.get()).test(beersSum));
    }
}
