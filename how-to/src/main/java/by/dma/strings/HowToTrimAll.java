package by.dma.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * How to trim all string in the list.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class HowToTrimAll {

    public static void main(String[] args) {
        var sourceList = List.of("  aaa ", "  bb", "cc ");
        System.out.println("Original list: " + sourceList);
        List<String> listA = new ArrayList<>(sourceList);
        listA.replaceAll(String::trim);
        System.out.println("ListA after modification: " + listA);

        List<String> listB = new ArrayList<>(sourceList);
        for (int i = 0; i < listB.size(); i++) {
            listB.set(i, listB.get(i).trim());
        }
        System.out.println("ListB after modification: " + listB);

        List<String> listC = new ArrayList<>(sourceList);
        IntStream.range(0, listC.size())
                 .parallel()
                 .forEach(i -> listC.set(i, listC.get(i).trim()));
        System.out.println("ListC after modification: " + listC);
    }
}
