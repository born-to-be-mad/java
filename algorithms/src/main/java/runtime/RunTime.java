/*************************************************************************
 * (C) COPYRIGHT International Business Machines Corp. 2012, 2013
 * All Rights Reserved
 * <p>
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 **************************************************************************/
package runtime;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Dzmitry Marudau
 * Date: 11/1/2016
 * Time: 12:53 PM
 */
public class RunTime {

    // Linear time: O(n) time
    public static int findNumberOfRepetitions(String str, char c) {
        int res = 0;//1
        for (int i = 0; i < str.length(); i++) { //1, n+1, n
            if (str.charAt(i) == c) {//n
                res++;//n
            }
        }
        return res;//1
    }

    // Quad time O(n*n) time
    public static int[] findNumberOfRepetitions(String str, char[] chars) {
        int[] sums = new int[chars.length];//1
        for (int i = 0; i < str.length(); i++) {//1, n+1 , n
            for (int j = 0; j < chars.length; j++) {//n, n*m+1, n*m
                if (str.charAt(i) == chars[j]) {//n*m
                    sums[j]++;//n*m
                }
            }
        }
        return sums;
    }

    // Optimal time O(n+m) time
    public static int[] findNumberOfRepetitionsOptimal(String str, char[] chars) {
        Map<Character, Integer> dictionary = new HashMap<>();
        int[] sums = new int[chars.length];//1
        for (int i = 0; i < str.length(); i++) {//1, n+1 , n
            if (!dictionary.containsKey(str.charAt(i))) {
                dictionary.put(str.charAt(i), 1);
            } else {
                int sum = dictionary.get(str.charAt(i));
                dictionary.put(str.charAt(i), sum + 1);
            }
        }
        for (int j = 0; j < chars.length; j++) {
            if (!dictionary.containsKey(chars[j])) {
                sums[j] = 0;
            } else {
                sums[j] = dictionary.get(chars[j]);
            }

        }
        return sums;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        input = input.equalsIgnoreCase("5") ? null : input;
        switch (TypeEnum.get(input)) {
            case A: {
                System.out.println("A type");
                break;
            }
            case B: {
                System.out.println("B type");
                break;
            }
            case C: {
                System.out.println("C type");
                break;
            }
            default: {
                System.out.println("Unknown");
                break;
            }
        }
        s.close();
        char[] charArray = "abcdefghtruioxnvz1234567890asdoflasdfklasdfkorewtipowrjetsdgklaskdfjaksdfjaskdfja[]][][]126345513---==-\\sdkfjaskdfjaksd".toCharArray();
        String checkStr = "abcdeaskjfhjadsghfsasd;lfjasldgkjfqweiruiwreasdfjasddfljasdkfdasdfa" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "[dsdaf][asdf[][][]asdfasd---9412549127418357283574283574283574283547" +
                "tttttttttttttttttdasdddddddddddddddddddddddddddddddddddddddddddddd" +
                "tttttttttttttttttdasdddddddddddddddddddddddddddddddddddddddddddddd" +
                "tttttttttttttttttdasdddddddddddddddddddddddddddddddddddddddddddddd" +
                "asd;asdfklasldfkjasdkflasdkf;lasdfka;lsdfklasdfklasdfka;lsdfklasdfl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "1111111111111111112222222222222233333333333333444444444444a66666666" +
                "11111111111111111122222222222222dsfasdfasdf3333444444444444a66666666" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;qwo0235359482364dsakfjaksjdfdkaskasdfjkasdfjkasdfjasdfasdfdasf" +
                "asd;fljsdfaksdfkasdfjkadlsfjkasdfjasdkfjasdasdfasdfasdfasdfasdfdasl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkaasdfasdfasdqweraw4eqewsdfjasdkdfjasdl" +
                "asd;fljsdfaksdfkasdfjkadlsfjkasdfjasdsadfasdfasdfasdfasdsdafkfjasdl";
        System.out.println("############ BAD solution ########");
        long startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(findNumberOfRepetitions(checkStr, charArray)));
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Running time:" + duration + " ms");

        System.out.println("############ OPTIMAL solution ########");
        startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(findNumberOfRepetitionsOptimal(checkStr, charArray)));
        endTime = System.currentTimeMillis();
        duration = endTime - startTime;
        System.out.println("Running time:" + duration + " ms");

    }

}

enum TypeEnum {
    A("0"),
    B("1"),
    C("2"),
    UNKNOWN("");

    private String typeString;
    private static final Map<String, TypeEnum> ENUM_MAP;

    TypeEnum(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return this.typeString;
    }

    static {
        Map<String, TypeEnum> map = new ConcurrentHashMap<String, TypeEnum>();
        for (TypeEnum instance : TypeEnum.values()) {
            map.put(instance.getTypeString(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static TypeEnum get(String name) {
        if (name == null) {
            return UNKNOWN;
        }
        TypeEnum foundEnum = ENUM_MAP.get(name);
        return foundEnum == null ? UNKNOWN : foundEnum;
    }

}
