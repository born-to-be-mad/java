package recipes.comparator;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * When you need to implement java.util.stream.Collector manually, because none of the
 * factory methods in the java.util.stream.Collectors class handle your needs.
 *
 * @author : Dzmitry Marudau
 * @created at : 10:29
 * @since : 2019.07
 **/
public class ImplementCollectorInterfaceDemo {
    //Collectors use five functions(SUPPLIER , ACCUMULATOR , COMBINER , FINISHER , and CHARACTERISTICS)
    //that work together to accumulate entries into a
    //mutable container and optionally transform the result.
    public static void main(String[] args) {
        String[] names = {"Herman", "Dima", "Max", "Stanislav", "Alexandr", "Serg", "Clemens", "Jurgen"};

        System.out.println("### STANDARD COLLECTOR in ACTION ###");
        evenLengthStringsViaStandardCollector(names).forEach(System.out::println);

        System.out.println("### OWN COLLECTOR in ACTION ###");
        oddLengthStringSet(names).forEach(System.out::println);

    }

    private static List<String> evenLengthStringsViaStandardCollector(String... strings) {
        return Stream.of(strings)
                .filter(s -> s.length() % 2 == 0)
                .sorted()
                .collect(Collectors.toList());
    }

    private static SortedSet<String> oddLengthStringSet(String... strings) {
        return Stream.of(strings)
                .filter(s -> s.length() % 2 != 0)
                .collect(intoSortedSetCollector());
    }

    //The result will be a sorted, unmodifiable set of strings, ordered lexicographically.
    private static Collector<String, ?, SortedSet<String>> intoSortedSetCollector() {
        return Collector.of(
                TreeSet<String>::new, //SUPPLIER to create a new TreeSet
                SortedSet::add, //BiConsumer(ACCUMULATOR) to add each string to the TreeSet
                (left, right) -> { //BinaryOperator(COMBINER) to combine two SortedSet instances into one
                    left.addAll(right);
                    return left;
                },
                Collections::unmodifiableSortedSet); //FINISHER function to create an unmodifiable set
        //CHARACTERISTICS is not present here, ut it can be passed as last parameter
        // it represents an immutable Set of elements of an enum type Collector.Characteristics:
        //CONCURRENT, IDENTITY_FINISH, UNORDERED
    }
}
