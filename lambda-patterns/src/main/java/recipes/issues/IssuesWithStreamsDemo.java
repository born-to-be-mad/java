package recipes.issues;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:18
 * @since : 2019.07
 **/
public class IssuesWithStreamsDemo {

    private static final Logger LOG = Logger.getLogger(IssuesWithStreamsDemo.class.getName());

    public static void main(String[] args) throws IOException {
        configureLogger();

        LOG.info("Logger Name: " + LOG.getName());

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

        System.out.println("### Count words using the merge method");
        fullWordCounts(phrase)
                .forEach((word, count) -> System.out.println(word + "=" + count));

        LOG.fine(() -> fullWordCounts(phrase).toString());
    }

    private static void configureLogger() throws IOException {
        // Create a file handler object
        FileHandler handler = new FileHandler("logs.txt");
        handler.setFormatter(new SimpleFormatter());

        // Add file handler as handler of logs
        LOG.addHandler(handler);

        // Set Logger level(
        LOG.setLevel(Level.FINE);
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
        Arrays.stream(phrase.split("\\s+"))
                .forEach(word ->
                        wordCounts.computeIfPresent(word, (key, val) -> val + 1));
        return wordCounts;
    }

    private static Map<String, Integer> fullWordCounts(String phrase) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String testString = phrase.toLowerCase().replaceAll("\\W", " ");
        Arrays.stream(testString.split("\\s+"))
                .forEach(word -> wordCounts.merge(word, 1, Integer::sum));
        return wordCounts;
    }
}
