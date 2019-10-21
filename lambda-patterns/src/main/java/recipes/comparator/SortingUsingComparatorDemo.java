package recipes.comparator;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:50
 * @since : 2019.07
 **/
public class SortingUsingComparatorDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
                "better", "lean", "piece", "than", "fat", "victory");

        List<String> sortedStringsByDefault = strings.stream()
                .sorted()
                .collect(toList());
        System.out.printf("Sort string by default:%s%n", sortedStringsByDefault);

        List<String> sortedStrings = strings.stream()
                .sorted(comparingInt(String::length))
                .collect(toList());
        System.out.printf("Sort by length using comparator:%s%n", sortedStrings);

        List<String> sortedStringsByLengthAndNatural = strings.stream()
                .sorted(comparingInt(String::length)
                        .thenComparing(naturalOrder()))
                .collect(toList());
        System.out.printf("Sort by length, then equal lengths lexicographically:%s%n", sortedStringsByLengthAndNatural);

        List<Player> players = Arrays.asList(
                new Player("Erik", "Cantona", 68),
                new Player("Roberto", "Carlos", 76),
                new Player("Alessandro", "Del Piero", 70),
                new Player("Leonel", "Messi", 103),
                new Player("Christian", "Ronaldo", 97));
        List<Player> sortByScoreThenLastThenFirst = players.stream()
                .sorted(comparingInt(Player::getScore).reversed()
                        .thenComparing(Player::getLast)
                        .thenComparing(Player::getFirst))
                .collect(toList());

        System.out.printf("Sort objects not implemented Comparable:%s%n", sortByScoreThenLastThenFirst);
    }

}

