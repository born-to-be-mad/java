package by.dma.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author dzmitry.marudau
 * @since 2019.07
 */
public class SortComparableChallenge {

    public static void main(String... doYourBest) {
        Set<Simpson> set = new TreeSet<>();
        set.add(new Simpson("Homer"));
        set.add(new Simpson("Marge"));
        set.add(new Simpson("Lisa"));
        set.add(new Simpson("Bart"));
        set.add(new Simpson("Maggie"));

        trySort1(set);//Marge Maggie Lisa Homer Bart
        trySort2(set);//Bart Homer Lisa Maggie Marge
        trySort3(set);//Marge Maggie Lisa Homer Bart
        trySort4(set);//Bart Homer Lisa Maggie Marge
    }

    private static void trySort1(Set<Simpson> set) {
        System.out.println("### TRY1 ###");
        List<Simpson> list = new ArrayList<>(set);
        Collections.reverse(list);
        Collections.sort(list);

        list.forEach(System.out::println);
    }

    private static void trySort2(Set<Simpson> set) {
        System.out.println("### TRY2 ###");
        List<Simpson> list = new ArrayList<>(set);
        Collections.reverse(list);
        Collections.reverse(list);
        Collections.reverse(list);

        list.forEach(System.out::println);
    }

    private static void trySort3(Set<Simpson> set) {
        System.out.println("### TRY3 ###");
        List<Simpson> list = new ArrayList<>(set);
        Collections.sort(list);

        list.forEach(System.out::println);
    }

    private static void trySort4(Set<Simpson> set) {
        System.out.println("### TRY4 ###");
        List<Simpson> list = new ArrayList<>(set);
        Collections.reverse(list);

        list.forEach(System.out::println);
    }

    static class Simpson implements Comparable<Simpson> {
        String name;

        Simpson(String name) {
            this.name = name;
        }

        public int compareTo(Simpson simpson) {
            return simpson.name.compareTo(this.name);
        }

        public String toString() {
            return this.name;
        }

    }

}
