package by.dma.synchronizers.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Demo for {@code CyclicBarrier}.
 * See {@linkplain https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Ferry {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
    //у барьера соберется три потока. После этого, они будут освобождены.

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    //Таск, который будет выполняться при достижении сторонами барьера
    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Паром с автомобилями начал переправу...");
                Thread.sleep(500);
                System.out.println("Паром переправил автомобили!");
            } catch (InterruptedException e) {
                System.out.println("Exception:" + e);
            }
        }
    }

    //Стороны, которые будут достигать барьера
    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
                BARRIER.await();
                System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
            } catch (Exception e) {
                System.out.println("Exception:" + e);
            }
        }
    }
}
