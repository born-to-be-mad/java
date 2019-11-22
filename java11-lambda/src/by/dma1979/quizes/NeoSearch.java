package by.dma1979.quizes;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
public class NeoSearch {
    public static void main(String... doYourBest) {
        List<String> ls = List.of("Neo", "Morpheus", "Oracle", "Trinity", "Neo");

        Predicate<String> neoSearch = str -> {
            System.out.println("Agent Smith is looking for Neo...");
            return str.contains("Neo");
        };

        var binaryNumbers = List.of(1, 0, 1, 1).stream();
        Integer binarySum =
            binaryNumbers.reduce(Integer::sum).orElseThrow(StackOverflowError::new);

        boolean neoFound = ls.stream()
            .filter(str -> str.length() >= binarySum)
            .allMatch(neoSearch);
        System.out.println(neoFound);
    }


}
