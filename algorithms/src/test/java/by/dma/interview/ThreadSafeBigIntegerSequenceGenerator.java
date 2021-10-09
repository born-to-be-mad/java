package by.dma.interview;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Thread safe BigInteger sequence generator with non-blocking method next(): [1, 2, 4, 8, 16, ...].
 */
public class ThreadSafeBigIntegerSequenceGenerator {
    private AtomicReference<BigInteger> current = new AtomicReference<>(null);

    public BigInteger next() {
        BigInteger recent;
        BigInteger next;
        do {
            recent = current.get();
            next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
        } while (!current.compareAndSet(recent, next));
        return next;
    }
}
