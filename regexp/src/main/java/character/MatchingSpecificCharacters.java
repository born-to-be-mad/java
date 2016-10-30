package character;

import core.RegexTest;

/**
 * Created by Dzmitry Marudau on 30.10.2016.
 * TASK:
 You have a test string .
 Your task is to write a regex that will match  with following conditions:
 must be of length: 6
 First character: 1, 2 or 3
 Second character: 1, 2 or 0
 Third character: x, s or 0
 Fourth character: 3, 0 , A or a
 Fifth character: x, s or u  `
 Sixth character: . or ,

 * DETAILS:
 * The character class [ ] matches only one out of several characters placed inside the square brackets.
 */
public class MatchingSpecificCharacters {
    public static void main(String[] args) {
        RegexTest tester = new RegexTest();
        tester.checker("^[1-3][0-2][xs0][30Aa][xsu][.,]$");
    }
}
