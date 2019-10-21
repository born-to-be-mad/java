package java9;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * New Stream methods since Java 9: ofNullable , iterate , takeWhile , and dropWhile .
 *
 * @author : Dzmitry Marudau
 * @created at : 00:43
 * @since : 2019.10
 **/
public class Java9StreamMethodsDemoTest {
    @Test
    public void ofNullable() throws Exception {
        assertEquals(1, Stream.ofNullable("abcdef").count(), "Stream with one element");
        assertEquals(0, Stream.<String>ofNullable(null).count(), "Stream with NULL element");
    }

    @Test
    public void iterate() throws Exception {
        List<BigDecimal> bigDecimals =
                Stream.iterate(BigDecimal.ZERO, bd -> bd.add(BigDecimal.ONE))
                        .limit(10)
                        .collect(Collectors.toList());
        assertEquals(10, bigDecimals.size(),
                "Java 8 way to create a stream of big decimals");
        bigDecimals = Stream.iterate(BigDecimal.ZERO,
                bd -> bd.longValue() < 10L,
                bd -> bd.add(BigDecimal.ONE))
                .collect(Collectors.toList());
        assertEquals(10, bigDecimals.size(),
                "Java 9 way to create a stream of big decimals");
    }

    @Test
    public void takeWhile() {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .takeWhile(s -> !s.equals("of"))
                .collect(Collectors.toList());
        List<String> correct = Arrays.asList("this", "is", "a", "list");
        assertEquals(correct, strings);
    }
    @Test
    public void dropWhile() {
        List<String> strings = Stream.of("this is a list of strings".split(" "))
                .dropWhile(s -> !s.equals("of"))
                .collect(Collectors.toList());
        List<String> correct = Arrays.asList("of", "strings");
        assertEquals(correct, strings);
    }
}

