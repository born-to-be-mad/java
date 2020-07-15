package by.dma.challenge;

/**
 * How to use constructors overloading in order to make your code flexible and easy to maintain.
 * @author dzmitry.marudau
 * @since 2019.09
 */
public class GodOfWarConstructorChallenge {

    private String name;

    GodOfWarConstructorChallenge(String name) {
        this.name = name;
    }

    GodOfWarConstructorChallenge() {
        this(getName(1));
    }

    GodOfWarConstructorChallenge(int i) {
        this(getName(2));
    }

    GodOfWarConstructorChallenge(Object i) {
        this(getName((Integer) i));
    }

    static String getName(int index) {
        String name = new String[] {"Kratos", "Zeus", "Poseidon", "Hades", "Athena"}[index];
        return name;
    }

    public static void main(String... doYourBest) {
        /*GodOfWarConstructorChallenge firstGod = new GodOfWarConstructorChallenge();
        GodOfWarConstructorChallenge secondGod = new GodOfWarConstructorChallenge("Kratos");
        GodOfWarConstructorChallenge thirdGod = new GodOfWarConstructorChallenge(Integer.valueOf(4));*/
        //Zeus Kratos Athena

/*        GodOfWarConstructorChallenge firstGod = new GodOfWarConstructorChallenge(1);
        GodOfWarConstructorChallenge secondGod = new GodOfWarConstructorChallenge(0);
        GodOfWarConstructorChallenge thirdGod = new GodOfWarConstructorChallenge(Integer.valueOf(4));*/
        //Poseidon Poseidon Athena

        GodOfWarConstructorChallenge firstGod = new GodOfWarConstructorChallenge(Integer.valueOf(1));
        GodOfWarConstructorChallenge secondGod = new GodOfWarConstructorChallenge(Integer.valueOf(0));
        GodOfWarConstructorChallenge thirdGod = new GodOfWarConstructorChallenge(Integer.valueOf(4));
        //Zeus Kratos Athena

        System.out.print(firstGod.name + " ");
        System.out.print(secondGod.name + " ");
        System.out.print(thirdGod.name);
    }
}
