package by.dma.challenge.java6;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Quiz on Parsing Tokens.
 * Use the Scanner class to parse primitive types and strings using regular expressions.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class TokenizerChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner("ThisIsIt,theFinalString,NoBugsProject");

        Pattern pattern = Pattern.compile("[^\\w*]");
        sc.useDelimiter(pattern);

        while (sc.hasNext()) {
            System.out.println(sc.next());
        }

        sc.close();
    }
}

// In simple words, this symbol “^\\w” means “not a word” and the “*” means that – Occurs zero or more times, is short for {0,},
// so it will tokenize by the comma “,“.
