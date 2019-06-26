package challenges.week25;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Dzmitry Marudau on 04.11.2016.
 */
public class BabyStepGiantStepTest {
    @Test
    public void checkDistance() {
        assertEquals(2, BabyStepGiantStep.distance(Pair.of(0.0, 0.0), Pair.of(0.0, 2.0)), 0.001);
    }

    @Test
    public void checkTwoSteps() {
        assertEquals(BabyStepGiantStep.solve(2, 3, 1), 2, "Two steps");
    }

    @Test
    public void CheckZeroSteps() {
        assertEquals(BabyStepGiantStep.solve(1, 2, 0), 0, "Zero steps");
    }

    @Test
    public void CheckMultipleTest() {
        assertEquals(BabyStepGiantStep.solve(3, 4, 11), 3, "Multiple steps");
    }

    @Test
    public void CheckCase3() {
        assertEquals(BabyStepGiantStep.solve(2, 11, 3), 2, "Multiple steps");
    }

    @Test
    public void CheckCase4() {
        assertEquals(BabyStepGiantStep.solve(3, 4, 9), 3, "Multiple steps");
    }
}
