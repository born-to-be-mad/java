package recipes.issues;

import java.util.function.Function;

/**
 * A functional interface based on Function that throws Exception.
 *
 * @author : Dzmitry Marudau
 * @created at : 01:14
 * @since : 2019.08
 **/
@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {
    public static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    R apply(T t) throws E;
}