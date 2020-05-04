package by.dma.commons;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Different utils.
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
class UtilsTest {

    @Test
    void toJDbcParams() {
        Assertions.assertEquals("?,?,?", new Utils().toJDbcParams(List.of(1, 2, 3)));
        Assertions.assertEquals("?,?,?,?,?", new Utils().toJDbcParams(List.of(1, 2, 3, 4, 5)));
    }
}
