package by.dma.challenge.java8;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.01
 */
public class StreamsSkipChalenge {

    public static void main(String[] args) {
        process(List.of("Hello", "Java", "World"), true);
    }

    static void process(List<String> strings, boolean skipFirst) {
        Stream<String> stream = strings.stream();
        if (skipFirst) {
            stream.skip(1);
        } else {
            stream.skip(0);
        }

        stream.map(String::toUpperCase).forEach(System.out::print);
    }

}
