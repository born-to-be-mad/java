package by.dma.interview;

import java.util.concurrent.Semaphore;

/**
 * Minimal non-blocking stack with two methods(push and pop) via {@link Semaphore}.
 *
 * @param <T> the type of stack element value
 */
public class SemaphoreStack<T> {

    private final Semaphore semaphore = new Semaphore(1);
    private Element<T> head = null;

    /**
     * Adds element to the stack.
     *
     * @param value the value to add
     * @return the stack
     */
    public SemaphoreStack<T> push(T value) {
        semaphore.acquireUninterruptibly();
        try {
            head = new Element<>(value, head);
        } finally {
            semaphore.release();
        }

        return this;
    }

    /**
     * Pops element from the stack.
     *
     * @return the value on the head
     */
    public T pop() {
        semaphore.acquireUninterruptibly();

        try {
            Element<T> current = head;
            if (current != null) {
                head = head.next;
                return current.value;
            }
            return null;
        } finally {
            semaphore.release();
        }
    }

    private static class Element<E> {

        private final E value;
        private final Element<E> next;

        private Element(E value, Element<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
