package by.dma.interview;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Minimal non-blocking stack with two methods(push and pop).
 *
 * @param <T> the type of stack element value
 */
public class NonBlockingStack<T> {

    private final AtomicReference<Element> head = new AtomicReference<>(null);

    public NonBlockingStack<T> push(final T value) {
        var current = new Element();
        current.value = value;
        Element recent;
        do {
            recent = head.get();
            current.previous = recent;
        }
        while (!head.compareAndSet(recent, current));
        return this;
    }

    T pop() {
        Element result;
        Element previous;
        do {
            result = head.get();
            if (result == null) {
                return null;
            }
            previous = result.previous;
        }
        while (!head.compareAndSet(result, previous));
        return result.value;
    }

    private class Element {

        T value;
        private Element previous;
    }
}
