package by.dma.challenge.java9;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Quiz on Streams
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class TakeAndDropWhileChallenge {
    public static void main(String... castleVania) {
        //Choice 1
/*
        List<Integer> alucardHits = List.of(9, 7, 1, 8, 5);
        Set<Integer> draculaHits = Set.of(9, 6, 5, 7, 8);
*/

        //Choice 2
/*
        List<Integer> alucardHits = List.of(7, 6);
        List<Integer> draculaHits = List.of(5, 7, 10, 8);
*/

        //Choice 3
/*
        Set<Integer> alucardHits = Set.of(9, 9, 7, 1, 8, 5);
        List<Integer> draculaHits = List.of(9, 6, 5, 7, 8);
*/

        //Choice 4
        List<Integer> alucardHits = List.of(9, 7, 1, 8, 5);
        List<Integer> draculaHits = List.of(9, 6, 5, 7, 8);

        Stream<Integer> alucardPerformedHits = alucardHits.stream()
                                                          .takeWhile(i -> i > 5)
                                                          .dropWhile(i -> i > 8);

        Stream<Integer> draculaPerformedHits = draculaHits.stream()
                                                          .takeWhile(i -> i > 1)
                                                          .dropWhile(i -> i > 7);

        Stream.of(alucardPerformedHits, draculaPerformedHits)
              .flatMap(h -> h)
              .forEach(System.out::print);
    }
}
