package by.dma.challenge.java8;

/**
 * Quiz on Lambda: how to use lambdas with Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class LambdaChallenge {

    public static void main(String... doYourBest) {
        String vitoCorleoneShoot = useRevolver(() -> "BAMM!!");

        String michaelCorleoneShoot = useShotgun(() -> {
            return "POHHHH!!!"; // Line 7
        });

        System.out.println(vitoCorleoneShoot + michaelCorleoneShoot);
    }

    private static String useRevolver(Revolver revolver) {
        return revolver.shoot();
    }

    static String useShotgun(Shotgun shotgun) {
        return shotgun.shoot();
    }

    private interface Revolver {
        String shoot(); // Line 23
    }

    public interface Shotgun {
        String shoot();

        default String reload() { // Line 29
            return "Reloading...";
        }
    }

}
