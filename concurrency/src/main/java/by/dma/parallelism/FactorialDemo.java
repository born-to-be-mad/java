package by.dma.parallelism;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RecursiveTask;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * BigInteger has clever algorithms for multiplying large numbers. Unfortunately multiply() is single-threaded.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class FactorialDemo {

    public static final int TRIES = 10;

    public static void main(String... args) {


        record Pair(String description, BinaryOperator<BigInteger> operator) {

        }

        List<Pair> multiplies = List.of(
                new Pair("multiply", BigInteger::multiply)
                // java 19 , new Pair("parallelMultiply", BigInteger::parallelMultiply)
        );
        List<Function<BinaryOperator<BigInteger>, Factorial>> funcs =
                List.of(
                        FactorialSequential::new,
                        FactorialCompletableFuture::new,
                        FactorialForkJoin::new
                );
        for (var func : funcs) {
            var factorial = func.apply(BigInteger::multiply);
            System.out.println(factorial.getClass());
            IntStream.rangeClosed(0, TRIES)
                     .mapToObj(factorial::calculate)
                     .forEach(System.out::println);
            System.out.println();
        }
        for (int i = 0; i < TRIES; i++) {
            for (var func : funcs) {
                for (var multiply : multiplies) {
                    Factorial factorial = func.apply(multiply.operator());
                    System.out.print("Try N" + i + ": " + factorial.getClass() + " with " +
                                     multiply.description());
                    long time = System.nanoTime();
                    try {
                        BigInteger result = factorial.calculate(1_000_000);
                    } finally {
                        time = System.nanoTime() - time;
                        System.out.printf(" time = %dms%n",
                                          (time / 1_000_000));
                    }
                }
            }
            System.out.println();
        }
    }
}

class FactorialForkJoin extends AbstractFactorial {

    public FactorialForkJoin(BinaryOperator<BigInteger> multiply) {
        super(multiply);
    }

    @Override
    public BigInteger calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (n == 0) {
            return BigInteger.ONE;
        }
        return new FactorialTask(1, n).invoke();
    }

    private class FactorialTask extends RecursiveTask<BigInteger> {

        private final int from, to;

        public FactorialTask(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected BigInteger compute() {
            if (from == to) {return BigInteger.valueOf(from);}
            int mid = (from + to) >>> 1;
            var leftTask = new FactorialTask(from, mid);
            var rightTask = new FactorialTask(mid + 1, to);
            leftTask.fork();
            BigInteger right = rightTask.invoke();
            BigInteger left = leftTask.join();
            return multiply(left, right);
        }
    }
}

/**
 * CompletableFuture, which internally uses ForkJoin by default.
 * if we set the number of cores to 1 using `-XX:ActiveProcessorCount=1`,
 * or even if we turn off the common ForkJoinPool altogether with `-Djava.util.concurrent.ForkJoinPool.common
 * .parallelism=0`,
 * it will then simply use the current thread for the calculation.
 */
class FactorialCompletableFuture extends AbstractFactorial {

    public FactorialCompletableFuture(BinaryOperator<BigInteger> multiply) {
        super(multiply);
    }

    @Override
    public BigInteger calculate(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (n == 0) {
            return BigInteger.ONE;
        }
        return calculate(1, n).join();
    }

    private CompletableFuture<BigInteger> calculate(int from, int to) {
        if (from == to) {return completedFuture(BigInteger.valueOf(from));}
        int mid = (from + to) >>> 1;
        return calculate(from, mid).thenCombineAsync(
                calculate(mid + 1, to), this::multiply);
    }
}

/**
 * A simple recursive sequential algorithm.
 */
class FactorialSequential extends AbstractFactorial {

    public FactorialSequential(BinaryOperator<BigInteger> multiply) {
        super(multiply);
    }

    @Override
    public BigInteger calculate(int n) {
        if (n < 0) {throw new IllegalArgumentException("n < 0");}
        return calculate(0, n);
    }

    private BigInteger calculate(int from, int to) {
        if (from == to) {
            if (from == 0) {return BigInteger.ONE;}
            return BigInteger.valueOf(from);
        }
        int mid = (from + to) >>> 1;
        BigInteger left = calculate(from, mid);
        BigInteger right = calculate(mid + 1, to);
        return multiply(left, right);
    }
}

abstract class AbstractFactorial implements Factorial {

    private final BinaryOperator<BigInteger> multiply;

    protected AbstractFactorial(BinaryOperator<BigInteger> multiply) {
        this.multiply = multiply;
    }

    protected final BigInteger multiply(BigInteger left, BigInteger right) {
        return multiply.apply(left, right);
    }

}

interface Factorial {

    BigInteger calculate(int n);
}
