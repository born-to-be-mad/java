package by.dma;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Overview of Collections.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class CollectionsOverview {

    public static void main(String[] args) {
        List<Class<?>> interfaces = Arrays.asList(
                java.util.Collection.class,
                java.util.List.class,
                java.util.Set.class,
                java.util.SortedSet.class,
                java.util.NavigableSet.class,
                java.util.Queue.class,
                java.util.Deque.class,
                java.util.Map.class,
                java.util.SortedMap.class,
                java.util.NavigableMap.class
        );
        List<Class<?>> implementations = Arrays.asList(
                java.util.ArrayList.class,
                java.util.LinkedList.class,
                java.util.HashSet.class,
                java.util.TreeSet.class,
                java.util.PriorityQueue.class,
                java.util.HashMap.class,
                java.util.TreeMap.class,
                java.util.LinkedHashMap.class
        );
        List<Class<?>> threadSafeImplementation = Arrays.asList(
                java.util.concurrent.CopyOnWriteArrayList.class,
                java.util.concurrent.ConcurrentHashMap.class,
                java.util.concurrent.CopyOnWriteArraySet.class
        );

        System.out.println("INTERFACES");
        interfaces.forEach(System.out::println);
        System.out.println("Implementation Classes");
        implementations.forEach(System.out::println);
        System.out.println("Thread-safe Implementation Classes");
        threadSafeImplementation.forEach(System.out::println);

        //synchronized (thread-safe) WRAPPERS
        java.util.Collections.synchronizedCollection(List.of(1));
        java.util.Collections.synchronizedSet(Set.of(1));
        java.util.Collections.synchronizedList(List.of(1));
        java.util.Collections.synchronizedMap(Map.of(1, 2));
        java.util.Collections.synchronizedSortedSet(new TreeSet<>());
        java.util.Collections.synchronizedSortedMap(new TreeMap<>());

        //Unmodifiable WRAPPERS
        java.util.Collections.unmodifiableCollection(List.of(1L));
        java.util.Collections.unmodifiableSet(Set.of(1));
        java.util.Collections.unmodifiableList(List.of(1));
        java.util.Collections.unmodifiableMap(Map.of(1, 2));
        java.util.Collections.unmodifiableSortedSet(new TreeSet<>());
        java.util.Collections.unmodifiableSortedMap(new TreeMap<>());

        interfaces.forEach(CollectionsOverview::printClassInfo);
        implementations.forEach(CollectionsOverview::printClassInfo);
        threadSafeImplementation.forEach(CollectionsOverview::printClassInfo);
    }

    private static void printClassInfo(Class<?> clazz) {
        System.out.printf("%1$s%n#### %2$-40s ####%n%1$s%n", "#".repeat(50), clazz);
        Arrays.stream(clazz.getMethods()).forEach(System.out::println);
    }
}
