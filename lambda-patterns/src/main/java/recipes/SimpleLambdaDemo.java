package recipes;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class SimpleLambdaDemo {
    public static void main(String[] args) {
        new Thread(() ->
                System.out.println("Thread constructor using lambda")
        ).start();

        Runnable r = () -> System.out.println(
                "lambda expression implementing the run method");
        new Thread(r).start();

        File directory = new File("./src/main/java/recipes");
        String[] names = directory.list((dir, name) -> name.endsWith(".java"));
        System.out.println(Arrays.asList(Objects.requireNonNull(names)));

    }

}
