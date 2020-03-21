package by.dma.challenges.week25;

/**
 * User: Dzmitry Marudau
 *
 * TASK:
 * Consider two sets of positive integers, A={A[0],..., A[n-1]} and B={B[0],..., BA[m-1]}.
 * We say that a positive integer, x, is between sets A and B if the following conditions are satisfied:
 * 1. All elements in A are factors of x.
 * 2. x is a factor of all elements in B.
 * Given A and B, find and print the number of integers (i.e., possible x's) that are between the two sets.

 * Input Format
 * The first line contains two space-separated integers describing the respective values of n(the number of elements in set A) and  (the number of elements in set B).
 * The second line contains  distinct space-separated integers describing A[0],..., A[n-1].
 * The third line contains  distinct space-separated integers describing B[0],..., BA[m-1].


 * Constraints:
 * n>=1, m<=10, 1< a[i] <=100, 1<=b[i]<=100

 * Output Format
 * Print the number of integers that are considered to be between A and B.

 * Sample:
 * 2 3
 * 2 4
 * 16 32 96
 * Output:
 * 3
 * Details: {2,4} and B{16,32, 96} has 3 integers: 4, 8 and 16(amount=3).
 */
public class BetweenTwoSets {
    int n;
    int m;
    int[] a;
    int[] b;

    BetweenTwoSets(int n, int m, int[] a, int[] b) {
        this.n = n;
        this.m = m;
        this.a = a;
        this.b = b;
    }

    private static int nod(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int nod = arr[0];
        for (int i = 1; i < arr.length; i++) {
            nod = nod(nod, arr[i]);
        }
        return nod;
    }

    private static int nod(int numberA, int numberB) {
        if (numberB == 0) {
            return numberA;
        } else {
            return nod(numberB, numberA % numberB);
        }
    }

    private static int nok(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int nok = arr[0];
        for (int i = 1; i < arr.length; i++) {
            nok = nok(nok, arr[i]);
        }
        return nok;
    }

    private static int nok(int numberA, int numberB) {
        return numberA * numberB / nod(numberA, numberB);
    }

    void printInput() {
        System.out.println("Input:");
        System.out.println(n);
        System.out.println(m);
        for (int el : a) {
            System.out.print(el + " ");
        }
        System.out.println();
        for (int el : b) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    int solve() {
        System.out.println("NOK A:" + nok(a));
        System.out.println("NOD B:" + nod(b));
        int factor = nod(b) / nok(a);
        System.out.println("FACTOR :" + factor);
        int res = 0;
        for (int i = 1; i <= factor; i++) {
            if (factor % i == 0) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("########### CASE 1 ###############");
        BetweenTwoSets app = new BetweenTwoSets(1, 3, new int[]{1}, new int[]{3, 5, 11});
        app.printInput();
        System.out.println("Output:");
        System.out.println(app.solve());

        System.out.println("########### CASE 2 ###############");
        app = new BetweenTwoSets(2, 3, new int[]{2, 4}, new int[]{16, 32, 96});
        app.printInput();
        System.out.println("Output:");
        System.out.println(app.solve());

        System.out.println("########### CASE 3 ###############");
        app = new BetweenTwoSets(1, 3, new int[]{1}, new int[]{2, 5, 7});
        app.printInput();
        System.out.println("Output:");
        System.out.println(app.solve());

        System.out.println("########### CASE 4 ###############");
        app = new BetweenTwoSets(1, 3, new int[]{1}, new int[]{4, 6, 8});
        app.printInput();
        System.out.println("Output:");
        System.out.println(app.solve());
    }
}
