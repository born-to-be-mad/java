package by.dma.quiz;

import java.util.Arrays;

/**
 * Quiz on Binary Search.
 * Learn how to use search for elements you want into an array.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
public class BinarySearchChallenge {
    static String[] marvel = {"Spiderman", "Venom", "Carnage", "Mysterio"};

    public static void main(String[] args) {
        Arrays.sort(marvel);

        System.out.println(Arrays.binarySearch(marvel, "Xavier"));
        System.out.println(marvel[Arrays.binarySearch(marvel, "Carnage")]);
        System.out.println(Arrays.binarySearch(marvel, "Lizard"));
        System.out.println(Arrays.binarySearch(marvel, "Apocalypse"));
        System.out.println(Arrays.binarySearch(marvel, "Spiderman"));
    }
}
