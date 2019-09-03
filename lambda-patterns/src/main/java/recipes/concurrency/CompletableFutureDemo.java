package recipes.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:13
 * @since : 2019.09
 **/
public class CompletableFutureDemo {
    private Map<Integer, Product> cache = new HashMap<>();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        CompletableFuture.supplyAsync(CompletableFutureDemo::sleepThenReturnString, service)
                .thenApply(Integer::parseInt)
                .thenApply(x -> x * x)
                .thenAccept(System.out::println)
                .join();
        ForkJoinPool.commonPool().awaitQuiescence(2, TimeUnit.SECONDS);
        System.out.println("Calculating...");
    }

    public CompletableFuture<Product> getProduct(int id) {
        try {
            Product product = getLocal(id);
            if (product != null) {
                return CompletableFuture.completedFuture(product);
            } else {
                CompletableFuture<Product> future = new CompletableFuture<>();
                Product p = getRemote(id);
                cache.put(id, p);
                future.complete(p);
                return future;
            }
        } catch (Exception e) {
            CompletableFuture<Product> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

    public CompletableFuture<Product> getProductAsync(int id) {
        try {
            Product product = getLocal(id);
            if (product != null) {
                logger.info("getLocal with id=" + id);
                return CompletableFuture.completedFuture(product);
            } else {
                logger.info("getRemote with id=" + id);
                return CompletableFuture.supplyAsync(() -> {
                    Product p = getRemote(id);
                    cache.put(id, p);
                    return p;
                });
            }
        } catch (Exception e) {
            logger.info("exception thrown");
            CompletableFuture<Product> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;

        }
    }

    private Product getLocal(int id) {
        return cache.get(id);
    }

    private Product getRemote(int id) {
        try {
            //Simulate a delay followed by a retrieval
            Thread.sleep((long) (Math.random() * 1000));
            //Simulate a network, database, or other kind of error
            if (id % 3 == 0) {
                throw new RuntimeException("Bad request");
            }
        } catch (InterruptedException ignored) {
        }
        return new Product(id, "name");
    }

    private static String sleepThenReturnString() {
        long randomNumber = 5 + (long) (Math.random() * 10);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
        return String.valueOf(randomNumber);
    }
}
