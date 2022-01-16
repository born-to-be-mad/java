package by.dma.sets;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.01
 */
public class TreeSetDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Set<Integer> oddNumbersBetweenMinusTenAndPlusTen = new TreeSet<>();
        oddNumbersBetweenMinusTenAndPlusTen.addAll(Arrays.asList(-8, -5, -7, -1, -3, 1, 1, 1, 9, 7, 8));
        System.out.println("oddNumbersBetweenMinusTenAndPlusTen = " + oddNumbersBetweenMinusTenAndPlusTen);

        try {
            Set<Object> mixed = new TreeSet<>();
            mixed.add("String");
            mixed.add(123);
            System.out.println("mixed = " + mixed);
        } catch (Exception e) {
            System.out.println("Expected exception for mized objects in Treeset:" + e.getMessage());
        }

        // IDENTIFY MAXIMUM TREE DEPTH
/*        Set<Integer> millionValuesTreeSet = new TreeSet<>(
                IntStream.range(0, 1_000_000).boxed().collect(Collectors.toSet())
        );
        System.out.println("millionValuesTreeSet = " + millionValuesTreeSet);*/

        TreeMap<Integer, Object> million = new TreeMap<>();
        IntStream.range(0, 1_000_000).forEach(i -> million.put(i, "dummy"));
        System.out.println("million.size() = " + million.size());
        Field parentField = null;
        LongAccumulator maximumDepth = new LongAccumulator(Long::max, 0);
        for (Map.Entry<Integer, Object> entry : million.entrySet()) {
            if (parentField == null) {
                parentField = entry.getClass().getDeclaredField("parent");
                parentField.setAccessible(true);
                System.out.println("parentField = " + parentField);
            }
            int level = 0;
            Object node = entry;
            while (node != null) {
                level++;
                node = parentField.get(node);
            }
            maximumDepth.accumulate(level);
        }
        System.out.println("maximumDepth = " + maximumDepth);

    }

}
