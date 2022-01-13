import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExtObjectsTest {

    @Test
    void shouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ExtObjects.requireNotNullOrElseThrow(null, () -> new IllegalArgumentException("Object cannot be null"));
        });
    }

    @Test
    void shouldThrowIllegalStateException() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            ExtObjects.requireNotNullOrElseThrow(null, () -> new IllegalStateException("Object cannot be null"));
        });
    }
}
