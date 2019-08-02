package recipes.streams;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingStreamsDemo {
    public static void main(String[] args) {
        String namesStreamOf = Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens", "Jurgen")
                .collect(Collectors.joining(", "));
        System.out.printf("Stream.Of: %s%n", namesStreamOf);

        String [] names = {"Dima", "Stanislav", "Herman", "Serg", "Clemens", "Jurgen"};
        String namesArraysStream = Arrays.stream(names)
                .collect(Collectors.joining(", "));
        System.out.printf("Arrays.stream: %s%n", namesArraysStream);

        System.out.printf("### CREATE Streams via Stream.iterate ###%n");
        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L))
                .limit(14)
                .forEach(System.out::println);
        // prints 14 days starting from today

        System.out.printf("### CREATE Streams via Stream.generate ###%n");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        //prints 5 random double numbers

        System.out.printf("### CREATE IntStream/LongStream via range/rangeClose ###%n");
        List<Integer> intsRange = IntStream.range(100, 105)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(intsRange);
        List<Integer> intsRangeClosed = IntStream.rangeClosed(100, 105)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(intsRangeClosed);


    }
}
