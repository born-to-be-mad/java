package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static java.util.Arrays.asList;

/**
 * How to solve different tasks via Stream API based on best practices.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class StreamBestUtils {

    /**
     * Create the source of children nodes for parent DOM XML node.
     *
     * @param parent the parent npde
     * @return the stream of children
     */
    public static Stream<Node> children(Node parent) {
        NodeList nodeList = parent.getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                        .mapToObj(nodeList::item);
    }

    /**
     * Create the source of list elements with indexes.
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
        Stream<Element> filteredResult3 =
                select(
                        IntStream.range(0, list.getLength())
                                 .mapToObj(list::item),
                        Element.class
                );

        // approach IV: best practices
        Stream<Element> filteredResult4 =
                IntStream.range(0, list.getLength())
                         .mapToObj(list::item)
                         .flatMap(select(Element.class));
        return filteredResult4;
    }

    public static <T, TT> Stream<TT> select(Stream<T> stream, Class<TT> clazz) {
        return stream.filter(clazz::isInstance)
                     .map(clazz::cast);
    }

    public static <T, TT> Function<T, Stream<TT>> select(Class<TT> clazz) {
        return e -> clazz.isInstance(e) ? Stream.of(clazz.cast(e)) : null;
    }

    public static <T> Predicate<T> distinct(long atLeast) {
        ConcurrentHashMap<T, Long> map = new ConcurrentHashMap<>();
        return t -> map.merge(t, 1L, Long::sum) == atLeast;
    }

    /**
     * Not short-circuit takeWhile implementation.
     * @param predicate input predicate
     * @param <T> type of pbjects
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
     * @param stream input stream
     * @param predicate input predicate
     * @param <T> type of pbjects
     * @return result stream
     */
    public static <T> Stream<T> takeWhile(Stream<T> stream, Predicate<T> predicate) {
        Spliterator<T> src = stream.spliterator();
        Spliterator<T> res = new Spliterators.AbstractSpliterator<T>(
                src.estimateSize(),
                src.characteristics() & ~Spliterator.SIZED & ~Spliterator.SUBSIZED) {

            boolean finished = false;
            T next;

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
        return StreamSupport.stream(res, stream.isParallel())
                            .onClose(stream::close);

    }

    public static void main(String[] args) {
        // ############################
        System.out.println("### takeWhile(not short-circuit) Java 8 ###");
        Stream.of(1, 2, 3, -3, 4, 5, 6)
              .filter(takeWhile(x -> x > 0))
              .forEach(System.out::println);

        System.out.println("### takeWhile(short-circuit) Java 8 ###");
        takeWhile(Stream.of(1, 2, 3, -3, 4, 5, 6), x -> x > 0)
                .forEach(System.out::println);

        // #####################################################################
        // # CREATE SOURCE GENERATING CARTESIAN PRODUCT OF THE LIST OF STRINGS #
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
        Supplier<Stream<String>> s =
                input.stream()
                // Stream<List<String>>
                .<Supplier<Stream<String>>>map(list -> list::stream)
                // Stream <Supplier<Stream<List<String>>>>
                .reduce((sup1, sup2) -> () -> sup1.get().flatMap(e1 -> sup2.get()
                                                                           .map(e2 -> e1 + e2)))
                .orElse(() -> Stream.of(""));

        s.get().forEach(System.out::println);

        System.out.println("############################################################");
        // ############################################################
        // ### LEAVE VALUES IN THE STREAM REPEATED AT LEAST N TIMES ###
        // variant I
        List<String> list = asList("Hello", "world",
                                   "Hello", "Java",
                                   "I", "like", "Java",
                                   "It's", "my", "world");
        Map<String, Long> counts =
                list.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        counts.values()
              .removeIf(cnt -> cnt < 2);
        counts.keySet().forEach(System.out::println);

        System.out.println("############################################################");
        // variant II
        list.stream().filter(distinct(2)).forEach(System.out::println);

    }
}


class Group {
    private User[] users;
    private List<Mentor> mentors;
    private Map<String, Group> nameToGroups;

    public Stream<User> users() {
        return Arrays.stream(users);
    }

    public Stream<Group> neighbors() {
        return nameToGroups.values()
                           .stream();
    }

    public Stream<Mentor> mentors() {
        return mentors.stream();
    }
}

class User {
    String name;
}

class Mentor {
    String surName;
    int rating;
}
