package java9;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * New Stream methods sinse Java 9: ofNullable , iterate , takeWhile , and dropWhile .
 *
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2019.10
 **/
public class Java9StreamMethodsDemoTest {
    @Test
    public void ofNullable() throws Exception {
        Stream<String> stream = Stream.ofNullable("abcdef");
        assertEquals(1, stream.count());
        stream = Stream.ofNullable(null);
        assertEquals(0, stream.count());
    }

    @Test
    public void iterate() throws Exception {
        List<BigDecimal> bigDecimalsJava8Way =
                Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.ONE))
                        .limit(10)
                        .collect(Collectors.toList());
        assertEquals(10, bigDecimalsJava8Way.size());

        List<BigDecimal> bigDecimalsJava9Way = Stream.iterate(BigDecimal.ZERO,
                bd -> bd.longValue() < 10L,
                bd -> bd.add(BigDecimal.ONE))
                .collect(Collectors.toList());
        assertEquals(10, bigDecimalsJava9Way.size());
    }
}

