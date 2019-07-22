package recipes.streams;

import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:11
 * @since : 2019.07
 **/
public class NumberStreamUtils {

    private NumberStreamUtils() {
    }

    public static boolean isPrime(int num) {
        int limit = (int) (Math.sqrt(num) + 1);
        return num == 2 || num > 1
               &&
               IntStream.range(2, limit)
                       .noneMatch(divisor -> num % divisor == 0);
    }
}
