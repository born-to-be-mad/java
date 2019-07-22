package recipes.streams;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 10:37
 * @since : 2019.07
 **/
class StringStreamUtilsTest {

    private final Stream<String> inputStream = Stream.of(
            "Madam, in Eden, I'm Adam",
            "Go hang a salami; I'm a lasagna hog",
            "Flee to me, remote elf!",
            "A Santa pets rats as Pat taps a star step at NASA");

    @Test
    void isPalindromeOldApproach() {
        assertTrue(inputStream.allMatch(StringStreamUtils::isPalindromeOldApproach));
        assertFalse(StringStreamUtils.isPalindrome("This is NOT a palindrome!"));
    }

    @Test
    void isPalindrome() {
        assertTrue(inputStream.allMatch(StringStreamUtils::isPalindrome));
        assertFalse(StringStreamUtils.isPalindrome("This is NOT a palindrome!"));
    }
}