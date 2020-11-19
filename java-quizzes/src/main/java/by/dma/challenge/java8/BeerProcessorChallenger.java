package by.dma.challenge.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Quiz on Lambda. Absorb the powerful concepts of functional programming with lambdas by solving this Java quiz!
 * Remember that Lambdas are lazy!
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
public class BeerProcessorChallenger {
    private static int drunkenness;

    public static void main(String... doYourBest) {
        Supplier<Integer> moeBeerSupplier = () -> drunkenness = 5; // Line 10
        drunkenness = 10;
        Function<Integer, Integer> processBeer = beerProcessor -> drunkenness = (beerProcessor + 2);

        Consumer<Integer> homerBeerConsumer = System.out::println;
        homerBeerConsumer.accept(moeBeerSupplier.get() + processBeer.apply(drunkenness)); // Line 15
    }

}
