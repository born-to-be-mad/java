import java.util.function.Supplier;

public final class ExtObjects {

    private ExtObjects() {
        throw new AssertionError("Impossible to create the instance");
    }

    public static <T, X extends Throwable> T requireNotNullOrElseThrow(
            T obj, Supplier<? extends X> extensionSupplier) throws X {

        if (obj != null) {
            return obj;
        }
        throw extensionSupplier.get();

    }
}
