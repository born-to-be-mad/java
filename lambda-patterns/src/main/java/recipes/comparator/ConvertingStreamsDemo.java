package recipes.comparator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:33
 * @since : 2019.07
 **/
public class ConvertingStreamsDemo {

    public static void main(String[] args) {
        String[] words =
                Stream.of("Let", "bygones", "be", "bygones")
                        .toArray(String[]::new);
        System.out.printf("Create an array from stream:%s%n", Arrays.toString(words));

        System.out.println("### CREATE MAP FROM STREAM ###");
        List<Player> players = Arrays.asList(
                new Player("Erik", "Cantona", 68),
                new Player("Roberto", "Carlos", 76),
                new Player("Alessandro", "Del Piero", 70),
                new Player("Leonel", "Messi", 103),
                new Player("Christian", "Ronaldo", 97));
        Map<String, Integer> playerMap = players.stream()
                .collect(Collectors.toMap(Player::getFirst, Player::getScore));
        playerMap.forEach((key, value) ->
                System.out.printf("%s scored %s%n", key, value));
        System.out.println("######");

        Map<String, Player> playersByLastName = players.stream()
                .collect(Collectors.toMap(Player::getLast, player -> player));
        playersByLastName.forEach((key, value) ->
                System.out.printf("%s = %s%n", key, value));
        System.out.println("######");

        playersByLastName = players.stream()
                .collect(Collectors.toMap(Player::getLast, Function.identity()));
        playersByLastName.forEach((key, value) ->
                System.out.printf("%s = %s%n", key, value));
    }
}
