package patterns;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * THe usage of lambda-functions as lightweight STRATEGY.
 **/
public class CalculationStrategyDemo {
    public static void main(String[] args) {

        List<Integer> numbers = IntStream
                .generate(() -> (int) (Math.random() * 100))
                .limit(20)
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("### SUM FUNCTION ###");
        System.out.printf("Total: %d%n", calculateBySumFunction(numbers, number -> true));
        System.out.printf("Sum of odd numbers: %d%n", calculateBySumFunction(numbers, number -> number % 2 == 0));
        System.out.printf("Sum numbers of even numbers: %d%n",
                calculateBySumFunction(numbers, number -> number % 2 != 0));

        System.out.println("### REDUCE FUNCTION ###");
        System.out.printf("Total: %d%n", calculateByReduceFunction(numbers, number -> true));
        System.out.printf("Sum of odd numbers: %d%n", calculateByReduceFunction(numbers, number -> number % 2 == 0));
        System.out.printf("Sum numbers of even numbers: %d%n",
                calculateByReduceFunction(numbers, number -> number % 2 != 0));

        System.out.println("### BAD FILTERING ###");
        List<Integer> filteredNumbers =
                numbers.stream()
                        .filter(num -> {
                            if (num % 7 == 0) {
                                return true;
                            }
                            if (num % 11 == 0) {
                                return true;
                            }
                            if (num % 17 == 0) {
                                return true;
                            }
                            // a lot of code here for filtering
                            return false;
                        }).collect(Collectors.toList());
        System.out.printf("Numbers divided by 7, 11 and 17 : %s%n", filteredNumbers);

        System.out.println("### GOOD FILTERING(avoid multi line lambda) ###");
        filteredNumbers = numbers.stream()
                .filter(CalculationStrategyDemo::filterStrategy)
                .collect(Collectors.toList());
        System.out.printf("Numbers divided by 7, 11 and 17 : %s%n", filteredNumbers);
    }

    private static boolean filterStrategy(Integer num) {
        if (num % 7 == 0) {
            return true;
        }
        if (num % 11 == 0) {
            return true;
        }
        if (num % 17 == 0) {
            return true;
        }
        // a lot of code here for filtering
        return false;
    }

    private static long calculateBySumFunction(List<Integer> numbers, Predicate<Integer> selector) {
        return numbers.stream()
                .filter(selector)
                .mapToLong(intNumber -> intNumber)
                .sum();
    }

    private static long calculateByReduceFunction(List<Integer> numbers, Predicate<Integer> selector) {
        return numbers.stream()
                .filter(selector)
                .reduce(0, Integer::sum);
    }
}
