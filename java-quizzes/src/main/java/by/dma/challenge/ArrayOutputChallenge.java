package by.dma.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Quiz on Arrays: how to delete an element of an array within a looping.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ArrayOutputChallenge {
    public static void main(String... doYourBest) {
        List<String> list = new ArrayList<>();

        list.add("Arya");
        list.add("Tyrion");
        list.add("Cersei");
        list.add("Daenerys");
        list.add("Jaime");

        doProcess(list);

        System.out.println(list.size());
    }

    static void doProcess(List<String> list) {
        for (String character : list) {
            if(character.equals("Cersei")) {
                list.remove(character);
            }
        }
    }
}
