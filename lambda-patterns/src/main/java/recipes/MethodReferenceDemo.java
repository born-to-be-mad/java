package recipes;

import java.util.stream.DoubleStream;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        System.out.println("Using a method reference to a static method and instance method:");
        DoubleStream
                .generate(Math::random) //infinitive stream
                .limit(20) //ensure that only 20 elements produced
                .forEach(System.out::println);

    }
}
