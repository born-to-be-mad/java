package by.dma.challenge.java8;

/**
 * You will learn how to manipulate Method References when instantiating a new class and will understand better the
 * concept of higher functions and functional programming with Java.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class MisterBurnsGetsMedicine {
    public static void main(String... doYourBest) {
        MedicineSupplier medicine = Smithers::new;
        medicine.provideMedicine(Integer.valueOf(3)); // Line 5
        medicine.provideMedicine(10);
        medicine.provideMedicine(1);
    }

    static class Smithers {
        Smithers(int any) {
            System.out.printf("Smithers gives:%s to Mister Burns with int  %n", any);
        }

        Smithers(short any) {
            System.out.printf("Smithers gives:%s to Mister Burns with short  %n", any);
        }

        Smithers(Integer any) {
            System.out.printf("Smithers gives:%s to Mister Burns with Integer  %n", any);
        }
    }

    private interface MedicineSupplier {
        Smithers provideMedicine(int quantity);
    }

}
