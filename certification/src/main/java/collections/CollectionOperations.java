package collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionOperations {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(List.of("Plane", "Bike", "Car"));
        List<String> list2 = new ArrayList<>(List.copyOf(list1));
        list1.sort(String::compareTo);
        list2.sort(String::compareTo);
        System.out.println(list1.equals(list2));
    }

}
