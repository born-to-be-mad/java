package java9;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:51
 * @since : 2019.09
 **/
public interface PrivateMethodsInInterfacesDemo {
    default int sumEvens(int... nums) {
        return add(n -> n % 2 == 0, nums);
    }
    default int sumOdds(int... nums) {
        return add(n -> n % 2 != 0, nums);
    }
    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)
                .filter(predicate)
                .sum();
    }
}
