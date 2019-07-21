package recipes.streams;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 15:17
 * @since : 2019.07
 **/
public class StreamStatisticsDemo {

    public static void main(String[] args) {
        long countStrings = Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens","Jurgen")
                .count();
        System.out.printf("There are %d elements in the stream(Stream.count)%n", countStrings);

        long countStringsViaCollector = Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens","Jurgen")
                .collect(Collectors.counting());
        System.out.printf("There are %d elements in the stream(Collectors.counting)%n", countStringsViaCollector);

        Stream.of("Dima", "Stanislav", "Herman", "Serg", "Clemens","Jurgen")
                .collect(Collectors.partitioningBy(name -> name.length() % 2 == 0, Collectors.counting()))
                .forEach((k, v) -> System.out.printf("%5s: %d%n", k, v));
    }
}
