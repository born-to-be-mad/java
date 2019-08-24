package recipes.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:45
 * @since : 2019.08
 **/
public class ParallelSteamUsageDemo {

    private static int squareIt(int n) {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException ignore) {
        }
        return n * n;
    }

    private static int squareAndSumSequential() {
        return IntStream.of(3, 1, 4, 1, 5, 9)
                .map(ParallelSteamUsageDemo::squareIt)
                .sum();
    }

    private static int squareAndSumParallel() {
        return IntStream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .map(ParallelSteamUsageDemo::squareIt)
                .sum();
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        int total = squareAndSumSequential();
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.printf("Total of squares = %d sequentially calculated for %s ms %n", total, duration.toMillis());

        start = Instant.now();
        total = squareAndSumParallel();
        end = Instant.now();
        duration = Duration.between(start, end);
        System.out.printf("Total of squares = %d calculated in parallel for %s ms %n", total, duration.toMillis());
    }
}
