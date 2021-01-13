package by.dma.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class FileUtils {
    public static void copyFileViaFileStreams(File source, File dest) throws IOException {
        try (InputStream input = new FileInputStream(source); OutputStream output = new FileOutputStream(dest)) {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
    }

    public static void copyFileViaFileChannels(File source, File dest) throws IOException {
        try (FileChannel inputChannel = new FileInputStream(source)
                .getChannel(); FileChannel outputChannel = new FileOutputStream(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
    }

    public static void copyFileViaApacheCommonsIO(File source, File dest) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(source, dest);
    }

    public static void copyFileViaJava7Files(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    /**
     * Split lines of text into separate words,
     * filter out dumb words to keep everything clean,
     * group words by the first letter, count how many words are in each group.
     */
    public static Map<Character, Long> collectWords(List<String> strings) {
        return strings.parallelStream()
                      .flatMap(line -> Stream.of(line.split("\\s+")))
                      .filter(word -> !"dumb".equalsIgnoreCase(word))
                      .collect(groupingBy(word -> word.charAt(0), counting()))
                      .entrySet()
                      .parallelStream()
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
