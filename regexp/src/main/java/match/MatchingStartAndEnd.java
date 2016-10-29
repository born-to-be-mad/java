package match;

/**
 * Created by Dzmitry Marudau on 29.10.2016.
 * TASK:
 You have a test string . Your task is to match the pattern Xxxxxx.
 Here, X denotes a word character, and x denotes a digit.
 String must start with a digit  and end with . symbol.
 String should be 6 characters long only.
 * DETAILS:
 * The ^ symbol matches the position at the start of a string.
 * The $ symbol matches the position at the end of a string.
 */

public class MatchingStartAndEnd {
    public static void main(String[] args) {
        Regex_Test tester = new Regex_Test();
        tester.checker("^\\d\\w{4}.$");
    }
}

