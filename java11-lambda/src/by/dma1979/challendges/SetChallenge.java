package by.dma1979.challendges;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class SetChallenge {
    public static void main(String... doYourBest) {
        Set<Object> set = new HashSet<>();
        set.add(new Simpson("123"));
        set.add(new Simpson("123"));
        set.add(new Simpson("Homer"));
        set.add(new Simpson("HOMER"));
        set.add(new Simpson("789"));
        set.add(new Simpson("789"));
        set.add(new Simpson("789"));

        System.out.println(set.size());
    }

    static class Simpson {
        String name;

        public Simpson(String name) {
            this.name = name;
        }

        public int hashCode() {
            return 1 >> 1 % 500 + 300 / 2000;
        }

        public boolean equals(Object obj) {
            return this.name.equals(((Simpson) obj).name);
        }
    }

}
