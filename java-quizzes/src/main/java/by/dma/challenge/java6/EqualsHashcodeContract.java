package by.dma.challenge.java6;

import java.util.HashSet;
import java.util.Set;

/**
 * Quiz on equals and hashcode (assume Java 6 or higher).
 * You will learn how the equals and hashcode contract works in different situations.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class EqualsHashcodeContract {
    public static void main(String... doYourBest) {
        Set<Borat> borats = new HashSet<>();
        borats.add(new Borat(1, "Kazakhstan"));
        borats.add(new Borat(2, "USAndA"));
        borats.add(new Borat(2, "England"));
    }

    static class Borat {
        int id;
        String country;

        Borat(int id, String country) {
            this.id = id;
            this.country = country;
        }

        public int hashCode() {
            System.out.println("hashCode:" + this.id + this.country);
            return this.id;
        }

        public boolean equals(Object obj) {
            System.out.println("equals:" + this.id + this.country);
            return ((Borat) obj).country.equals(this.country);
        }
    }
}
