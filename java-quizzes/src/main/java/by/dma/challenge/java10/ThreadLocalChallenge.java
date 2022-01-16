package by.dma.challenge.java10;

/**
 * Quiz on Threads.
 * You will learn how to store data isolated by different Threads to avoid collision of data.
 * The LocalThread class basically isolates one value in the current Thread that is being executed.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ThreadLocalChallenge {
    public static void main(String... doYourBest) throws InterruptedException {
        MarvelThreadLocal wolverine = new MarvelThreadLocal("Wolverine", true);
        startJoinThread(wolverine);
        System.out.println(wolverine.xmanName.get());

        startJoinThread(new MarvelThreadLocal("Xavier", true));
        startJoinThread(new MarvelThreadLocal("Cyclops", false));
    }

    private static void startJoinThread(MarvelThreadLocal marvelThreadLocal) throws InterruptedException {
        marvelThreadLocal.start();
        marvelThreadLocal.join();
    }

    static class MarvelThreadLocal extends Thread {
        ThreadLocal<String> xmanName = new ThreadLocal<>();
        boolean canBeatMagneto;

        MarvelThreadLocal(String name, boolean canBeatMagneto) {
            xmanName.set(name);
            this.canBeatMagneto = canBeatMagneto;
        }

        public void run() {
            if (canBeatMagneto) {
                System.out.println(xmanName.get());
            } else {
                System.out.println("Magneto");
            }
        }

    }
}
