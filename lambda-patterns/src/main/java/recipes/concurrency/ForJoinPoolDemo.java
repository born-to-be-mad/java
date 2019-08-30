package recipes.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:14
 * @since : 2019.08
 **/
public class ForJoinPoolDemo {

    public static void main(String[] args) {
        specifyPoolParameters();
        createOwnJoinPool();
    }

    static void specifyPoolParameters() {
        System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());

        System.setProperty(
                "java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        int poolSize = ForkJoinPool.commonPool().getPoolSize();
        System.out.println("Pool size: " + poolSize);

        int parallelism = ForkJoinPool.commonPool().getParallelism();
        System.out.println("Parallelism: " + parallelism);
    }

    static void createOwnJoinPool() {
        ForkJoinPool pool = new ForkJoinPool(15);
        int parallelism = pool.getParallelism();
        System.out.println("Custom parallelism after ForkJoinPool: " + parallelism);
        ForkJoinTask<Long> task = pool.submit(
                () -> LongStream.rangeClosed(1, 3_000_000)
                        .parallel()
                        .sum());

        long total;
        try {
            total = task.get();
            System.out.println("Sum of 3 millions elements:" + total);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}
