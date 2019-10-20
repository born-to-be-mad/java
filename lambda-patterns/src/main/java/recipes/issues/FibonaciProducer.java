package recipes.issues;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:32
 * @since : 2019.07
 **/
public class FibonaciProducer {

    private Map<Long, BigInteger> cache = new HashMap<>();

    public BigInteger get(long i) {
        if (i == 0) {
            return BigInteger.ZERO;
        }
        if (i == 1) {
            return BigInteger.ONE;
        }
        return cache
                .computeIfAbsent(i, n -> get(n - 2).add(get(n - 1)));
    }
}
