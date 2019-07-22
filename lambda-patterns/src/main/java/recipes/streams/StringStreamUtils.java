package recipes.streams;

/**
 * @author : Dzmitry Marudau
 * @created at : 10:29
 * @since : 2019.07
 **/
class StringStreamUtils {

    static boolean isPalindromeOldApproach(String inputString) {
        StringBuilder sb = new StringBuilder();
        for (char c : inputString.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    static boolean isPalindrome(String inputString) {
        String forward = inputString.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }
}
