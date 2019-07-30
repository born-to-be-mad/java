package recipes.issues;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:18
 * @since : 2019.07
 **/
public class IssuesWithStreamsDemo {
    public static void main(String[] args) {
        System.out.println("### Stream of random numbers ###");
        createStreamOfRandomNumbers(new Random());
        System.out.println("### Stream of secure random numbers ###");
        createStreamOfRandomNumbers(new SecureRandom());

        System.out.println("### Fibonachi calculation with cache ###");
        FibonaciProducer fibonaciProducer = new FibonaciProducer();
        IntStream.rangeClosed(1, 10)
                .forEach(n -> System.out.printf("%d fibonachi = %s%n", n, fibonaciProducer.get(n)));

        System.out.println("### Count words in the phrase ###");
        String phrase = "To be or not to be? That is the question." +
                "Be brave. Be strong. Be human. Bob is your uncle. Bob Marley.";
        Map<String, Integer> counts = countWords(phrase, "Be", "is", "Bob");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));
    }

    private static void createStreamOfRandomNumbers(Random randomGenerator) {
        randomGenerator.doubles(5, 0.1, 0.2)
                .sorted()
                .forEach(System.out::println);
        List<Long> longs = randomGenerator.longs(5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);
        List<Integer> listOfInts = randomGenerator.ints(5, 4, 10)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println(listOfInts);
    }

    private static Map<String, Integer> countWords(String phrase, String... words) {
        Map<String, Integer> wordCounts = new HashMap<>();
        Arrays.stream(words).forEach(s -> wordCounts.put(s, 0));
        Arrays.stream(phrase.split("\\s"))
                .forEach(word ->
                        wordCounts.computeIfPresent(word, (key, val) -> val + 1));
        return wordCounts;
    }
}
