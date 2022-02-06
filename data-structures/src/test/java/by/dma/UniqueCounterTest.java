package by.dma;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
class UniqueCounterTest {
    private final UniqueCounter counter = new UniqueCounter();

    @Test
    void testCountWordsLegacyWay() {
        var words = List.of("Hello", "World", "Hello", "Java", "World", "Hello", "Hello", "Hello");

        assertEquals(counter.countWordsLegacyWay(words), Map.ofEntries(
                Map.entry("Hello", 5),
                Map.entry("World", 2),
                Map.entry("Java", 1)
        ));
    }

    @Test
    void testCountWordsWithMapPutIfAbsentAndComputeIfPresent() {
        var words = List.of("Hello", "World", "Hello", "Java", "World", "Hello", "Hello", "Hello");
        UniqueCounter counter = new UniqueCounter();
        assertEquals(counter.countWordsWithMapPutIfAbsentAndComputeIfPresent(words), Map.ofEntries(
                Map.entry("Hello", 5),
                Map.entry("World", 2),
                Map.entry("Java", 1)
        ));
    }

    @Test
    void testCountWordsWithCompute() {
        var words = List.of("Hello", "World", "Hello", "Java", "World", "Hello", "Hello", "Hello");
        UniqueCounter counter = new UniqueCounter();
        assertEquals(counter.countWordsWithCompute(words), Map.ofEntries(
                Map.entry("Hello", 5),
                Map.entry("World", 2),
                Map.entry("Java", 1)
        ));
    }


    @Test
    void testCountWordsMapMerge() {
        var words = List.of("Hello", "World", "Hello", "Java", "World", "Hello", "Hello", "Hello");
        UniqueCounter counter = new UniqueCounter();
        assertEquals(counter.countWordsWithMapMerge(words), Map.ofEntries(
                Map.entry("Hello", 5),
                Map.entry("World", 2),
                Map.entry("Java", 1)
        ));
    }

}
