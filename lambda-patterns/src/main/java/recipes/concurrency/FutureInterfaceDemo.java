package recipes.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:33
 * @since : 2019.08
 **/
public class FutureInterfaceDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Asynchronous future result.";
            }
        });
        Future<String> futureLambda = service.submit(() -> {
            Thread.sleep(1000);
            return "Asynchronous future lambda result.";
        });
        System.out.println("Processing...");

        getIfNotCancelled(futureLambda);

        System.out.print("Waiting");
        //future.cancel(true);
        while (!future.isDone()) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        System.out.println();

        getIfNotCancelled(future);

    }

    private static void getIfNotCancelled(Future<String> future) {
        try {
            if (future.isCancelled()) {
                System.out.println("Future cancelled");
            } else {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
