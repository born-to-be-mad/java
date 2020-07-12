package by.dma.challenge.java10;

/**
 *  How to manipulate Advanced Overloading, which allows the JVM will choose the method that results in the best
 *  performance.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class AdvancedOverloadingChallenge {
    static String x = "";

    public static void main(String... doYourBest) {
        Float f = new Float(4);
        Double d = new Double(4);
        Object o = new Double(4);
        Long l = new Long(4);

        executeAction(f);
        executeAction(d);
        executeAction(o);
        executeAction(l);
        System.out.println(x);

        x = "";
        System.out.println("######");
        executeAction(1);
        executeAction(1.0);
        executeAction(Double.valueOf("5"));
        executeAction(1L);
        System.out.println(x);

        x = "";
        System.out.println("######");
        executeAction(1F);
        executeAction(1.0);
        executeAction(new StackOverflowError());
        executeAction(1);
        System.out.println(x);

    }

    static void executeAction(int... var) {
        x += "a";
    }

    static void executeAction(Integer var) {
        x += "b";
    }

    static void executeAction(Object var) {
        x += "c";
    }

    static void executeAction(short var) {
        x += "d";
    }

    static void executeAction(float var) {
        x += "e";
    }

    static void executeAction(double var) {
        x += "f";
    }
}
