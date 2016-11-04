package challenges.week25;

/**
 * Created by Dzmitry Marudau on 04.11.2016.
 */

import java.util.*;

public class AppendAndDelete {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        int k = in.nextInt();

        System.out.printf(checkStrings(s1, s2, k) ? "Yes" : "No");
        in.close();
    }

    public static boolean checkStrings(String s1, String s2, int k) {
        for (int i = 0; i < k; i++) {
            if (s2.contains(s1)) {
                int s1Length = s1.length();
                int s2Length = s2.length();
                if (k - s1Length >= s2Length) {
                    return true;
                }
                return isEven(k - i) && (i >= (s2Length - s1Length));
            } else {
                s1 = removeLastChar(s1);
            }
        }
        return s2.equals(s1);
    }

    private static boolean isEven(int num) {
        return (num & 1) == 0;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
