package patterns;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculationStrategyDemo {
    public static void main(String[] args) {

        List<Integer> numbers = IntStream
                .generate(() -> (int) (Math.random() * 100))
                .limit(10)
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());

        System.out.printf("Total: %d%n", calculate(numbers, number -> true));
        System.out.printf("Sum of odd numbers: %d%n", calculate(numbers, number -> number % 2 == 0));
        System.out.printf("Sum numbers of even numbers: %d%n", calculate(numbers, number -> number % 2 != 0));
    }

    private static long calculate(List<Integer> numbers, Predicate<Integer> selector) {
        return numbers.stream()
                .filter(selector)
                .mapToLong(longNum -> longNum)
                .sum();
    }
}
