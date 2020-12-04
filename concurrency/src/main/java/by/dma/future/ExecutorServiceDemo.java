package by.dma.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import by.dma.task.Task;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:20
 * @since : 2020.12
 **/
public class ExecutorServiceDemo {
  // Maximum number of threads in thread pool
  static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() + 1;

  public static void main(String[] args) {
    System.out.println("### Creating pool with size = " + MAX_THREADS);
    ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

    IntStream.rangeClosed(1, 10)
             .forEach(value -> pool.execute(new Task("task " + value)));

    pool.shutdown();
  }
}
