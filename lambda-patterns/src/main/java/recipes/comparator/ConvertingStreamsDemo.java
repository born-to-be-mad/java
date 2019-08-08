package recipes.comparator;

import java.util.*;
import java.util.function.BinaryOperator;
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

        HashSet<String> setOfWords =
            Stream.of(("Better lean piece than fat victory. "
                           + "A new broom sweeps clean, but please go fifty-fifty! "
                           + "Like will to like; let bygones be bygones ").split("[\\s.,;!?]+"))
                .collect(Collectors.toCollection(HashSet::new));
        System.out.printf("Create set of strings from string:%s%n", setOfWords.toString());

        System.out.println("### CREATE MAP FROM STREAM ###");
        List<Player> players = Arrays.asList(
                new Player("Erik", "Cantona", 68).setClub(Club.MU),
                new Player("Rayan", "Gicks", 95).setClub(Club.MU),
                new Player("Paul", "Drogba", 46).setClub(Club.MU),
                new Player("Roberto", "Carlos", 76).setClub(Club.REAL_MADRID),
                new Player("Alessandro", "Del Piero", 70).setClub(Club.JUVENTUS),
                new Player("Leonel", "Messi", 103).setClub(Club.BARCELONA),
                new Player("Gerard", "Piquet", 55).setClub(Club.BARCELONA),
                new Player("Christian", "Ronaldo", 97).setClub(Club.JUVENTUS));
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
        System.out.println("######");

        Optional<Player> playerWithMaxScoreBinaryOperator = players.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingInt(Player::getScore)));
        System.out.println("Player with max score(BinaryOperator.maxBy): " + playerWithMaxScoreBinaryOperator);
        Optional<Player> playerWithMaxScoreStreamMax = players.stream()
                .max(Comparator.comparingInt(Player::getScore));
        System.out.println("Player with max score(Stream.max): " + playerWithMaxScoreStreamMax);

        OptionalInt maxScore = players.stream()
                .mapToInt(Player::getScore)
                .max();
        System.out.println("Max score(BinaryOperator.maxBy): " + maxScore);

        System.out.println("###### Using Collectors.maxBy as a downstream collector ###");
        Map<Club, Optional<Player>> map = players.stream()
                .collect(Collectors.groupingBy(
                        Player::getClub,
                        Collectors.maxBy(Comparator.comparingInt(Player::getScore))));
        map.forEach((club, player) -> System.out.println(club + ": " + player));
    }
}
