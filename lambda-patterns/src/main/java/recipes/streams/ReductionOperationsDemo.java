package recipes.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        OptionalInt maxLengthString = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();
        OptionalInt minLengthString = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.printf("The max and min lengths are %s and %s%n", minLengthString, maxLengthString);

        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y)
                .orElse(0);
        System.out.printf("Sum of numbers from the range [1,10] = %d%n", sum);

        int doubledSum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y * 2)
                .orElse(0);
        System.out.printf("Problematic doubled Sum of numbers from the range [1,10] = %d%n", doubledSum);

        //when you use the version of reduce with an initial value for the accumulator,
        // the return type is int rather than OptionalInt
        int correctDoubledSum = IntStream.rangeClosed(1, 10)
                .reduce(0, (x, y) -> x + y * 2);
        System.out.printf("Correct doubled Sum of numbers from the range [1,10] = %d%n", correctDoubledSum);

        //The identity for max is the minimum integer
        int max = IntStream.generate(() -> (int) (100 * Math.random()))
                .limit(10)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("The max value is " + max);

        //inefficient because String concatenation creates and destroys objects
        String concatenatedString = Arrays.stream(strings)
                .reduce("Concatenating strings from a stream using reduce:", String::concat);
        System.out.println(concatenatedString);

        //Collecting strings, with method references
        String jointStringViaStringBuilder = Arrays.stream(strings)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
        System.out.println(jointStringViaStringBuilder);

        //Simplest of all= use the joining method in the Collectors utility
        String joinStringsViaCollector = Arrays.stream(strings)
                .collect(Collectors.joining());
        System.out.println(joinStringsViaCollector);

        System.out.println("### The most general form of reduce ###");
        Stream<Book> bookStream = Stream.of(
                new Book(1, "Spring in action"),
                new Book(2, "Modern Java recipes"),
                new Book(3, "Spring in cloud"));
        HashMap<Integer, Book> bookMap = bookStream
                .reduce(new HashMap<>(),
                        (map, book) -> {
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });
        bookMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

class Book {
    private Integer id;
    private String title;

    Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + "}";
    }
}
