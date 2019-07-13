package patterns;

import java.awt.*;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class DecoratorDemo {
    public static void main(String[] args) {
        Function<Integer,Integer> square = a -> a*a;
        print(5, "squared", square);
        print(5, "doubled", a -> a*2);
        print(5, "squared and doubled", square.andThen(a -> a*2));
        print(new Camera());
        print(new Camera(Color::brighter));
        print(new Camera(Color::brighter, Color::brighter));
        print(new Camera(Color::darker));
    }

    private static void print(Camera camera) {
        System.out.println(camera.snap(new Color(125,124,123)));
    }

    private static void print(int number, String msg, Function<Integer,Integer> function) {
        System.out.println(number + " " + msg + " " + function.apply(number));
    }
}
//Camera can have different filter modules
@SuppressWarnings("unchecked")
class Camera {
    Function<Color, Color>[] filters;
    Camera(Function<Color, Color>... filters) {
        this.filters = filters;
    }

    Color snap(Color input) {
        for (Function<Color, Color> filter : filters) {
            input= filter.apply(input);
        }

        return input;
    }
}
