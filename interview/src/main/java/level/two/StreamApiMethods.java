package level.two;

import java.util.stream.IntStream;

/**
 * What is the output?
 *
 * @impNote Result: nothing
 */
public class StreamApiMethods {

    public static void main(String[] args) {
        IntStream.iterate(10, i -> i - 2)
                 .limit(5)
                 .skip(1)
                 .dropWhile(i -> i < 6)
                 .sorted()
                 .takeWhile(i -> i > 2)
                 .forEach(System.out::println);
    }

}
