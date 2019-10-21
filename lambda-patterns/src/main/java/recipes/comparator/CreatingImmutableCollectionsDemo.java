package recipes.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:37
 * @since : 2019.07
 **/
public class CreatingImmutableCollectionsDemo {
    public static void main(String[] args) {
        try {
            List<Integer> immutableList = createImmutableListJava7(1, 2, 3);
            immutableList.add(123);
        } catch (Exception e) {
            System.out.println("Impossible to add element to immutable list crated via Java7");
        }
        try {
            List<Integer> immutableList = createImmutableListJava8(1, 2, 3);
            immutableList.add(123);
        } catch (Exception e) {
            System.out.println("Impossible to add element to immutable list crated via Java8k");
        }
        try {
            List<Integer> immutableList = createImmutableListJava9(1, 2, 3);
            immutableList.add(123);
        } catch (Exception e) {
            System.out.println("Impossible to add element to immutable list crated via Java9");
        }
    }

    @SafeVarargs
    public static <T> List<T> createImmutableListJava7(T... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    @SafeVarargs
    public static <T> Set<T> createImmutableSetJava7(T... elements) {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(elements)));
    }

    @SafeVarargs
    public static <T> List<T> createImmutableListJava8(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toList(),
                        Collections::unmodifiableList));
    }

    @SafeVarargs
    public static <T> List<T> createImmutableListJava9(T... elements) {
        return List.of(elements);
    }

    @SafeVarargs
    public static <T> Set<T> createImmutableSetJava9(T... elements) {
        return Set.of(elements);
    }

    public static <K, V> Map<K, V> createImmutableMapJava9(K k1, V v1, K k2, V v2) {
        return Map.of(k1, v1, k2, v2);
    }

    @SafeVarargs
    public final <T> Set<T> createImmutableSetJava8(T... elements) {
        return Arrays.stream(elements)
                .collect(collectingAndThen(toSet(),
                        Collections::unmodifiableSet));
    }
}
