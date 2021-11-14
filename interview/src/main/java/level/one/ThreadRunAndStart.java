package level.one;

public class ThreadRunAndStart implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadRunAndStart());
        // combination 1
        thread.setDaemon(true);
        thread.run();

        // combination 2
        /*
        thread.setDaemon(false);
        thread.run();
        */

        // combination 3
        /*
        thread.setDaemon(false);
        thread.start();
        */

        // combination 4
        /*
        thread.setDaemon(false);
        thread.start();
        thread.start();
        */

    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println("running…");
        }
    }

}
