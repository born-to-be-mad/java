package by.dma.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import by.dma.entity.BankAccount;
import by.dma.entity.SlowBankAccount;

/**
 * Deadlock sample.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DeadlockDemo {
    public static final int REPEATS = 10_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("availableProcessors=" + Runtime.getRuntime().availableProcessors());
      /*
        BankAccount source = new BankAccount(1_000);
        BankAccount destination = new BankAccount(1_000);
      */

        BankAccount source = new SlowBankAccount(1_000);
        BankAccount destination = new BankAccount(1_000);

        DeadlockTester tester = new DeadlockTester();
        tester.checkThatNoDeadLocks(() -> {
            ExecutorService pool = Executors.newFixedThreadPool(2);
            Future<?> transferTo = pool.submit(() -> {
                for (int i = 0; i < REPEATS; i++) {
                    source.transferTo(destination, 100);
                }
            });
            Future<?> transferBack = pool.submit(() -> {
                for (int i = 0; i < REPEATS; i++) {
                    destination.transferTo(source, 100);
                }
            });
            try {
                transferTo.get();
                transferBack.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pool.shutdown();
        }
        );
    }
}
