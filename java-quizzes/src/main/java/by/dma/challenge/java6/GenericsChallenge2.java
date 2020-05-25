package by.dma.challenge.java6;

/**
 * Quiz on Generics: how to use generics and create generic components using this knowledge.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class GenericsChallenge2 {
    public static void main(String... doYourBest) {
        Archer archer = new Archer();

        archer.attack("->");
        archer.attack(Double.valueOf(1.0));
        archer.attack(Float.valueOf(1));
    }

    static class Archer<T> {
        T t;

        void attack(T t) {
            System.out.println(this.t);
        }
    }
}
