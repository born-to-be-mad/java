package by.dma.challenge;

/**
 * Quiz on Constructors/Exceptions.
 * Constructors in Java are used to construct the object.
 * There are also some rules, every class in Java constructors, whether you declare it explicitly or not.
 * If we don't declare any constructor in the class the default constructor will be invoked.
 *
 * @author dzmitry.marudau
 * @since 2019.6
 */
public class ToString {

    private static int number = 10;

    public static void main(String... doYourBest) {
        new ToString();
    }

    public ToString() {
        //System.out.println(this + "" + new MisterBean());
        //System.out.println(new MisterBean());

        System.out.println(this);
    }

    public String toString() {
        return "ToString.number = " + number;
    }

    static class MisterBean extends ToString {
    }
}
