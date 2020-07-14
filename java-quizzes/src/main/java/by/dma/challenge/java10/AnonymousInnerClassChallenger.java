package by.dma.challenge.java10;

import java.util.function.Predicate;

/**
 * How to manipulate the Predicate interface.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class AnonymousInnerClassChallenger {

    public static void main(String... silentHill) {
        var newSlay = "---";
        var jamesShot = "---";

        Predicate<String> pyramidHeadAttack = new Predicate<String>() {
            @Override
            public boolean test(String pyramidHeadSlay) {
                return newSlay == new String(pyramidHeadSlay).intern(); // smth wrong here?
            }
        }
        .and(new PyramidHead())
        .or(james -> james.equals(jamesShot))
        .negate(); // any problem here?

        System.out.println(pyramidHeadAttack.test("---"));
    }

    static class PyramidHead implements Predicate<String> {
        @Override
        public boolean test(String axeAttack) {
            return axeAttack.equals("--!");
        }
    }
}
