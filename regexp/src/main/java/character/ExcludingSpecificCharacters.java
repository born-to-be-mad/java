package character;

import core.RegexTest;

/**
 * Created by Dzmitry Marudau on 30.10.2016.
 * TASK:
 You have a test string .
 Your task is to write a regex that will match  with following conditions:
 must be of length 6.
 First character should not be a digit (  or  ).
 Second character should not be a lowercase vowel (  or  ).
 Third character should not be b, c, D or F.
 Fourth character should not be a whitespace character ( \r, \n, \t, \f or <space> ).
 Fifth character should not be a uppercase vowel (  or  ).
 Sixth character should not be a . or , symbol

 * DETAILS:
 * The negated character class [^] matches any character that is not in the square brackets.
 */
public class ExcludingSpecificCharacters {
    public static void main(String[] args) {
        RegexTest tester = new RegexTest();
        tester.checker("^[^0-9][^aeiou][^bcDF][^\\W][^AEIOU][^.,]$");
    }
}
