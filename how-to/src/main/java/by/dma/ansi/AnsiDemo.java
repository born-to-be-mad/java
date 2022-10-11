package by.dma.ansi;

/**
 * Playing with ANSI sequences.
 * See https://en.wikipedia.org/wiki/ANSI_escape_code
 *
 * @author dzmitry.marudau
 * @since 2022.10
 */
public class AnsiDemo {

    public static void main(String[] args) {
        System.out.println("Hello Java");
        System.out.println("\033[31mHello Java\033[0m");
        System.out.println("\033[4;44;31mHello Java\033[0mEND");
        // clear the screen
        //System.out.println("\033[2J");
    }

}
