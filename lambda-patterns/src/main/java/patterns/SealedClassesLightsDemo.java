package patterns;

/**
 * We can create a closed hierarchy with sealed classes.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class SealedClassesLightsDemo {

    public static void main(String[] args) {
        TrafficLight light = new RedLight();
        System.out.println("light = " + light);
    }
}

sealed interface TrafficLight {

}

final class RedLight implements TrafficLight {

    @Override
    public String toString() {
        return "RedLight{}";
    }
}

non-sealed class GreenLight implements TrafficLight {

    @Override
    public String toString() {
        return "GreenLight{}";
    }
}
