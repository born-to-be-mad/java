package recipes.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
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

    @Test
    public void compose() throws Exception {
        int x = 11;
        int y = 22;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));
        assertEquals(33, (int) completableFuture.get());
    }

    @Test
    public void combine() throws Exception {
        int x = 22;
        int y = 33;
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCombine(CompletableFuture.supplyAsync(() -> y),
                                (n1, n2) -> n1 + n2);
        assertEquals(55, (int) completableFuture.get());
    }
}