package by.dma.interview.deadlocks;

import java.util.stream.IntStream;

/**
 * Play with `-Djava.util.concurrent.ForkJoinPool.common.parallelism=N`.
 * Use:
 * `jps` to see the list of java processes
 * `jstack -l PROCESS_ID` to dump process.
 *
 * To provide thread-safe initialization of classes,  JVM uses synchronization on `invisible initialization` lock
 * that each class has.
 *
 * Inaccurate writing of initializers can lead to deadlocks.
 * To avoid this:
 * - make sure that static initializers do not refer to other uninitialized classes;
 * - do not create instances of child classes in static initializers;
 * - do not create threads and avoid concurrent code execution in static initializers;
 * - trust no one; study the source code and report bugs found in third-party projects.
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
