package by.dma.quizes;

import java.util.function.Predicate;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class AnonymousInnerClassChallenge {

    public static void main(String[] args) {
        var newSlay = "---";
        var jamesShot = "---";
        Predicate<String> pyramidHeadAttack = new Predicate<String>() {

            @Override
            public boolean test(String pyramidHeadSlay) {
                return newSlay == new String(pyramidHeadSlay).intern();
            }
        }
            .and(new PyramidHead())
            .or(james -> james.equals(jamesShot))
            .negate();
        System.out.println(pyramidHeadAttack.test("---"));
        //FINAL result is FALSE
    }

    private static class PyramidHead implements Predicate<String> {
        @Override
        public boolean test(String axeAtack) {
            return axeAtack.equals("---!");
        }
    }
}
