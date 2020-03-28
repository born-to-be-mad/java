package by.dma.challenge;

/**
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
