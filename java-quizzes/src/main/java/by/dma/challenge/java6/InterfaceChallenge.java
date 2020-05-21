package by.dma.challenge.java6;

import java.util.function.Function;

/**
 * Quiz on Interfaces: to use key methods with the new interface features.
 * @author dzmitry.marudau
 * @since 2019.08
 */
public class InterfaceChallenge {

    interface Jedi {
        String MASTER = "Yoda";

        default String attack() {
            return jump(jedi -> String.join(jedi, useSaber(), useForce()));
        }

        private String jump(Function<String, String> function) {
            return function.apply("Luke");
        }

        private static String useSaber() {
            return "S";
        }

        private String useForce() {
            return "F";
        }

    }

    public static void main(String... starWars) {
        System.out.println(
            new Jedi() {
                public String useForce() {
                    return "X";
                }
            }
            .attack() + Jedi.useSaber() + Jedi.MASTER);
        // first instruction returns 'S'+'Luke'+'F' where 'Luke' is delimiter between 'S', 'F' passed as the parameters
        // Jedi.useSaber() returns 'S'
        // Jedi.MASTER returns 'Yoda'

        //Final result: SLukeFSYoda
    }
}
