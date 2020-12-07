package by.dma.challenge.java8;

/**
 * Learn what happens when using Polymorphism with static methods and attributes
 * that have the same name in the parent and child classes.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class DraculaPolymorphism {
    public static void main(String... doYourBest) {
        Dracula dracula = new Alucard();

        System.out.println(dracula.name + dracula.getName());
        System.out.println(dracula.metamorphosis());
    }

    static class Dracula {
        String name = "Dracula";

        static String metamorphosis() {
            return "werewolf";
        }

        String getName() {
            return this.name;
        }
    }

    static class Alucard extends Dracula {
        String name = "Alucard";

        static String metamorphosis() {
            return "bat";
        }

        String getName() {
            return this.name;
        }
    }
}
