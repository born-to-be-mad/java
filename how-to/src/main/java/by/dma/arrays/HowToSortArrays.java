package by.dma.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:26
 * @since : 2019.07
 **/
public class HowToSortArrays {
    public static void main(String[] args) {
        int[] numbers = new int[]{-8, 7, 5, 9, 10, -2, 3};
        String[] strings = {"better", "lean", "piece", "than", "fat", "victory"};
        Player[] objects = {
                new Player("Erik", "Cantona", 68).setClub(Club.MU),
                new Player("Rayan", "Gicks", 95).setClub(Club.MU),
                new Player("Paul", "Drogba", 46).setClub(Club.MU),
                new Player("Roberto", "Carlos", 76).setClub(Club.REAL_MADRID),
                new Player("Alessandro", "Del Piero", 70).setClub(Club.JUVENTUS),
                new Player("Leonel", "Messi", 103).setClub(Club.BARCELONA),
                new Player("Gerard", "Piquet", 55).setClub(Club.BARCELONA),
                new Player("Christian", "Ronaldo", 97).setClub(Club.JUVENTUS)
        };

        //When sorting primitives, the Arrays.sort method uses a Dual-Pivot implementation of Quicksort.
        Arrays.sort(numbers);
        System.out.printf("Sorted numbers:%s%n", Arrays.toString(numbers));

        numbers = IntStream.of(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .toArray();
        System.out.printf("Sorted numbers in reverse order:%s%n", Arrays.toString(numbers));

        // When sorting objects an iterative implementation of MergeSort is used.
        Arrays.sort(strings);
        System.out.printf("Sorted strings:%s%n", Arrays.toString(strings));

        Arrays.sort(strings, Comparator.reverseOrder());
        System.out.printf("Sorted strings in reverse order:%s%n", Arrays.toString(strings));

        //Sorting objects that don’t implement the Comparable Interface, requires us to specify our own comparator.
        Arrays.sort(objects, Comparator.comparing(Player::getFirst));
        System.out.printf("Sorted players by first name:%s%n", Arrays.toString(objects));

        //chaining comparisons using Comparator’s thenComparing method
        Arrays.sort(objects, Comparator.comparing(Player::getClub).thenComparing(Player::getFirst));
        System.out.printf("Sorted players by club, then by first name:%s%n", Arrays.toString(objects));

        Arrays.sort(objects, Comparator.comparing(Player::getClub).reversed());
        System.out.printf("Sorted players by club in reverse order:%s%n", Arrays.toString(objects));

    }
}
