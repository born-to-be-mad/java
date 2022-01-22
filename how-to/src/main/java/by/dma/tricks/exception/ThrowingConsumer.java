package by.dma.tricks.exception;

import java.util.function.Consumer;

/**
 * Vacuous exceptions.
 *
 * @author dzmitry.marudau
 * @since 2022.01
 */
@FunctionalInterface
public interface ThrowingConsumer<I, T extends Throwable> {

    void accept(I in) throws T;

    static <T extends Throwable>
    RuntimeException rethrow(Throwable throwable) throws T {
        throw (T) throwable; //rely on vacuous cast
    }

    static <I, T extends Throwable>
    Consumer<I> asConsumer(ThrowingConsumer<I, T> function) {
        return in -> {
            try {
                function.accept(in);
            } catch (Throwable t) {
                throw rethrow(t);
            }
        };
    }

}
