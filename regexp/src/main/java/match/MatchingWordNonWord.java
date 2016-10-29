package match;

/**
 * Created by Dzmitry Marudau on 29.10.2016.
 * TASK:
 * You have a test string . Your task is to match the pattern 'xxxXxxxxxxxxxxXxxx' f.e. 'www.myexamples.com'
 * Here x denotes any word character and X denotes any non-word character.
 * DETAILS:
 * The expression \w will match any word character.
 * \W matches any non-word character.
 */
public class MatchingWordNonWord {
    public static void main(String[] args) {

        Regex_Test tester = new Regex_Test();
        tester.checker("\\w{3}\\W\\w{10}\\W\\w{3}");

    }
}

