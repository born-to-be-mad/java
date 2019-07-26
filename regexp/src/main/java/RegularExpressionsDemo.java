import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:50
 * @since : 2019.07
 **/
public class RegularExpressionsDemo {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "202", "A1", "1234", "A1B20C345");

        maches(numbers);

        replaceAll(numbers);

        patternAndMatcher(numbers);

        scannerFindAll(numbers);
    }

    private static void maches(List<String> numbers) {
        // Since Java 1.4
        for (String number : numbers) {
            if (number.matches("\\d+")) {
                System.out.println(number);        // 1, 20, 333
            }
        }

        // Java 8 stream
        numbers.stream()
                .filter(x -> x.matches("\\d+"))
                .forEach(System.out::println);
    }

    private static void replaceAll(List<String> numbers) {
        System.out.println("### Replace numbers via 'replaceAll'");
        for (String number : numbers) {
            System.out.println(number.replaceAll("\\d", "#"));
        }

        // Java 8 stream
        numbers.stream()
                .map(x -> x.replaceAll("\\d", "#"))
                .forEach(System.out::println);
    }

    private static void patternAndMatcher(List<String> numbers) {
        System.out.println("### Find numbers via Patterns and Matcher");
        Pattern pattern = Pattern.compile("\\d+");

        for (String number : numbers) {
            Matcher matcher = pattern.matcher(number);
            while (matcher.find()) {
                System.out.println(matcher.group(0));
            }
        }

        // Java 8 stream
        numbers.stream()
                .flatMap(x ->
                        StreamSupport.stream(new MatchItr(pattern.matcher(x)), false))
                .forEach(System.out::println);
    }

    //This is relevant from Java 9
    private static void scannerFindAll(List<String> numbers) {
        System.out.println("### Find numbers via Scanner.findAll");
        Pattern pattern = Pattern.compile("\\d+");

        List<String> collect = numbers.stream()
                .map(x -> new Scanner(x)
                                .findAll(pattern)
                                .map(MatchResult::group)
                                .collect(Collectors.toList())
                )
                .flatMap(List::stream)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    //The stream can’t loop the .filter to get all the groups, we need a custom Spliterators
    final static class MatchItr extends Spliterators.AbstractSpliterator<String> {
        private final Matcher matcher;

        MatchItr(Matcher m) {
            super(m.regionEnd() - m.regionStart(), ORDERED | NONNULL);
            matcher = m;
        }

        public boolean tryAdvance(Consumer<? super String> action) {
            if (!matcher.find()) return false;
            action.accept(matcher.group());
            return true;
        }
    }
}
