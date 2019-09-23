package recipes.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author dzmitry.marudau
 * @since 2019.9
 */
public class PredicateComposition {

    public static void main(String[] args) {
        final var items = List.of("Java", "Kotlin", "Spring", "Quarqus", "Kubernetes", "Loom", "Micronaut", "Maven");
        final List<String> orExample =
            items.stream()
                // Use the Predicate.or method to combine two Predicate objects.
                .filter(startsWith("K").or(startsWith("M")))
                .collect(Collectors.toUnmodifiableList());
        assert orExample.size() == 4 : "orExample contains 4 items";

        final List<String> negateExample =
            items.stream()
                // Find all values that do not start with "Gr"
                // and have less than 8 characters.
                .filter(startsWith("K").negate().and(smallerThan(8)))
                .collect(Collectors.toUnmodifiableList());
        assert negateExample.size() == 2 : "Predicate.negate example contains 2 items(Java, Kotlin)";

        final List<String> predicateNotExample =
            items.stream()
                // Find all values that do not start with "Gr",
                // using Predicate.not instead of negate,
                // and have less than 8 characters.
                .filter(Predicate.not(startsWith("K")).and(smallerThan(8)))
                .collect(Collectors.toUnmodifiableList());
        assert predicateNotExample.size() == 2 : "Predicate.not example contains 2 items(Java, Kotlin)";

    }

    // Create a predicate to check if String value starts with a given value.
    private static Predicate<String> startsWith(final String begin) {
        return s -> s.startsWith(begin);
    }

    // Create a predicate to check if String value has
    // less characters than the given size.
    private static Predicate<String> smallerThan(final int size) {
        return s -> size >= s.length();
    }
}
