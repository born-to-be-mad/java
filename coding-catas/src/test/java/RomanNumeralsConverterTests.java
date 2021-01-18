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
        assertEquals(1, RomanNumeralsConverter.convert("I"));
        assertEquals(5, RomanNumeralsConverter.convert("V"));
        assertEquals(10, RomanNumeralsConverter.convert("X"));
        assertEquals(50, RomanNumeralsConverter.convert("L"));
        assertEquals(100, RomanNumeralsConverter.convert("C"));
        assertEquals(500, RomanNumeralsConverter.convert("D"));
        assertEquals(1_000, RomanNumeralsConverter.convert("M"));
    }

}
