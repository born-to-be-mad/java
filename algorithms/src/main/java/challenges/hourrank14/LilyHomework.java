package challenges.hourrank14;

import java.util.Scanner;

/**
 * Created by Dzmitry Marudau on 05.11.2016.
 */
public class LilyHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        while((n--)>0) {
            b[n] = a[n] = sc.nextLong();
            b[n] = a[n];
        }
        boolean flag = true;
        n= a.length-1;
        int aSwaps=0;
        int bSwaps=0;
        while ( flag ) {
            flag = false;
            for( int j=0;  j < n;  j++ )
            {
                if ( a[j] < a[j+1]) {
                    long temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    aSwaps++;
                    flag = true;
                }
                if ( b[j] > b[j+1]) {
                    long temp = b[j];
                    b[j] = b[j+1];
                    b[j+1] = temp;
                    bSwaps++;
                    flag = true;
                }
            }
        }

        System.out.println(Math.min(aSwaps,bSwaps));
        sc.close();
    }

}
