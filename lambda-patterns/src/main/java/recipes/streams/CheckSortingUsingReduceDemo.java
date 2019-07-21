package recipes.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Dzmitry Marudau
 * @created at : 10:06
 * @since : 2019.07
 **/
public class CheckSortingUsingReduceDemo {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList(
                "Health", "is", "better", "than", "Wealth");
        List<String> sortedStrings = strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.printf("Sorted strings:%s%n", sortedStrings);

        //Testing that result strings are sorted properly
        sortedStrings.stream()
                .reduce((prev, curr) -> {
                    //assertTrue
                    if (prev.length() > curr.length()) {
                        throw new IllegalArgumentException("Strings are not sorted");
                    }
                    return curr;
                });
    }

}
