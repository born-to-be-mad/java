package by.dma.quiz;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Quiz on Lambda. Learn what happens when different types of functional interfaces are used together and how to take advantage of the paradigm.
 *
 * @author dzmitry.marudau
 * @since 2020.2
 */
public class JediDarkSideFight {
    public static void main(String... doYourBest) {
        String luke = "useSaber";
        Supplier<String> yoda = () -> "useForce";

        UnaryOperator<String> fightEmpire = luke::concat;
        UnaryOperator<String> fightDarkSide = String::toUpperCase;

        BiFunction<UnaryOperator<String>, UnaryOperator<String>, Function<String, String>>
            attackDarkSide = Function::andThen;

        String finalAttack = attackDarkSide.apply(fightEmpire, fightDarkSide).apply(yoda.get());

        Consumer<String> consumeForce = System.out::println;
        consumeForce.accept(finalAttack);
    }

}
