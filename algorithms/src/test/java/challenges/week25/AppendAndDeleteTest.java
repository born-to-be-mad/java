package challenges.week25;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Dzmitry Marudau on 04.11.2016.
 */
public class AppendAndDeleteTest {
    @Test
    public void checkStringsTest() {
        assertTrue(AppendAndDelete.checkStrings("aba", "aba", 7));
        assertTrue(AppendAndDelete.checkStrings("hackerhappy", "hackerrank", 9));
    }
}
