package by.dma.challenge;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author dzmitry.marudau
 * @since 2019.8
 */
public class MinMaxChallenge {

    public static void main(String... doYourBest) {
        IntStream intStream = List.of(1, 2, 3, 4, 5, 6)
            .stream().mapToInt(n -> n);

        IntStream intStream2 = intStream;

        OptionalInt optIntMin = intStream.min();
        //Once the Stream is used at the intStream.min() invocation,
        // the same Stream can't be used again to the intStream2.max() method invocation.
        OptionalInt optIntMax = intStream2.max();

        int sum = optIntMax.orElse(5) + optIntMin.orElse(5);

        System.out.println(sum);
    }

}
