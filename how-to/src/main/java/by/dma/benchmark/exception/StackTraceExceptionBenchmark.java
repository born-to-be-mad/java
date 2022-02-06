package by.dma.benchmark.exception;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * Getting/representing stack trace is much more expensive than filling stacktrace.
 * - 'new Exception()' is cheap but excetion with 'writableStackTrace=false' is cheaper
 * -> fillInStackTrace() costs!
 * -> getStackTrace() costs more!
 * -> O(stack_trace_depth)
 * - 'throw' is cheap
 * -> catch = goto in the same frame
 * -> O(stack_depth)
 *
 * @author dzmitry.marudau
 * @since 2022.02
 *
 * Hint: `use --XX:-OmitStackTraceInFastThrow` VM option to disable the optimization when JVM stops outputting stack trace of
 * the exception which often occurs for performance purpose
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, time = 3)
public class StackTraceExceptionBenchmark {

    @Benchmark
    public Object fillStackTrace() {
        return new Exception();
    }

    @Benchmark
    public Object getStackTrace() {
        return new Exception().getStackTrace();
    }

}
/*
Benchmark                                    Mode  Cnt      Score      Error  Units
StackTraceExceptionBenchmark.fillStackTrace  avgt   25   1182.311 ±   64.104  ns/op
StackTraceExceptionBenchmark.getStackTrace   avgt   25  11033.969 ± 3471.891  ns/op
*/
