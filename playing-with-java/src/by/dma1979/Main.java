package by.dma1979;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Function<Integer, Double> function = new Function<>() {
            public Double apply(Integer i) {
                return i * 5.5;
            }
        };
        System.out.println("Function:" + function.apply(5));

        Function<Integer, Double> functionAsLambda = i -> i * 5.5;
        System.out.println("Function as lambda:" + functionAsLambda.apply(5));
        //prints: 27.5
        System.out.println("\n ################## \n");

        Consumer<String> consumer = new Consumer<>() {
            public void accept(String s) {
                System.out.println("The " + s + " is consumed.");
            }
        };
        consumer.accept("USER");
        //prints: The Hello! is consumed.
        Consumer consumerAsLambda = s -> System.out.println("Consumer as lambda:The " + s + " is consumed.");
        consumerAsLambda.accept("USER");
        //prints: The USER is consumed.
        System.out.println("\n ################## \n");

        Supplier supplier = new Supplier() {
            public String get() {
                String res = "Success";
                //Do something and return result—Success or Error.
                return res;
            }
        };
        System.out.println("Supplier:" + supplier.get());
        Supplier supplierAsLambda = () -> {
            String res = "Success";
            return res;
        };
        System.out.println("Supplier as lambda:" + supplierAsLambda.get());
        //prints: Result
        System.out.println("\n ################## \n");

        Predicate<Double> predicate = new Predicate<>() {
            public boolean test(Double num) {
                System.out.println("Test if " + num + " is smaller than 20");
                return num < 20;
            }
        };
        System.out.println(predicate.test(15.0));
        System.out.println(predicate.test(25.0));
        Predicate<Double> predicateAsLambda = (num) -> {
            System.out.println("Test if " + num + " is smaller than 20");
            return num < 20;
        };
        System.out.println(predicateAsLambda.test(15.0));
        //Test if 15.0 is smaller than 20
        //true
        System.out.println(predicateAsLambda.test(25.0));
        //Test if 25.0 is smaller than 20
        //false
        System.out.println("\n ################## \n");

        IntFunction intFunction = new IntFunction() {
            public String apply(int i) {
                return String.valueOf(i * 10);
            }
        };
        System.out.println("IntFunction:" + intFunction.apply(5));
        //prints: 50
        System.out.println("\n ################## \n");

        BiFunction<String, Integer, Double> biFunction = new BiFunction<>() {
            public Double apply(String s, Integer i) {
                return (s.length() * 10d) / i;
            }
        };
        System.out.println("BiFunction:" + biFunction.apply("abc", 2));
        BiFunction<String, Integer, Double> biFunctionAsLambda = (s, i) -> (s.length() * 10d) / i;
        System.out.println("BiFunction as lambda:" + biFunctionAsLambda.apply("abc", 2));
        //prints: 15.0
        System.out.println("\n ################## \n");

        BinaryOperator<Integer> binaryOperator = new BinaryOperator<>() {
            public Integer apply(Integer i, Integer j) {
                return i >= j ? i : j;
            }
        };
        System.out.println("BinaryOperator:" + binaryOperator.apply(1, 2));  //prints: 2
        BinaryOperator<Integer> binaryOperatorAsLambda = (i, j) -> i >= j ? i : j;
        System.out.println("BinaryOperator as lambda:" + binaryOperatorAsLambda.apply(1, 2));  //prints: 2
        System.out.println("\n ################## \n");

        IntBinaryOperator intBinaryOperator = new IntBinaryOperator() {
            public int applyAsInt(int i, int j) {
                return i >= j ? i : j;
            }
        };
        System.out.println("IntBinaryOperator:" + intBinaryOperator.applyAsInt(1, 2));
        IntBinaryOperator intBinaryOperatorAsLambda = (i, j) -> i >= j ? i : j;
        System.out.println("IntBinaryOperator as lambda:" + intBinaryOperatorAsLambda.applyAsInt(1, 2));
        System.out.println("\n ################## \n");

        System.out.println("\n ###CALCULATE ### \n");
        Supplier source = () -> 4;
        Function<Integer, Double> before = i -> i * 3.0;
        Function<Double, Double> after = d -> d * 5.0;
        Function<Integer, Double> process = before.andThen(after);
        Predicate<Double> condition = n -> n > 10;
        Consumer<Double> success = d -> System.out.println("Success: " + d);
        Consumer<Double> failure = d -> System.out.println("Failure: " + d);
        calculate(source, process, condition, success, failure);
        calculate(source, process, n -> n > 100, success, failure);

        calculate(() -> 4, i -> i * 3.0, n -> n > 10, success, failure);
        calculate(() -> 4, i -> i * 3.0, n -> n > 100, success, failure);

    }


    static void calculate(Supplier<Integer> source, Function<Integer, Double> process, Predicate<Double> condition,
                   Consumer<Double> success, Consumer<Double> failure) {
        int i = source.get();
        double res = process.apply(i);
        if (condition.test(res)) {
            success.accept(res);
        } else {
            failure.accept(res);
        }
    }
}
