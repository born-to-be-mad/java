package recipes;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        System.out.println("Using a method reference to a static method and instance method:");
        DoubleStream
                .generate(Math::random) //infinitive stream
                .limit(20) //ensure that only 20 elements produced
                .forEach(System.out::println);

        System.out.println("Invoking the length method of class String using a method reference");
        Stream.of("This", "is", "very", "simple", "stream", "of", "strings")
                .map(String::length)
                .forEach(System.out::print);


    }
}
