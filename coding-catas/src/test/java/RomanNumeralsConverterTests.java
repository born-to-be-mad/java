import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link RomanNumeralsConverter}, which converts Roman numerals to Arabic numerals.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class RomanNumeralsConverterTests {

    @Test
    public void isJUnitWorking() {
        assertTrue(true);
    }

    @Test
    public void convertsSingleRomanDigit() {
        int arabic = RomanNumeralsConverter.convert("I");
        assertEquals(1, arabic);
    }

}
