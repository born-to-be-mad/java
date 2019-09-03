package by.dma.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class JUnit5DemoTest {

    private static LinearSearcher searcher;

    //the static method that will be executed once before all @Test methods in the current class
    @BeforeAll
    static void setup() {
        int[] array = {2, 3, 4, 10, 40};
        searcher = new LinearSearcher(array);
    }

    //the method that will be executed before each @Test method in the current class.
    @BeforeEach
    void printTestHeader() {
        System.out.println("#### START ####");
    }

    //the method that will be executed after each @Test method in the current class.
    @AfterEach
    void printTestFooter() {
        System.out.println("#### FINISH ####");
    }

    @Test
    void availableAssertions() {
        assertNotNull(searcher);
        assertArrayEquals(new int[] {1, 2, 3}, new int[] {1, 2, 3});
        assertFalse(false);
        assertTrue(true);
        assertIterableEquals(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
    }

    @Test
    void exceptionThrownTest() {
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            searcher.callMethodWithException();
        });
    }


    @Test
    void inDurationTest(){
        Assertions.assertTimeout(Duration.ofSeconds(3), ()->{
            System.out.println("Test");
        });
    }

    @Test
    void getPosition() {
        int result = searcher.getPositionOfNumber(10);
        Assertions.assertEquals(3, result);
    }

    @Test
    void noSuchNumber() {
        int result = searcher.getPositionOfNumber(55);
        Assertions.assertEquals(-1, result);
    }

    //the static method executed once after all @Test methods in the current class.
    @AfterAll
    static void finish() {
        System.out.println("Tests are finished!");
    }

}

class LinearSearcher {
    private int[] data;

    LinearSearcher(int[] arr) {
        this.data = arr;
    }

    int getPositionOfNumber(int value) {
        int dataLength = data.length;
        for (int index = 0; index < dataLength; index++) {
            if (data[index] == value) {
                return index;
            }
        }
        return -1;
    }

    void callMethodWithException() throws FileNotFoundException {
        throw new FileNotFoundException("Test exception");
    }
}

