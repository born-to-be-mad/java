package patterns;

import java.util.function.Supplier;

public class LazyInitializationDemo {
    public static void main(String[] args) {
        final Lazy<Integer> lazyA = new Lazy<>(() -> computeValue(100));
        final Lazy<Integer> lazyB = new Lazy<>(() -> computeValue(10));
        int a = 1;
        int b = -1;
        System.out.println("Start");
        if (a > 0 && lazyA.getInstance() > 5) {
            System.out.println("Path1...");
        }
        if (b > 0 && lazyB.getInstance() > 5) {
            System.out.println("Path2...");
        }
        System.out.println("End");
    }

    private static int computeValue(int max) {
        int generatedValue = (int) (Math.random() * max);
        System.out.printf("Generated value:%d%n", generatedValue);
        return generatedValue;
    }
}

class Lazy<T> {
    private Supplier<T> supplier;
    private T instance;

    Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    T getInstance() {
        if (instance == null) {
            instance = supplier.get();
            supplier = null;
        }
        return instance;
    }
}
