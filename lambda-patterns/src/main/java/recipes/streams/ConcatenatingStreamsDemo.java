package recipes.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:31
 * @since : 2019.07
 **/
public class ConcatenatingStreamsDemo {

    private Stream<String> first;
    private Stream<String> second;
    private Stream<String> third;
    private Stream<String> fourth;

    public static void main(String[] args) {
        new ConcatenatingStreamsDemo().run();
    }

    private void run() {
        initSourceStreams();
        List<String> option1Result = Stream.concat(Stream.concat(first, second), third)
                .collect(Collectors.toList());
        System.out.printf("Concatenated 3 streams(OPTION 1):%s%n", option1Result);

        initSourceStreams();
        List<String> option2Result = Stream.of(first, second, third, fourth)
                .reduce(Stream.empty(), Stream::concat)
                .collect(Collectors.toList());
        System.out.printf("Concatenated 3 streams(OPTION 2):%s%n", option2Result);
        //possible StackOverflow error

        initSourceStreams();
        List<String> option3Result = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        System.out.printf("Concatenated 3 streams(OPTION 3, the best non-parallel):%s%n", option3Result);

        initSourceStreams();
        List<String> option4Result = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .parallel()
                .collect(Collectors.toList());
        System.out.printf("Concatenated 3 streams(OPTION 4, the best parallel):%s%n", option4Result);
    }

    private void initSourceStreams() {
        first = Stream.of("a", "b", "c").parallel();
        second = Stream.of("X", "Y", "Z");
        third = Stream.of("alpha", "beta", "gamma");
        fourth = Stream.empty();
    }
}
