package by.dma.jvm;

import com.sun.jna.Platform;

/**
 * Identify JVM Bit version
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class JVMBitVersion {
    public String getUsingSystemClass() {
        return System.getProperty("sun.arch.data.model") + "-bit";
    }

    public String getUsingNativeClass() {
        if (com.sun.jna.Native.POINTER_SIZE == 8) {
            return "64-bit";
        }
        if (com.sun.jna.Native.POINTER_SIZE == 4) {
            return "32-bit";
        }
        return "unknown";
    }

    public boolean getUsingPlatformClass() {
        return (Platform.is64Bit());
    }
}
