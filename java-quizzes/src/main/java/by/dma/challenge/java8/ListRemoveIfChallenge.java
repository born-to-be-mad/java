package by.dma.challenge.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * How to work with Wrappers in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class ListRemoveIfChallenge {
    public static void main(String... dungeonsAndDragons) {
        List<Integer> list = new ArrayList<>();

        list.add(22);
        list.add(12);
        list.add(14);
        list.add(128);

        Integer prestoAge = 14;
        Integer dungeonMasterAge = 128;

        list.removeIf(e -> e == new Integer(12)
                || e == dungeonMasterAge
                || e == prestoAge
                || e.equals(new Integer(22)));

        System.out.println(list);
    }
}
