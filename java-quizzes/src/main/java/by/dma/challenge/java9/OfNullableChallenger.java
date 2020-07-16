package by.dma.challenge.java9;

import java.util.List;
import java.util.stream.Stream;

/**
 * How to manipulate Stream that might be null in a safe way.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class OfNullableChallenger {
    public static void main(String... args) {
        Soprano soprano = null;

        Stream.ofNullable(soprano)
              .filter(s -> s.guns.get(0) == null)
              .forEach(s -> System.out.println(s.guns.size()));
    }

    static class Soprano {
        List<String> guns;

        public Soprano(List<String> guns) {
            this.guns = guns;
        }
    }
}
