/**
 * Converts Roman numerals to Arabic numerals.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class RomanNumeralsConverter {

    static int convert(String romanLiteral) {
        if (romanLiteral.equalsIgnoreCase("I")) {
            return 1;
        }
        if (romanLiteral.equalsIgnoreCase("V")) {
            return 5;
        }
        if (romanLiteral.equalsIgnoreCase("X")) {
            return 10;
        }
        return 0;
    }
}
