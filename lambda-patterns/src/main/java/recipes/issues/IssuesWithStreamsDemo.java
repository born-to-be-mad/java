package recipes.issues;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:18
 * @since : 2019.07
 **/
public class IssuesWithStreamsDemo {
    public static void main(String[] args) {
        System.out.println("### Stream of random numbers ###");
        createStreamOfRandomNumbers(new Random());
        System.out.println("### Stream of ecure random numbers ###");
        createStreamOfRandomNumbers(new SecureRandom());
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
}
