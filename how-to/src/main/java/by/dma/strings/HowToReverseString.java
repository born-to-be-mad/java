package by.dma.strings;

/**
 * @author vm: Dzmitry Marudau
 * @created at : 00:22
 * @since : 2019.10
 **/
public class HowToReverseString {
    public static void main(String[] args) {
        System.out.printf("viaStringBuilder: %s -> %s%n", "ABCDEFGH", viaStringBuilder("ABCDEFGH"));
        System.out.printf("viaRLOCharacter: %s -> %s%n", "ABCDEFGH", viaRLOCharacter("ABCDEFGH"));
        System.out.printf("viaRecursionAndSubstring: %s -> %s%n", "ABCDEFGH", viaRecursionAndSubstring("ABCDEFGH"));
        System.out.printf("viaRecursionAndSubstring: %s -> %s%n", "ABCDEFGH", viaRecursionAndSubstring2("ABCDEFGH"));
    }

    public static String viaStringBuilder(String original) {
        return new StringBuilder(original).reverse().toString();
    }

    /**
     * The RLO character (U+202e in unicode) is designed to support languages
     * that are written right to left, such as Arabic and Hebrew.
     * */
    public static String viaRLOCharacter(String original) {
        return "\u202E" + original;
    }

    public static String viaRecursionAndSubstring(String original) {
        if (original.isEmpty()) {
            return original;
        }
        return viaRecursionAndSubstring(original.substring(1)) + original.charAt(0);
    }

    public static String viaRecursionAndSubstring2(String original) {
        // base case: if string is null or empty
        if (original == null || original.equals("")) {
            return original;
        }
        // last character + recur for remaining string
        return original.charAt(original.length() - 1)
                + viaRecursionAndSubstring2(original.substring(0, original.length() - 1));

    }
}


