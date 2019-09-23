package java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:06
 * @since : 2019.09
 **/
public class DemonstrateImmutabilityTests {
    @Test
    public void showImmutabilityAdd() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
                    List<Integer> intList = List.of(1, 2, 3);
                    intList.add(99);
                }
        );
    }

    @Test
    public void showImmutabilityClear() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            List<Integer> intList = List.of(1, 2, 3);
            intList.clear();
        });
    }

    @Test
    public void showImmutabilityRemove() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
                    List<Integer> intList = List.of(1, 2, 3);
                    intList.remove(0);
                }
        );
    }

    @Test
    public void showImmutabilityReplace() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
                    List<Integer> intList = List.of(1, 2, 3);
                    intList.replaceAll(n -> -n);
                }
        );
    }

    @Test
    public void showImmutabilitySet() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
                    List<Integer> intList = List.of(1, 2, 3);
                    intList.set(0, 99);
                }
        );
    }
}
