package by.dma.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class FileUtils {
    public static void copyFileViaFileStreams(File source, File dest) throws IOException {
        try (InputStream input = new FileInputStream(source);
             OutputStream output = new FileOutputStream(dest)) {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
    }

    public static void copyFileViaFileChannels(File source, File dest) throws IOException {
        try (FileChannel inputChannel = new FileInputStream(source).getChannel();
             FileChannel outputChannel = new FileOutputStream(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
    }

    public static void copyFileViaApacheCommonsIO(File source, File dest) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(source, dest);
    }

    public static void copyFileViaJava7Files(File source, File dest)
        throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

}
