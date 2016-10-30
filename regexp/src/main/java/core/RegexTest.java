package core;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry Marudau on 30.10.2016.
 * Utility class for checking regexp
 */
public class RegexTest {

    public void checker(String Regex_Pattern){

        Scanner Input = new Scanner(System.in);
        String testString = Input.nextLine();
        Pattern p = Pattern.compile(Regex_Pattern);
        Matcher m = p.matcher(testString);
        System.out.println(m.find());
    }

}