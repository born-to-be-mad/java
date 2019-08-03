package recipes.predicate;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringPredicateUtils {
    public static final Predicate<String> LONG_LENGTH =
            s -> s.length() >= 6;

    public static final Predicate<String> STARTS_WITH_HASH =
            s -> s.startsWith("#");

    public static final Predicate<String> ENDS_WITH_HASH =
            s -> s.endsWith("#");

    public String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.length() == length)
                .collect(Collectors.joining(", "));
    }

    public String getNamesStartingWith(final String prefix, final String... names) {
        return Arrays.stream(names)
                .filter(s -> s.startsWith(prefix))
                .collect(Collectors.joining(", "));
    }

    public String getNamesSatisfyingCondition(final Predicate<String> condition, final  String... names) {
        return Arrays.stream(names)
                .filter(condition)
                .collect(Collectors.joining(", "));
    }

}

