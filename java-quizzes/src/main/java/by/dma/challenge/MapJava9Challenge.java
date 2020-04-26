package by.dma.challenge;

import java.util.Map;

/**
 * Learn how to use the putIfAbsent method from a Map and also how to manipulate items inputs.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class MapJava9Challenge {
    public static void main(String... doYourBest) {
        Map<String, String> map = Map.of("no", "bugs", "no", "stress");

        map.put("Do", "YourBest");

        map.forEach((k, v) -> {if (k == "no") map.remove(k);});

        System.out.println(map);
    }
}
