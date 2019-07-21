package recipes.streams;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:51
 * @since : 2019.07
 **/
public class FindAndMatchStreamDemo {

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList(
                "this", "is", "a", "stream", "of", "strings");
        Set<String> words = new HashSet<>(wordList);
        Set<String> words2 = new HashSet<>(words);
// Now add and remove enough elements to force a rehash
        IntStream.rangeClosed(0, 50).forEachOrdered(i ->
                words2.add(String.valueOf(i)));
        words2.retainAll(wordList);
// The sets are equal, but have different element ordering
        System.out.println(words.equals(words2));
        System.out.println("Before: " + words);
        System.out.println("After : " + words2);

        Optional<Integer> parallelAny = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .parallel()
                .map(FindAndMatchStreamDemo::getIntegerWithRandomDelay)
                .findAny();
        System.out.println("Random integer: " + parallelAny);

        Optional<Integer> sequentialAny = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .unordered()
                .map(FindAndMatchStreamDemo::getIntegerWithRandomDelay)
                .findAny();
        System.out.println("Sequential Any: " + sequentialAny);
    }

    private static Integer getIntegerWithRandomDelay(Integer n) {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        return n;
    }

}
