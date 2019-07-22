package recipes.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoxingStreamsDemo {
    public static void main(String[] args) {
        System.out.printf("### BOXING Streams: IntStream -> Integers via IntStream.collect ###%n");
        List<Integer> intsBoxed = IntStream.of(15, 5, 1, 7, 14, 2)
                .boxed()
                .collect(Collectors.toList());
        System.out.printf("IntStream.mapToObj: %s%n", intsBoxed);

        System.out.printf("### BOXING Streams: IntStream -> Integers via IntStream.mapToObj ###%n");
        List<Integer> intsMapToObj = IntStream.of(15, 5, 1, 7, 14, 2)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        System.out.printf("IntStream.mapToObj: %s%n", intsMapToObj);

        System.out.printf("### BOXING Streams: IntStream -> Integers via IntStream.collect ###%n");
        List<Integer> intsCollect = IntStream.of(15, 5, 1, 7, 14, 2)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.printf("IntStream.collect: %s%n", intsCollect);

        int[] intArray = IntStream.of(15, 5, 1, 7, 14, 2).toArray();
        System.out.printf("IntStream to int[]: %s%n", Arrays.toString(intArray));


    }
}
