package by.dma.challenge;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class MethodReferenceChallenger {

    public static void main(String[] args) {
        Runnable universeImpactRunnable = () -> new ChuckNorris().roundHouseKick(); //lazy
        Runnable galaxyImpactRunnable = new ChuckNorris()::roundHouseKick; //immediately

        System.out.println("Galaxy is finished=");
        universeImpactRunnable.run();
        universeImpactRunnable.run();

        galaxyImpactRunnable.run();
        galaxyImpactRunnable.run();
        //result 1 2 0 0
    }

    static class ChuckNorris {
        private static int numberOfKicks;
        private int galaxyDamage;

        public ChuckNorris() {
            this.galaxyDamage = numberOfKicks++;
        }

        void roundHouseKick() {
            System.out.println(this.galaxyDamage);
        }
    }
}
