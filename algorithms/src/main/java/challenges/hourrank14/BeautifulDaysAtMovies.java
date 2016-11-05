package challenges.hourrank14;

import java.util.Scanner;

/**
 * Created by Dzmitry Marudau on 05.11.2016.
 Lily likes to play games with integers and their reversals.
 For some integer x, we define reversed(x) to be the reversal of all digits in x.
 For example x=123, reversed(x)=321.

 Logan wants to go to the movies with Lily on some day x satisfying  i<= x <=j,
 but he knows she only goes to the movies on days she considers to be beautiful.
 Lily considers a day to be beautiful
 if the absolute value of the difference between x and reversed(x) is evenly divisible by k.

 Given i, j, and k, count and print the number of beautiful days when Logan and Lily can go to the movies.

 Input Format

 A single line of three space-separated integers describing the respective values of , , and .
 */
public class BeautifulDaysAtMovies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfDays = 0;
        int i = sc.nextInt();
        int j = sc.nextInt();
        int k = sc.nextInt();
        while(i<=j) {
            if( (reverseNumber(j)-j)%k == 0){
                numberOfDays++;
            }
            j--;
        }
        System.out.println(numberOfDays);
        sc.close();
    }

    private static int reverseNumber(int n){
        int reverse = 0;
        while( n != 0 ) {
            reverse = reverse * 10;
            reverse = reverse + n%10;
            n = n/10;
        }
        return reverse;

    }
}
