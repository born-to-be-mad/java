package recipes.streams;

import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 10:19
 * @since : 2019.07
 **/
public class DebuggingStreamsDemo {

    public static void main(String[] args) {

        System.out.printf("Sum of squares for numbers from 10 to 30,  divided by 3 : %d%n",
                sumSquaresDivisibleBy(10, 30, 3));
    }

    private static int sumSquaresDivisibleBy(int start, int end, int divide) {
        return IntStream.rangeClosed(start, end)
                .peek(n -> System.out.printf("original: %d%n", n))
                .map(n -> n * n)
                .peek(n -> System.out.printf("squared : %d%n", n))
                .filter(n -> n % divide == 0)
                .peek(n -> System.out.printf("filtered: %d%n", n))
                .sum();
    }
}
