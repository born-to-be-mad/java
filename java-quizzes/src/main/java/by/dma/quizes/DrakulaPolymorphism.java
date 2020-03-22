package by.dma.quizes;

/**
 * Quiz on Polymorphism.
 *
 * @author dzmitry.marudau
 * @since 2020.1
 */
public class DrakulaPolymorphism {
    public static void main(String[] args) {
        Drakula drakula = new Alucard();
        System.out.println(drakula.name + drakula.getName());
        System.out.println(drakula.poly());
    }

    static class Drakula {
        String name = "Drakula";

        static String poly() {
            return "wolf";
        }

        public String getName() {
            return name;
        }
    }

    static class Alucard extends Drakula {
        String name = "Drakula";

        static String poly() {
            return "bat";
        }

        public String getName() {
            return name;
        }
    }
}
