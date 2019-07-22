package recipes.streams;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:15
 * @since : 2019.07
 **/
class NumberStreamUtilsTest {
    @Test
    public void isPrimeUsingAllMatch() throws Exception {
        assertTrue(IntStream.of(2, 3, 5, 7, 11, 13, 17, 19)
                .allMatch(NumberStreamUtils::isPrime));
    }

    @Test
    public void isPrimeWithComposites() throws Exception {
        assertFalse(Stream.of(4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20)
                .anyMatch(NumberStreamUtils::isPrime));
    }

    @Test
    public void isNotPrime() throws Exception {
        assertTrue(Stream.of(4, 6, 8, 10)
                .noneMatch(NumberStreamUtils::isPrime));
    }
}