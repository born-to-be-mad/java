package by.dma.challenge.java8;

import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Quiz on Functions: how to use Functions and use their methods.
 * <p>
 *
 * <h3>Answers</h3>
 * <ol>
 * <li>DarthVader Luke ObiWan Palpatine QuiGonJinn</li>
 * <li>NoSuchElementException will be thrown at the line 11 </li>
 * <li>QuiGonJinn Palpatine ObiWan Luke DarthVader</li>
 * <ul/>
 *
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class FunctionChallenge {

    public static void main(String... doYourBest) {
        String starWars = "Luke DarthVader ObiWan QuiGonJinn Palpatine";

        Function<String, Stream<String>> lineSplitter =
            tst -> Pattern.compile(" ").splitAsStream(tst); // Line 11

        Stream.of(starWars)
              .flatMap(lineSplitter)
              .sorted((o1, o2) -> o2.compareTo(o1)) // equal to Comparator.reverseOrder()
              .forEachOrdered(System.out::println);
    }

}
