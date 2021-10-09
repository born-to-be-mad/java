package by.dma.interview;

/**
 * Simple multi-threading limited buffer via `synchronized`.
 *
 * @param <T> element type
 */
public class QueueSynchronized<T> {

    private volatile int size = 0;
    private final Object[] content;
    private final int capacity;

    private int out;
    private int in;

    private final Object isEmpty = new Object();
    private final Object isFull = new Object();

    QueueSynchronized(final int capacity) {
        this.capacity = capacity;
        content = new Object[this.capacity];
        out = 0;
        in = 0;
        size = 0;
    }

    private int cycleInc(int index) {
        return (++index == capacity)
               ? 0
               : index;
    }

    @SuppressWarnings("unchecked")
    public T get() throws InterruptedException {
        if (size == 0) {
            synchronized (isEmpty) {
                while (size < 1) {
                    isEmpty.wait();
                }
            }
        }
        try {
            synchronized (this) {
                final Object value = content[out];
                content[out] = null;
                if (size > 1) {
                    out = cycleInc(out);
                }
                size--;
                return (T) value;
            }
        } finally {
            synchronized (isFull) {
                isFull.notify();
            }
        }
    }

    public QueueSynchronized<T> put(T value) throws InterruptedException {
        if (size == capacity) {
            synchronized (isFull) {
                while (size == capacity) {
                    isFull.wait();
                }
            }
        }
        synchronized (this) {
            if (size != 0) {
                in = cycleInc(in);
            }
            content[in] = value;
            size++;
        }
        synchronized (isEmpty) {
            isEmpty.notify();
        }
        return this;
    }
}
