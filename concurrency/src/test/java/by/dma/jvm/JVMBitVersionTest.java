package by.dma.jvm;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@code JVMBitVersion}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
class JVMBitVersionTest {

    JVMBitVersion jvmVersion = new JVMBitVersion();

    @Test
    public void whenUsingSystemClass_thenOutputIsAsExpected() {
        if (System.getProperty("sun.arch.data.model") == "64") {
            assertEquals("64-bit", jvmVersion.getUsingSystemClass());
        } else if (System.getProperty("sun.arch.data.model") == "32") {
            assertEquals("32-bit", jvmVersion.getUsingSystemClass());
        }
    }

    @Test
    public void whenUsingNativeClass_thenOutputIsAsExpected() {
        assertTrue(List.of("64-bit", "32-bit").contains(jvmVersion.getUsingNativeClass()));
    }

    @Test
    public void whenUsingPlatformClass_thenOutputIsAsExpected() {
        assertTrue(jvmVersion.getUsingPlatformClass());
    }
}
