package by.dma;

import java.util.Arrays;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:46
 * @since : 2020.01
 **/
public class RecursiveSum {
    public static void main(String[] args) {
        int[] myList = {1, 3, 6, 9, 15};
        RecursiveSum app = new RecursiveSum();
        System.out.printf("Sum of %s = %d%n", Arrays.toString(myList), app.sum(myList));
    }

    private long sum(int[] myList) {
        if (myList.length == 1) {
            return myList[0];
        }
        return myList[0] + sum(Arrays.copyOfRange(myList, 1, myList.length));
    }
}
