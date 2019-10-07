package java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
class SquareTest {

    @Test
    void shouldHaveFourCorners() {
        Square square = new Square();
        int numberOfCorners = square.getNumberOfCorners();
        Assertions.assertEquals(4, numberOfCorners);
    }
}