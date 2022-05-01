package by.dma.bytecode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.05
 */
public class BytecodeReader {

    public static final int BYTE_MASK = 0xFF;

    public static void main(String[] args) throws IOException {
        Path classfile = Path.of("path-to-file\\ByteCodeDemo.class");// path to classfile
        // ref https://stackoverflow.com/a/12310078/893197
        byte[] bytes = Files.readAllBytes(classfile);
        int byteNumber = 1;
        for (byte aByte : bytes) {
            String byteString = String.format("%5s: %8s", byteNumber++,
                                              Integer.toBinaryString(aByte & BYTE_MASK))
                                      .replace(' ', '0');
            System.out.println(byteString);
        }
    }

}
