package recipes.streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:52
 * @since : 2019.07
 **/
public class StreamLazinessDemo {

    public static void main(String[] args) {
        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(StreamLazinessDemo::multipleByTwo)
                .filter(StreamLazinessDemo::divideByThree)
                .findFirst();
        System.out.printf("First even number within the range 100-200, doubled and divided by 3: %s%n",
                firstEvenDoubleDivBy3);
    }

    public static int multipleByTwo(int n) {
        System.out.printf("Inside multByTwo with arg %d%n", n);
        return n * 2;
    }

    private static boolean divideByThree(int n) {
        System.out.printf("Inside divByThree with arg %d%n", n);
        return n % 3 == 0;
    }
}
