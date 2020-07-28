package by.dma.challenge.java8;

import java.util.HashSet;
import java.util.Set;

/**
 * How to manipulate the equals and hashcode methods and use them to your advantage to use Set Collection.
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class SetChallenge {
    private static int equalCalls = 0;
    private static int hashCodeCalls = 0;

    public static void main(String... doYourBest) {
        Set<Object> set = new HashSet<>();
        set.add(new Simpson("Homer"));
        set.add(new Simpson("Homer"));
        set.add(new Simpson("Bart"));
        set.add(new Simpson("BART"));
        set.add(new Simpson("Grut"));
        set.add(new Simpson("Grut"));
        set.add(new Simpson("Grut"));

        System.out.printf("Set contains %s%n", set.size());

        System.out.printf("equalCalls=%d, hashCodeCalls=%d%n", equalCalls, hashCodeCalls);
    }

    static class Simpson {
        private String name;

        public Simpson(String name) {
            this.name = name;
        }

        public int hashCode() {
            hashCodeCalls++;
            return 1 >> 1 % 500 + 300 / 2000;
        }

        public boolean equals(Object obj) {
            equalCalls++;
            return this.name.equals(((Simpson) obj).name);
        }
    }

}
