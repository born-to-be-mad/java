package by.dma.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class ListRemoveIfChallenge {
    public static void main(String... dungeonsAndDragons) {
        List<Integer> list = new ArrayList<>();

        list.add(22);
        list.add(12);
        list.add(14);
        list.add(128);

        Integer prestoAge = 14;
        Integer dungeonMasterAge = 128;

        /*
        Integer numbers from -128 to 127 will be cached.
        That means that every variable created within this range will be cached,
        that means that a new object won't be created in the memory heap.

        e == new Integer(12)
        This condition will be false because when we use the new operator,
        we are forcing the creation of a new object in the memory heap. Caching won't work in this case.

        e == dungeonMasterAge
        The 14 number will be cached normally, so this condition will be true.

        e == prestoAge
        We are using here the number 128. Remember that the caching goes only until 127.
        So, this comparison will be false, this number will continue on the list.

        e.equals(new Integer(22))
        This condition will be true because we are using the equals method
        that compares the value of the object not the reference of the object.
        */
        list.removeIf(e -> e == new Integer(12)
                           || e == dungeonMasterAge
                           || e == prestoAge
                           || e.equals(new Integer(22)));

        System.out.println(list);
    }

}
