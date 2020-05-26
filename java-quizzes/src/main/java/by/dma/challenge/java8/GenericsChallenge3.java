package by.dma.challenge.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Quiz on GenericsChallenge3(Java 8 or higher): how to use generics and create generic components using this knowledge.
 * @author dzmitry.marudau
 * @since 2019.08
 */
public class GenericsChallenge3 {

    public static void main(String... doYourBest) {
    /*
    We are invoking the get method passing an ArrayList and a String as parameters.
    It's going to work fine, T will be a String.
    Even though we are passing an ArrayList without a type,
    Java will implicitly pass a String since the second type is String.
    If the second type was Integer, the type of the ArrayList would be Integer as well.
    */
        List<String> firstResult = GenericsChallenge3.<String>get(new ArrayList<>(), new String("1")); // Line 9

        //we are passing two different types to those generic parameters
        //T will become an Object since we are passing two different types.
        // In order to make T compatible with the different types we are passing,
        // the JVM will transform T into an object so that the elements can be inserted into the ArrayList.
        List<Object> secondResult = GenericsChallenge3.get("Homer", Double.valueOf("4"));

        Stream<Object> stream = Stream.concat(firstResult.stream(),
                                              secondResult.stream());
        stream.forEach(System.out::println);
    }


    public static <T> List<T> get(List<T> list, T value) {

        list.add(value);
        return list;
    }

    public static <T, R extends T> List<T> get(T type1, R type2) {
        List<T> list = new ArrayList<>();
        list.add(type1);
        list.add(type2); // Line 27
        return list;
    }
}
