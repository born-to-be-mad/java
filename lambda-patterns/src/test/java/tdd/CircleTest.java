package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
class CircleTest {

    @Test
    void getNumberOfCorners() {
        Shape circle = new Circle();
        int numberOfCorners = circle.getNumberOfCorners();
        Assertions.assertEquals(0, numberOfCorners);
    }

    @Test
    void getNumberOfEdges() {
        Shape circle = new Circle();
        int numberOfEdges = circle.getNumberOfEdges();
        Assertions.assertEquals(0, numberOfEdges);
    }
}