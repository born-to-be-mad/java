package by.dma.synchronizers.phaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/**
 * Есть пять остановок. На первых четырех из них могут стоять пассажиры и ждать автобуса.
 * Автобус выезжает из парка и останавливается на каждой остановке на некоторое время.
 * После конечной остановки автобус едет в парк.
 * Нам нужно забрать пассажиров и высадить их на нужных остановках.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class Bus {
    private static final Phaser PHASER = new Phaser(1); //Сразу регистрируем главный поток
    //Фазы 0 и 6 - это автобусный парк, 1 - 5 остановки

    public static void main(String[] args) throws InterruptedException {
        List<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {           //Сгенерируем пассажиров на остановках
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, i + 1)); //Этот пассажир выходит на следующей
            }

            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, 5));    //Этот пассажир выходит на конечной
            }
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    System.out.println("Автобус выехал из парка.");
                    PHASER.arrive(); //В фазе 0 всего 1 участник - автобус
                }
                case 6 -> {
                    System.out.println("Автобус уехал в парк.");
                    PHASER.arriveAndDeregister(); //Снимаем главный поток, ломаем барьер
                }
                default -> {
                    int currentBusStop = PHASER.getPhase();
                    System.out.println("Остановка № " + currentBusStop);
                    for (Passenger p : passengers)          //Проверяем, есть ли пассажиры на остановке
                        if (p.departure == currentBusStop) {
                            PHASER.register(); //Регистрируем поток, который будет участвовать в фазах
                            p.start();         // и запускаем
                        }
                    PHASER.arriveAndAwaitAdvance(); //Сообщаем о своей готовности
                }
            }
        }
    }

    public static class Passenger extends Thread {
        private final int departure;
        private final int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.println(this + " ждёт на остановке № " + this.departure);
        }

        @Override
        public void run() {
            try {
                System.out.println(this + " сел в автобус.");

                while (PHASER.getPhase() < destination) //Пока автобус не приедет на нужную остановку(фазу)
                    PHASER.arriveAndAwaitAdvance();     //заявляем в каждой фазе о готовности и ждем

                Thread.sleep(1);
                System.out.println(this + " покинул автобус.");
                PHASER.arriveAndDeregister();   //Отменяем регистрацию на нужной фазе
            } catch (InterruptedException e) {
                System.out.println("Exception:" + e);
            }
        }

        @Override
        public String toString() {
            return "Пассажир{" + departure + " -> " + destination + '}';
        }
    }
}
