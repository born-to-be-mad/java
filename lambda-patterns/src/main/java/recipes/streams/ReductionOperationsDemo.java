package recipes.streams;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class ReductionOperationsDemo {
    public static void main(String[] args) {
        String[] strings = "The chain is not stronger than its weakest link".split(" ");
        long count = Arrays.stream(strings)
                .map(String::length)
                .count();
        System.out.printf("There are %d strings%n", count);

        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length)
                .sum();
        System.out.printf("The total length is %d%n", totalLength);

        OptionalDouble ave = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        System.out.printf("The average length is %s%n", ave);

        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.printf("The max and min lengths are %s and %s%n", min, max);

        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y)
                .orElse(0);
        System.out.printf("Sum of numbers fro the range [1,10] = %d%n", sum);
    }
}
