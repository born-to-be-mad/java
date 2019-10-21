package recipes.issues;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:25
 * @since : 2019.08
 **/
public class ClosureCompositionDemo {
    private static final Logger LOG = Logger.getLogger(ClosureCompositionDemo.class.getName());

    public static void main(String[] args) throws IOException {
        configureLogger();
        System.out.println("### COMPOSITION METHODS IN FUNCTION ###");
        System.out.println("Parse then square the number by composition");
        Function<Integer, Integer> squareFunction = x -> x * x;
        Function<String, Integer> parseThenSquare = squareFunction.compose(Integer::parseInt);
        System.out.println(parseThenSquare.apply("5"));

        System.out.println("Square the number and then convert to string by 'andThen'");
        Function<Integer, String> squareAndConvertToString = squareFunction.andThen(String::valueOf);
        System.out.println(squareAndConvertToString.apply(5));

        System.out.println("### Log to console and log-file");
        Consumer<String> printToConsole = System.out::println;
        Consumer<String> logToFile = LOG::info;
        Consumer<String> printThenLog = printToConsole.andThen(logToFile);
        Stream.of("Let", "bygones", "br", "bygones").forEach(printThenLog);

        System.out.println("### COMPOSITION METHODS IN PREDICATE ###");
        System.out.println("### Numbers divided b 13 and 17");
        IntPredicate firstPredicate = ClosureCompositionDemo::isDividedBy17;
        IntPredicate secondPredicate = ClosureCompositionDemo::isDividedBY13;
        IntPredicate both = firstPredicate.and(secondPredicate);
        IntStream.rangeClosed(1, 1_000)
                .filter(both)
                .forEach(System.out::println);
    }

    private static boolean isDividedBy17(final int number) {
        return number % 17 == 0;
    }

    private static boolean isDividedBY13(final int number) {
        return number % 13 == 0;
    }

    private static void configureLogger() throws IOException {
        // Create a file fileHandler object
        // check if logs dir exists
        File logDir = new File("./logs/");
        if (!(logDir.exists())) {
            logDir.mkdir();
        }
        FileHandler fileHandler = new FileHandler("logs/run.log");
        fileHandler.setFormatter(new SimpleFormatter());

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        // Add file fileHandler as fileHandler of logs
        LOG.addHandler(fileHandler);
        LOG.addHandler(consoleHandler);

        // Set Logger level(
        LOG.setLevel(Level.WARNING);
    }
}
