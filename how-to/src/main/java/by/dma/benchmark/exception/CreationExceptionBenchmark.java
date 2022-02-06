package by.dma.benchmark.exception;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import by.dma.tricks.exception.LightException;

/**
 * Benchmark the exception creation.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, time = 3)
public class CreationExceptionBenchmark {

    @Benchmark
    public Object date() {
        return new Date();
    }

    @Benchmark
    public Object exception() {
        return new Exception();
    }

    @Benchmark
    public Object lightException() {
        return new LightException();
    }

}
/*
Benchmark                                    Mode  Cnt      Score      Error  Units
CreationExceptionBenchmark.date            avgt   25     7.066 ±   0.916  ns/op
CreationExceptionBenchmark.exception       avgt   25  1596.157 ± 360.085  ns/op
CreationExceptionBenchmark.lightException  avgt   25     9.450 ±   1.050  ns/op
*/
