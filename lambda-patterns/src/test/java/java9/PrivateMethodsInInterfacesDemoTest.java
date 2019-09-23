package java9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:56
 * @since : 2019.09
 **/
class PrivateMethodsInInterfacesDemoTest {

    PrivateMethodsInInterfacesDemo summarizer;

    @BeforeEach
    void setUp() {
        summarizer = new PrivateMethodsInInterfacesDemo() {
        };
    }

    @Test
    void sumEvens() {
        assertEquals(2 + 4 + 6 + 8,
                summarizer.sumEvens(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    void sumOdds() {
        assertEquals(1 + 3 + 5 + 7 + 9,
                summarizer.sumOdds(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

}