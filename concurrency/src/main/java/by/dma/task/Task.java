package by.dma.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Dzmitry Marudau
 * @created at : 01:11
 * @since : 2020.12
 **/
public class Task implements Runnable {
  private static final int MS_BETWEEN_CALCULATIONS = 1000;
  private final String name;

  public Task(String name) {
    this.name = name;
  }

  public void run() {
    try {
      System.out.printf("Starting task - %s at %s %n",
                        name, LocalTime.now().format(DateTimeFormatter.ISO_TIME));
      for (int i = 0; i <= 5; i++) {
        System.out.printf("Executing task - %s at %s %n",
                          name, LocalTime.now().format(DateTimeFormatter.ISO_TIME));
      }
      Thread.sleep(MS_BETWEEN_CALCULATIONS);
      System.out.printf("Task #%s# completed%n", name);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
