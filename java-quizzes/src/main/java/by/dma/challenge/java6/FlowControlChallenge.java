package by.dma.challenge.java6;

/**
 * How to work with Flow Control in Java.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class FlowControlChallenge {
    public static void main(String... doYourBest) {

        boolean isHomerFat;
        boolean isBartPrankster = false;

        int result = 0;

        if (isHomerFat = true || (isBartPrankster = true)) {
            result++;
            isHomerFat = false;
        }

        if (isHomerFat || isBartPrankster && (isBartPrankster = false || isHomerFat)) {
            result++;
        }

        if (!isHomerFat && !isBartPrankster) {
            result++;
        }

        System.out.println(String.valueOf(result) + isHomerFat + isBartPrankster);
    }
}
