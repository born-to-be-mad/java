package by.dma.benchmark;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import static java.lang.String.format;

/**
 * @author dzmitry.marudau
 * @since 2020.1
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3)
@State(Scope.Benchmark)
public class SLF4JLogRecordBenchmark {
    private static final Logger LOGGER = LoggerFactory.getLogger(SLF4JLogRecordBenchmark.class);

    @Param( {"INFO", "DEBUG"})
    private static String theLevel;
    @Param( {"P1"})
    private static String aString;
    @Param( {"42"})
    private static int anInt;
    @Param( {"00.42f"})
    private static float aFloat;
    @Param( {"true"})
    private static boolean aBoolean;
    @Param( {"!"})
    private static char aChar;
    private Level logLevel;

    @Setup
    public void setUp(final Blackhole blackhole) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream() {
            @Override
            public void write(int b) {
                blackhole.consume(b);
            }

            @Override
            public void write(byte[] b, int off, int len) {
                blackhole.consume(b);
            }

            @Override
            public void write(byte[] b) {
                blackhole.consume(b);
            }
        };
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LOGGER.getClass());
        //Logger logger = org.apache.log4j.Logger.getLogger(LOGGER.getName());
        logger.removeAllAppenders();
        logger.setAdditivity(false);

        Appender appender = new WriterAppender(new PatternLayout(), bos);
        logger.addAppender(appender);

        logLevel = "INFO".equals(theLevel) ? Level.INFO : Level.DEBUG;
        logger.setLevel(logLevel);
    }

    @Benchmark
    public void string_format() {
        LOGGER.debug(format("Result [%s], [%s], [%s], [%s], [%s]", aString, ++anInt, aBoolean, aFloat++, aChar));
    }

    @Benchmark
    public void lambda_heap() {
        LOGGER.atDebug().log(
            () -> ("Result [" + aString + "], [" + (++anInt) + "], [" + aBoolean + "], [" + (aFloat++) + "], [" + aChar + "]")
        );
    }

    @Benchmark
    public void localLambaViaFluentAPI() {
        String localString = aString;
        int localInt = ++anInt;
        boolean localBoolean = aBoolean;
        float localFloat = aFloat++;
        char localChar = aChar;
        LOGGER.atDebug().log(
            () -> ("Result [" + localString + "], [" + localInt + "], [" + localBoolean + "], [" + localFloat + "], [" + localChar + "]")
        );
    }

    @Benchmark
    public void unguarded_parametrized() {
        LOGGER.debug("Result [{}], [{}], [{}], [{}], [{}]", aString, ++anInt, aBoolean, aFloat++, aChar);
    }

    @Benchmark
    public void guarded_parametrized() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Result [{}], [{}], [{}], [{}], [{}]", aString, ++anInt, aBoolean, aFloat++, aChar);
        }
    }

    @Benchmark
    public void unguarded_unparametrized() {
        LOGGER.debug(
            "Result [" + aString + "], [" + (++anInt) + "], [" + aBoolean + "], [" + (aFloat++) + "], [" + aChar + "]"
        );
    }

    @Benchmark
    public void guarded_unparametrized() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                "Result [" + aString + "], [" + (++anInt) + "], [" + aBoolean + "], [" + (aFloat++) + "], [" + aChar + "]"
            );
        }
    }
}
