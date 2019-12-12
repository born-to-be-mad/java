package by.dma1979.challendges;

import java.util.stream.Stream;

/**
 * @author dzmitry.marudau
 * @since 2019.9
 */
public class StreamWhileChallenge {

    public static void main(String... doYourBest) {
        String sopranos = "<Tony,Junior,Christopher>";

        Stream.of(sopranos)
            .dropWhile(s -> !s.contains("<"))
            .takeWhile(s -> !s.contains(">"))
            .forEach(System.out::println);
        /*
        The condition !s.contains("<") will be false.
        It means ".dropWhile(false).takeWhile(false)"
        So nothing will be printed.*/

    }
}
