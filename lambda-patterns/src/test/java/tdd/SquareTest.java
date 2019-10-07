package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
class SquareTest {

    @Test
    void shouldHaveFourCorners() {
        Shape shape = new Square();
        int numberOfCorners = shape.getNumberOfCorners();
        Assertions.assertEquals(4, numberOfCorners);
    }

    @Test
    void shouldHaveFourEdges() {
        Shape shape = new Square();
        int numberOfEdges = shape.getNumberOfEdges();
        Assertions.assertEquals(4, numberOfEdges);
    }
}