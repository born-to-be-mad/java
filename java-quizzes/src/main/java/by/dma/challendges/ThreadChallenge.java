package by.dma.challendges;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class ThreadChallenge {

    private static int wolverineAdrenaline = 10;

    public static void main(String... doYourBest) throws InterruptedException {
        ThreadChallenge thread = new ThreadChallenge();

        //which choice(s) always display "Yamaha YZF"

        // ### Choice 1 ###
        /*Motorcycle yamaha = new Motorcycle("Yamaha");
        yamaha.start();
        yamaha.start();
        yamaha.start();*/

        // ### Choice 2 ###
/*        Motorcycle harley = new Motorcycle("Harley");
        harley.start();
        harley.join();

        Motorcycle bmw = new Motorcycle("BMW");
        bmw.setPriority(Thread.MAX_PRIORITY);
        bmw.setDaemon(false);
        bmw.start();
        bmw.join();

        Motorcycle yamaha = new Motorcycle("Yamaha YZ");
        yamaha.setPriority(Thread.MIN_PRIORITY);
        yamaha.start();
        yamaha.join();*/

        // ### Choice 3 ###
/*        new Motorcycle("Harley").start();

        Motorcycle bmw = new Motorcycle("BMW");
        bmw.setPriority(Thread.MAX_PRIORITY);
        bmw.setDaemon(false);
        bmw.start();
        bmw.join();

        Motorcycle yamaha = new Motorcycle("Yamaha");
        yamaha.setPriority(Thread.MIN_PRIORITY);
        yamaha.start();
        yamaha.join();*/

        Motorcycle harley = new Motorcycle("Harley");
        harley.start();
        harley.setDaemon(false);

        Motorcycle bmw = new Motorcycle("BMW");
        bmw.setPriority(Thread.MAX_PRIORITY);
        bmw.setDaemon(false);
        bmw.start();
        bmw.setDaemon(false);

        Motorcycle yamaha = new Motorcycle("Yamaha");
        yamaha.setPriority(Thread.MIN_PRIORITY);
        yamaha.start();
        yamaha.setDaemon(false);


        System.out.println(thread.wolverineAdrenaline);
    }

    static class Motorcycle extends Thread {
        Motorcycle(String bikeName) {
            super(bikeName);
        }

        @Override
        public void run() {
            wolverineAdrenaline++;
            if (wolverineAdrenaline == 13) {
                System.out.println(this.getName());
            }
        }
    }
}
