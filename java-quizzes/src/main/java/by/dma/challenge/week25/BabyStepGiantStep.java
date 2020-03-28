package by.dma.challenge.week25;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Dzmitry Marudau on 04.11.2016.
 * TASK:
 * You are given q queries in the form of a, b, and d.
 * For each query, print the minimum number of steps it takes to get from point (0, 0) to point (d,0) on a new line.
 * Input Format
 * The first line contains an integer, , denoting the number of queries you must process.
 * Each of the  subsequent lines contains three space-separated integers describing the respective values of , , and  for a query.
 */
public class BabyStepGiantStep {

    public static int solve(int a, int b, int d) {
        if (d == 0) {
            return 0;
        }
        int x1 = 0, y1 = 0;
        if (d < a && d < b) {
            return Math.min(a, b) / d;
        }
        if (d > a || d > b) {
            return Math.min(a, b);
        }
        return 0;

    }

    public static double distance(Pair<Double, Double> p1, Pair<Double, Double> p2) {
        double x = p2.getLeft() - p1.getLeft();
        double y = p2.getRight() - p1.getRight();
        return Math.sqrt(x * x + y * y);
    }
}
