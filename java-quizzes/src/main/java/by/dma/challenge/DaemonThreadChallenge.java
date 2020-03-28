package by.dma.challenge;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
public class DaemonThreadChallenge implements Runnable {
    public static void main(String... doYourBest) {
        Thread thread = new Thread(new DaemonThreadChallenge());


        /*
        There are two types of Threads, non-daemon, and daemon
        The Thread of the main method is non-daemon, that means that the instructions inside this method will be executed until the end.
        // the looping will not be executed forever(till all non-daemon Threads be finished.)
        thread.setDaemon(false);
        thread.start();
        */

        /*
        Setting up this instance of a Thread to non-daemon. In that case, the for looping will be executed forever.
        thread.setDaemon(true);
        thread.start();
        */

        /*Although the instance of this Thread is a daemon, there is no Thread being started, there is no second process being executed. we are only invoking the run that is inside the main Thread. The looping from the run method will be executed forever.*/
        thread.setDaemon(true);
        thread.run();

        /*
        The IllegalThreadStateException will be thrown. The same Thread can't be started twice.
        There is no way of the same Thread being executed twice. It wouldn't make sense.
        In order to execute the same Thread again, we would have to instantiate a new Thread.
        */
        thread.setDaemon(true);
        thread.start();
        thread.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println("For ever");
        }
    }

}
