package by.dma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * How to count the unique word occurrences.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
public class HowToCountUniqueWordsViaMap {

    public static void main(String[] args) {
        var words = List.of("Hello", "World", "Hello", "Java", "World", "Hello", "Hello", "Hello");
        UniqueCounter uniqueCounter = new UniqueCounter();
        System.out.println("uniqueCounter.straitForwardCount = " + uniqueCounter.straitForwardCount(words));
    }

}

class UniqueCounter {

    public Map<String, Integer> straitForwardCount(List<String> words) {
        var map = new HashMap<String, Integer>();
        words.forEach(word -> {
            var prev = map.get(word);
            if (prev == null) {
                map.put(word, 1);
            } else {
                map.put(word, prev + 1);
            }
        });
        return map;
    }
}
