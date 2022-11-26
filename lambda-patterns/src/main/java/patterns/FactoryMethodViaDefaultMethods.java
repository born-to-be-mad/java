package patterns;

/**
 * Factory Method using default methods.
 * 'Factory Method': a class or an interface relies on a delivered class to provide the implementation
 * whereas the base provides the common behaviour; it uses inheritance as a design tool.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class FactoryMethodViaDefaultMethods {

    public static void main(String[] args) {
        call(new IntelijIdeaLover());
        call(new EclipseLover());
    }

    static void call(Person person) {
        person.work();
    }
}

interface Person {

    // private Ide ide;
    Ide getIde();

    default void work() {
        System.out.println("Working with ide = " + getIde());
    }
}

class IntelijIdeaLover implements Person {

    private final Ide ide = new IntellijIdea();

    @Override
    public Ide getIde() {
        return ide;
    }
}

class EclipseLover implements Person {

    private final Ide ide = new Eclipse();

    @Override
    public Ide getIde() {
        return ide;
    }
}

interface Ide {

}

class IntellijIdea implements Ide {

}

class Eclipse implements Ide {

}
