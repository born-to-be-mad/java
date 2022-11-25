package by.dma.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
class NonBlockingArrayListTest {

    @Test
    void testAdd() throws InterruptedException {
        var list = new NonBlockingArrayList<String>()
                .add("aa")
                .add("bb")
                .add("cc")
                .add("dd");
        Assertions.assertEquals(list.size(), 4);
    }

}
