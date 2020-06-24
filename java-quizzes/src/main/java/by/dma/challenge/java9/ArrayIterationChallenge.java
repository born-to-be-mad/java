package by.dma.challenge.java9;

import java.util.ArrayList;
import java.util.List;

/**
 *  Learn details about Arrays in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ArrayIterationChallenge {
    public static void main(String... doYourBest) {
        List<String> list = new ArrayList<>();

        list.add("var1");
        list.add("var2");
        list.add("var3");
        list.add("var4");
        list.add("var5");

        /*list.remove(1);
        list.removeIf(s -> s == "var4");*/

        //list.removeIf(s -> s=="var1" && s == "var4");

        for (String s : list) {
            if (s.equals("var1") || s.equals("var4")) list.remove(s);

        }

        System.out.println(list);
    }
}
