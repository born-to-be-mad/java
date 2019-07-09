package recipes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConstructorReferenceDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Franz Martiz", "Fedor", "Egor Gosha", "Max", "Serg", "Mikhail");
        List<Mate> people = names.stream()
                .map(Mate::new)
                .collect(Collectors.toList());
        people.forEach(System.out::println);

        System.out.println("###cInvoking the vararg constructor ###");
        names.stream()
                .map(name -> name.split(" "))
                .map(Mate::new)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("### Creating array of mates ###");
        Mate[] mates = names.stream()
                .map(Mate::new)
                .toArray(Mate[]::new);
        for (Mate mate : mates) {
            System.out.println(mate);
        }

    }
}

class Mate {
    private String name;

    Mate(String name) {
        this.name = name;
    }

    //copy constructor
    Mate(Mate copy) {
        this.name = copy.name;
    }

    //vararg constructor
    Mate(String... names) {
        this.name = String.join("-", names);
    }

    @Override
    public String toString() {
        return "Mate{" +
                "name='" + name + '\'' +
                '}';
    }
}