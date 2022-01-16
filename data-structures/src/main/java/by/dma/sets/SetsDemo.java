package by.dma.sets;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.01
 */
public class SetsDemo {

    public static void main(String[] args) {
        Set<Integer> simpleSet = new HashSet<>();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(2);
        simpleSet.add(3);
        simpleSet.add(3);
        simpleSet.add(3);
        simpleSet.add(4);
        simpleSet.add(4);
        simpleSet.add(5);
        simpleSet.add(5);
        System.out.println("simpleSet = " + simpleSet);

        Set<Integer> oddBelowTen = Set.of(1, 3, 5, 7, 9);
        System.out.println("oddBelowTen = " + oddBelowTen);
        Set<Integer> evenBelowTen = Set.of(2, 4, 6, 8, 9);
        System.out.println("evenBelowTen = " + evenBelowTen);

        Set<Integer> numbersBelowTen = new HashSet<>(oddBelowTen);
        numbersBelowTen.addAll(evenBelowTen); //union
        System.out.println("numbersBelowTen = " + numbersBelowTen);

        Set<Integer> primeBelowTen = Set.of(2, 3, 5, 7);
        Set<Integer> interSelectionPrimesANdEven = new HashSet<>(primeBelowTen);
        interSelectionPrimesANdEven.retainAll(evenBelowTen);
        System.out.println("interSelectionPrimesANdEven = " + interSelectionPrimesANdEven);

        Set<Integer> primesLessOdds = new HashSet<>(primeBelowTen);
        primesLessOdds.removeAll(oddBelowTen);
        System.out.println("primesLessOdds = " + primesLessOdds);

    }

}
