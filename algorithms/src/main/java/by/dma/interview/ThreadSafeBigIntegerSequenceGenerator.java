package by.dma.interview;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Thread safe BigInteger sequence generator with non-blocking method next(): [1, 2, 4, 8, 16, ...].
 */
public class ThreadSafeBigIntegerSequenceGenerator {

    private final AtomicReference<BigInteger> current = new AtomicReference<>(null);

    public BigInteger next() {
        BigInteger recent;
        BigInteger next;
        do {
            recent = current.get();
            next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
        }
        while (!current.compareAndSet(recent, next));
        return next;
    }

    public static void main(String[] args) {
        ThreadSafeBigIntegerSequenceGenerator app = new ThreadSafeBigIntegerSequenceGenerator();
        Stream.generate(app::next).limit(10).forEach(System.out::println);
    }
}
