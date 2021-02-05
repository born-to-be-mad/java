package streams;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * How to solve different tasks via Stream API based on best practices.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class StreamBestUtils {

    /**
     * Returns the range of integers.
     *
     * @param fromInclusive start value(inclusive)
     * @param toExclusive   end value(exclusive)
     * @return Iterable, containing numbers from fromInclusive to toExclusive.
     */
    public static Iterable<Integer> range(int fromInclusive,
                                          int toExclusive) {
        return () -> new Iterator<>() {
            int cursor = fromInclusive;

            @Override
            public boolean hasNext() {
                return cursor < toExclusive;
            }

            @Override
            public Integer next() {
                return cursor++;
            }
        };
    }

    /**
     * Make the source of child nodes for the given parent DOM XML node.
     *
     * @param parent the parent node
     * @return the stream of children
     */
    public static Stream<Node> children(Node parent) {
        NodeList nodeList = parent.getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                        .mapToObj(nodeList::item);
    }

    /**
     * Make the source of list elements, along with indices.
     *
     * @param list the input list
     * @return the stream of elements with indexes
     */
    public static <T> Stream<IndexedValue<T>> withIndices(List<T> list) {
        return IntStream.range(0, list.size())
                        .mapToObj(idx -> new IndexedValue<>(idx, list.get(idx)));
    }

    /**
     * Select only elements of defined type from Stream.
     *
     * @param list the source list
     * @return the stream of children
     */
    public static Stream<Element> filterByType(NodeList list) {
        // approach I: casting[-], always need filer+map [-]
        Stream<Element> filteredResult1 =
                IntStream.range(0, list.getLength())
                         .mapToObj(list::item)
                         .filter(node -> node instanceof Element)
                         .map(node -> (Element) node);

        // approach II: casting[-], always need filer+map [-]
        Stream<Element> filteredResult2 =
                IntStream.range(0, list.getLength())
                         .mapToObj(list::item)
                         .filter(Element.class::isInstance)
                         .map(Element.class::cast);

        // approach III: readability[-]
        Stream<Element> filteredResult3 = select(
                IntStream.range(0, list.getLength())
                         .mapToObj(list::item),
                Element.class);

        // approach IV: best practices
        Stream<Element> filteredResult4 =
                IntStream.range(0, list.getLength())
                         .mapToObj(list::item)
                         .flatMap(select(Element.class));
        return filteredResult4;
    }

    /**
     * Leave only elements of the given type in the input stream.
     */
    public static <T, TT> Stream<TT> select(Stream<T> stream, Class<TT> clazz) {
        return stream.filter(clazz::isInstance).map(clazz::cast);
    }

    /**
     * Create a mapper function to apply to each Stream element filtering by input class.
     */
    public static <T, TT> Function<T, Stream<TT>> select(Class<TT> clazz) {
        return e -> clazz.isInstance(e) ? Stream.of(clazz.cast(e)) : null;
    }

    /**
     * Leave only those values that repeat at least N times.
     */
    public static <T> Predicate<T> distinct(long atLeast) {
        ConcurrentHashMap<T, Long> map = new ConcurrentHashMap<>();
        return t -> map.merge(t, 1L, Long::sum) == atLeast;
    }

    /**
     * Zip two lists together.
     *
     * @param <T1>   type of first list
     * @param <T1>   type of second list
     * @param <R>    type of result list
     * @param list1  first list
     * @param list2  second list
     * @param mapper input predicate
     * @return result stream
     */
    public static <T1, T2, R> Stream<R> zip(List<T1> list1, List<T2> list2,
                                            BiFunction<? super T1, ? super T2, ? extends R> mapper) {
        int size = list1.size();
        if (list2.size() != size) {
            throw new IllegalArgumentException("Different list sizes");
        }
        return  IntStream.range(0, size)
                         .mapToObj(idx -> mapper.apply(list1.get(idx), list2.get(idx)));
    }


    /**
     * Not short-circuit takeWhile implementation.
     *
     * @param predicate input predicate
     * @param <T>       type of objects
     * @return result stream
     */
    public static <T> Predicate<T> takeWhile(Predicate<T> predicate) {
        AtomicBoolean matched = new AtomicBoolean();
        return t -> {
            System.out.println("checking...");
            if (matched.get()) {
                return false;
            }
            if (!predicate.test(t)) {
                matched.set(true);
                return false;
            }
            return true;
        };
    }

    /**
     * Short-circuit takeWhile implementation.
     *
     * @param stream    input stream
     * @param predicate input predicate
     * @param <T>       type of pbjects
     * @return result stream
     */
    public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<T> predicate) {
        Spliterator<T> src = stream.spliterator();
        Spliterator<T> spliterator =
                new Spliterators.AbstractSpliterator<>(
                        src.estimateSize(),
                        src.characteristics() & ~Spliterator.SIZED & ~Spliterator.SUBSIZED) {

            private boolean finished = false;
            private T next;

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                if ((finished || !src.tryAdvance(t -> next = t) || !predicate.test(next))) {
                    finished = true;
                    return false;
                }
                action.accept(next);
                return true;
            }
        };
        return StreamSupport.stream(spliterator, stream.isParallel())
                            .onClose(stream::close);
    }

    public static <K, V> Map<K, List<V>> entriesToMap(List<Map<K, V>> input) {
        return input.stream()
                    .flatMap(map -> map.entrySet().stream())
                    .collect(groupingBy(Map.Entry::getKey,
                                        mapping(Map.Entry::getValue, toList())));
    }

    public static void main(String[] args) {
        System.out.println("### zip ###");
        List<String> jdks = List.of("JDK 1.0", "J2SE 1.2", "Java SE 8", "Java SE 11");
        List<Integer> releaseYears = List.of(1996, 1998, 2014, 2018);
        zip(jdks, releaseYears, (jdk, year) -> jdk + " was released in" + year)
                .forEach(System.out::println);
        // ############################
        System.out.println("### entriesToMap ###");
        List<Map<Character, Integer>> mapList = new ArrayList<>();
        mapList.add(
                Stream.of(
                        new AbstractMap.SimpleEntry<>('a', 1),
                        new AbstractMap.SimpleEntry<>('c', 2),
                        new AbstractMap.SimpleEntry<>('b', 3)
        ).collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));
        mapList.add(
                Stream.of(
                        new AbstractMap.SimpleEntry<>('c', 4),
                        new AbstractMap.SimpleEntry<>('b', 5),
                        new AbstractMap.SimpleEntry<>('a', 6),
                        new AbstractMap.SimpleEntry<>('d', 7)
        ).collect(toMap(Map.Entry::getKey, Map.Entry::getValue)));

        Map<Character, List<Integer>> characterListMap = entriesToMap(mapList);
        System.out.printf("%s -> %s%n", mapList, characterListMap);

        // ############################
        System.out.println("### takeWhile(not short-circuit) Java 8 ###");
        Stream.of(1, 2, 3, -3, 4, 5, 6).filter(takeWhile(x -> x > 0)).forEach(System.out::println);

        System.out.println("### takeWhile(short-circuit) Java 8 ###");
        takeWhile(Stream.of(1, 2, 3, -3, 4, 5, 6), x -> x > 0).forEach(System.out::println);
        takeWhile(new Random().ints().boxed(), x -> x % 10 != 0).forEach(System.out::println);

        // #####################################################################
        // # CREATE SOURCE GENERATING CARTESIAN PRODUCT OF THE LISTS OF STRINGS #
        System.out.println("#### CARTESIAN ####");
        List<List<String>> input = asList(
                asList("a", "b", "c"),
                asList("x", "y"),
                asList("1", "2", "3"));

        //base version
        input.get(0).stream().flatMap(a ->
        input.get(1).stream().flatMap(b ->
        input.get(2).stream().map(c -> a + b + c)))
        .forEach(System.out::println);

        //improved version
        Stream<Supplier<Stream<String>>> streamOfStreamSuplliers =
                input.stream()
                     .map(list -> list::stream);

        Supplier<Stream<String>> s = streamOfStreamSuplliers
                .reduce((sup1, sup2) ->
                        () -> sup1.get().flatMap(e1 -> sup2.get().map(e2 -> e1 + e2)))
                .orElse(() -> Stream.of(""));

        s.get().forEach(System.out::println);

        // ############################################################
        // ### LEAVE VALUES IN THE STREAM REPEATED AT LEAST N TIMES ###
        System.out.println("#### distinct ####");
        // variant I
        List<String> list = asList("Hello", "world", "Hello", "Java", "I", "like", "Java", "It's", "my", "world");
        Map<String, Long> counts = list.stream()
                                       .collect(groupingBy(Function.identity(), counting()));
        counts.values().removeIf(cnt -> cnt < 2);
        counts.keySet().forEach(System.out::println);

        System.out.println("############################################################");
        // variant II
        list.stream()
            .filter(distinct(2))
            .forEach(System.out::println);

    }

    private static class Group {
        private User[] users;
        private List<Mentor> mentors;
        private Map<String, Group> nameToGroups;

        public Stream<User> users() {
            return Arrays.stream(users);
        }

        public Stream<Group> neighbors() {
            return nameToGroups.values().stream();
        }

        public Stream<Mentor> mentors() {
            return mentors.stream();
        }
    }

    private static class User {
        String name;
    }

    private static class Mentor {
        String surName;
        int rating;
    }
}
