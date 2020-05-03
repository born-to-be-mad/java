package by.dma.challenge;

import java.util.stream.IntStream;

/**
 * Quiz on Streams to learn details about working with Streams in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class StreamChallenge8 {
    public static void main(String... doYourBest) {
        IntStream.iterate(10, i -> i - 2)
                 .limit(5)
                 .skip(1)
                 .dropWhile(i -> i < 6)
                 .sorted()
                 .takeWhile(i -> i > 2)
                 .forEach(System.out::println);
    }
}
