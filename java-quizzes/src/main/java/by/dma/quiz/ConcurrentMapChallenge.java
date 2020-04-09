package by.dma.quiz;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Quiz on Map ConcurrentMap.
 * Learn how to use the putIfAbsent method from a Map and also how to manipulate items inputs.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
public class ConcurrentMapChallenge {
    public static void main(String... doYourBest) {
        ConcurrentMap<String, Object> cache = new ConcurrentHashMap<>();
        cache.put("111", new Double(5));
        cache.putIfAbsent("111", "LOL");
        cache.put("111", new Integer(4));
        cache.put("222", new Integer(4));

        System.out.println(cache);
    }
}
