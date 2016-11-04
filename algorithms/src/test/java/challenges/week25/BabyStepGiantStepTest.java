package challenges.week25;

import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dzmitry Marudau on 04.11.2016.
 */
public class BabyStepGiantStepTest {
    @Test
    public void checkDistance() {
        assertEquals(2, BabyStepGiantStep.distance(new Pair(0.0, 0.0), new Pair(0.0, 2.0)), 0.001);
    }

    @Test
    public void checkTwoSteps() {
        assertEquals("Two steps", BabyStepGiantStep.solve(2, 3, 1), 2);
    }

    @Test
    public void CheckZeroSteps() {
        assertEquals("Zero steps", BabyStepGiantStep.solve(1, 2, 0), 0);
    }

    @Test
    public void CheckMultipleTest() {
        assertEquals("Multiple steps", BabyStepGiantStep.solve(3, 4, 11), 3);
    }

    @Test
    public void CheckCase3() {
        assertEquals("Multiple steps", BabyStepGiantStep.solve(2, 11, 3), 2);
    }

    @Test
    public void CheckCase4() {
        assertEquals("Multiple steps", BabyStepGiantStep.solve(3, 4, 9), 3);
    }
}
