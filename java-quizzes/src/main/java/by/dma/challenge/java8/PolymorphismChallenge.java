package by.dma.challenge.java8;

import java.util.List;

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
            System.out.println("Homer");
        }
    }

    static class Bart extends Simpson {
        protected void talk() {
            System.out.println("Bart");
        }
    }

    static class Maggie extends Simpson {
    }

    public static void main(String... doYourBest) {
        new Homer().talk();

        Simpson bartSimpson = new Bart();
        bartSimpson.talk();

        new Maggie().talk();

        System.out.println("#".repeat(15));

        List.of(new Homer(), new Bart(), new Maggie()).forEach(Simpson::talk);

        System.out.println("#".repeat(15));

        new Homer().talk();
        new Bart().talk();
        new Maggie().talk();
    }
}
