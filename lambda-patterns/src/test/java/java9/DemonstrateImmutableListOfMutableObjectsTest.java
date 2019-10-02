package java9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:37
 * @since : 2019.10
 **/
public class DemonstrateImmutableListOfMutableObjectsTest {
    @Test
    public void areWeImmutableOrArentWe() throws Exception {
        List<ValueHolder> holders = List.of(ValueHolder.of(2), ValueHolder.of(3));
        assertEquals(2, holders.get(0).getX());
        holders.get(0).setX(4);
        assertEquals(4, holders.get(0).getX());
    }
}
