package by.dma.challendges;

import java.util.Set;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class StreamDistinctChallenge {

    public static void main(String... doYourBest) {

        Set.of(new Warrior("Ezio"),
               new Warrior("Ezio"),
               new Warrior("Kratos"),
               new Warrior("Cloud"),
               new Warrior("Alucard"))
            .stream()
            .distinct()
            .forEach(w -> System.out.println(w.name));
    }

    static class Warrior {
        private String name;

        Warrior(String name) {
            this.name = name;
        }

        public int hashCode() {
            return this.name.length();
        }

        public boolean equals(Object obj) {
            return this.name.equals(((Warrior) obj).name);
        }
    }
}
