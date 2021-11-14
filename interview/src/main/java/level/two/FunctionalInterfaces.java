package level.two;

import java.util.function.Function;

/**
 * What is the output?
 *
 * @impNote Result: 1
 */
public class FunctionalInterfaces {

    public static void main(String[] args) {
        Function<Integer, Integer> add = x -> x + 2;
        Function<Integer, Integer> sub = x -> x - 2;
        Function<Integer, Integer> div = x -> x / 2;

        Function<Integer, Integer> func = add.andThen(sub).andThen(div);

        System.out.println(func.apply(2));

    }
}
