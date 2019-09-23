package by.dma1979.challendges;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class CompletableFutureChallenge {
    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String... oracleCodeOneAdventure) {
        CompletableFuture<List<String>> adventureStart = new CompletableFuture<>();

        Supplier<List<String>> sanFranSightSupplier = () ->
            List.of("Alcatraz", "Cable Car", "Golden Gate", "Lombard Street");

        adventureStart.completeAsync(sanFranSightSupplier, executor)
            .thenCompose(sights -> {
                return CompletableFuture.supplyAsync(() -> sights.stream()
                    .map(String::length)
                    .collect(Collectors.toList()));
            })
            .thenAccept(ratings -> {
                var rating = ratings.stream()
                    .dropWhile(sightRating -> sightRating <= 12)
                    .findFirst()
                    .orElse(0);
                System.out.print("Rating: " + rating + " ");
            });
        System.out.print("time to go home :( ");
    }

}
