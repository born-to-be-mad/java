package by.dma.challenge.java8;

/**
 * Polymorphism is a key object-oriented concept that allows you to create flexible and powerful class hierarchies.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class PolymorphismChallenge {
    static abstract class Simpson {
        void talk() {
            System.out.println("Simpson!");
        }
    }

    static class Homer extends Simpson {
        void talk() {
            System.out.println("Spider Pig!");
        }
    }

    static class Bart extends Simpson {
        protected void talk() {
            System.out.println("Eat my shorts!");
        }
    }

    static class Maggie extends Simpson {
    }

    public static void main(String... doYourBest) {
        new Homer().talk();

        Simpson bartSimpson = new Bart();
        bartSimpson.talk();

        new Maggie().talk();
    }
}
