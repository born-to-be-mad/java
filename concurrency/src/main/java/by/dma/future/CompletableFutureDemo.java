package by.dma.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:50
 * @since : 2020.12
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.runAsync(() -> System.out.println("runAsync: started")).thenRunAsync(
                () -> System.out.println("simple asynchronous execution")).thenRunAsync(
                () -> System.out.println("runAsync: finished"));

        CompletableFuture<Void> supplyAsync = CompletableFuture.supplyAsync(() -> "supplyAsync: start...").thenApply(
                ((s) -> s + "simple asynchronous execution")).thenAccept(System.out::println);
            supplyAsync.get();

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "Hello")
                                 .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World!"));
        System.out.println(future.get());

        CompletableFuture<Void> futureVoid =
                CompletableFuture.supplyAsync(() -> "Hello")
                                 .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " Java World!"),
                                                 (s1, s2) -> System.out.println(s1 + s2));

        Future<String> futureResult = new CompletableFutureDemo().calculateAsync();
        System.out.println(futureResult.get());

        System.out.println("### RUNNING IN PARALLEL ###");
        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> "Hello"),
                                          CompletableFuture.supplyAsync(() -> "Beautiful"),
                                          CompletableFuture.supplyAsync(() -> "World"));
        System.out.println("Combined result:" + combinedFuture.get());

        String combined = Stream.of(CompletableFuture.supplyAsync(() -> "Hello"),
                                    CompletableFuture.supplyAsync(() -> "Beautiful"),
                                    CompletableFuture.supplyAsync(() -> "World"))
                                .map(CompletableFuture::join)
                                .collect(joining(" "));
        System.out.println(combined);


    }

    public Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Computing...");
            return null;
        });

        return completableFuture;
    }
}

class SquareCalculator {

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    //private ExecutorService executor = Executors.newFixedThreadPool(2);

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}

class FactorialSquareCalculator extends RecursiveTask<Integer> {

    private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);
        calculator.fork();

        return n * n + calculator.join();
    }
}
