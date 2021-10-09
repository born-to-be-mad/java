package by.dma.interview.deadlocks;

import java.util.stream.IntStream;

/**
 * Play with `-Djava.util.concurrent.ForkJoinPool.common.parallelism=N`.
 * Use:
 * `jps` to see the list of java processes
 * `jstack -l PROCESS_ID` to dump process.
 */
public class StreamSumDeadlock {

    // DEADLOCK
    static final int SUM = IntStream.range(0, 100).parallel().reduce((n, m) -> n + m).getAsInt();

    // NO DEADLOCK
    //static final int SUM = IntStream.range(0, 100).reduce((n, m) -> n + m).getAsInt();
    //static final int SUM = IntStream.range(0, 100).parallel().reduce(Integer::sum).getAsInt();

    public static void main(String[] args) {
        System.out.println(SUM);
    }
}
