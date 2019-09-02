package recipes.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:21
 * @since : 2019.09
 **/
class CompletableFutureDemoTest {

    CompletableFutureDemo demo =new CompletableFutureDemo();

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(ExecutionException.class, () -> {
            demo.getProduct(3).get();;
        });
    }

    @Test
    public void testExceptionWithCause() throws Exception {
        try {
            demo.getProduct(3).get();
            fail("We have a problem...");
        } catch (ExecutionException e) {
            assertEquals(ExecutionException.class, e.getClass());
            assertEquals(RuntimeException.class, e.getCause().getClass());
        }
    }
}