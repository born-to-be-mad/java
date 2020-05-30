package by.dma.challenge.java8;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Quiz on Optional OptionalChallenge3: how to use essential Optional methods.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class OptionalChallenge3 {

    public static void main(String... doYourBest) {
        Optional<String> value = Optional.of("d'oh");

        AtomicInteger dohQtd = new AtomicInteger(999);
        AtomicInteger eatMyShortsQtd = new AtomicInteger(500);

        value.ifPresentOrElse(
            v -> dohQtd.incrementAndGet(),
            eatMyShortsQtd::incrementAndGet);

        System.out.println(value.get() + (dohQtd.get() + eatMyShortsQtd.get()));
    }
}
