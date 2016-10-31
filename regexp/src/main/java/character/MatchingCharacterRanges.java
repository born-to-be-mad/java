package character;

import core.RegexTest;

/**
 * Created by Dzmitry Marudau on 31.10.2016.
 * TASK:
 Write a RegEx that will match a string satisfying the following conditions:
 The string's length is >=5.
 The first character must be a lowercase English alphabetic character.
 The second character must be a positive digit. Note that we consider zero to be neither positive nor negative.
 The third character must not be a lowercase English alphabetic character.
 The fourth character must not be an uppercase English alphabetic character.
 The fifth character must be an uppercase English alphabetic character.

 * DETAILS:
 A hyphen (-) inside a character class specifies a range of characters where the left and right operands are the respective lower and upper bounds of the range.
 */
public class MatchingCharacterRanges {
    public static void main(String[] args) {

        RegexTest tester = new RegexTest();
        tester.checker("^[a-z][1-9][^a-z][^A-Z][A-Z]");

    }
}
