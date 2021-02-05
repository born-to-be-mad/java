package by.dma;

import java.util.Arrays;
import java.util.LinkedList;
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
                java.util.BitSet.class,
                java.util.EnumSet.class,
                java.util.PriorityQueue.class,
                java.util.HashMap.class,
                java.util.TreeMap.class,
                java.util.LinkedHashMap.class,
                java.util.EnumMap.class
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

        interfaces.forEach(CollectionsOverview::printClassInfo);
        implementations.forEach(CollectionsOverview::printClassInfo);
        threadSafeImplementation.forEach(CollectionsOverview::printClassInfo);

        //Collections.synchronizedInterface = synchronized (thread-safe) WRAPPERS
        java.util.Collections.synchronizedCollection(List.of(1));
        java.util.Collections.synchronizedSet(Set.of(1));
        java.util.Collections.synchronizedList(List.of(1));
        java.util.Collections.synchronizedMap(Map.of(1, 2));
        java.util.Collections.synchronizedSortedSet(new TreeSet<>());
        java.util.Collections.synchronizedSortedMap(new TreeMap<>());

        //Collections.unmodifiableInterface = Unmodifiable WRAPPERS
        java.util.Collections.unmodifiableCollection(List.of(1));
        java.util.Collections.unmodifiableSet(Set.of(1));
        java.util.Collections.unmodifiableList(List.of(1));
        java.util.Collections.unmodifiableMap(Map.of(1, 2));
        java.util.Collections.unmodifiableSortedSet(new TreeSet<>());
        java.util.Collections.unmodifiableSortedMap(new TreeMap<>());

        //Collections.checkedInterface = typesafe view of the specified collection
        java.util.Collections.checkedCollection(List.of(1), Integer.class);
        java.util.Collections.checkedSet(Set.of(1), Integer.class);
        java.util.Collections.checkedList(List.of(1), Integer.class);
        java.util.Collections.checkedMap(Map.of(1, 2), Integer.class, Integer.class);
        java.util.Collections.checkedSortedSet(new TreeSet<>(), Integer.class);
        java.util.Collections.checkedSortedMap(new TreeMap<>(), Integer.class, Integer.class);

        // ADAPTERS
        java.util.Collections.newSetFromMap(Map.of(1, true));
        java.util.Collections.asLifoQueue(new LinkedList<>(List.of(1, 2, 3));
        java.util.Arrays.asList(new int[] {1, 2, 3});

        java.util.Collections.emptySet();
        java.util.Collections.emptyList();
        java.util.Collections.emptyMap();

        java.util.Collections.singleton(1);
        java.util.Collections.singletonList(1);
        java.util.Collections.singletonMap(Map.of(1, 2);

        java.util.Collections.nCopies(1,5);
    }

    private static void printClassInfo(Class<?> clazz) {
        System.out.printf("%1$s%n#### %2$-40s ####%n%1$s%n", "#".repeat(50), clazz);
        Arrays.stream(clazz.getMethods()).forEach(System.out::println);
    }
}
