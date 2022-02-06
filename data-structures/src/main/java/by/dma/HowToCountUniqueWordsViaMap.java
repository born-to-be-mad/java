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
        System.out.println("uniqueCounter.straitForwardCount = " + uniqueCounter.countWordsLegacyWay(words));
    }

}

class UniqueCounter {

    // 1: old way
    public Map<String, Integer> countWordsLegacyWay(List<String> words) {
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

    // 2: init via `putIfAbsent`, then `computeIfPresent`
    public Map<String, Integer> countWordsWithMapPutIfAbsentAndComputeIfPresent(List<String> words) {
        var map = new HashMap<String, Integer>();
        words.forEach(word -> {
            map.putIfAbsent(word, 0);
            //map.put(word, map.get(word) + 1);
            map.computeIfPresent(word, (w, prev) -> prev + 1);
        });
        return map;
    }

    // 3: `compute` with null check
    public Map<String, Integer> countWordsWithCompute(List<String> words) {
        var map = new HashMap<String, Integer>();
        words.forEach(word -> map.compute(word, (w, prev) -> prev != null ? prev + 1 : 1));
        return map;
    }


    // 4: shining of `map merge`
    public Map<String, Integer> countWordsWithMapMerge(List<String> words) {
        var map = new HashMap<String, Integer>();
        words.forEach(word -> map.merge(word, 1, Integer::sum));
        return map;
    }
}
