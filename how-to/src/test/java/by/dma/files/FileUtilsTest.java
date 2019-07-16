package by.dma.files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
class FileUtilsTest {

    private static final String TARGET_FILE = "target/test-classes/TestDocument_copy.pdf";
    private static final String SOURCE_FILE = "target/test-classes/TestDocument.pdf";

    @AfterEach
    void setUp() throws IOException {
        Files.delete(Paths.get(TARGET_FILE));
    }

    @Test
    void copyFileViaFileStreams() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        FileUtils.copyFileViaFileStreams(new File(SOURCE_FILE), new File(TARGET_FILE));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaFileStreams", Duration.between(start, LocalDateTime.now()));
        assertTrue(Files.exists(Paths.get(TARGET_FILE)));
    }

    @Test
    void copyFileViaFileChannels() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        FileUtils.copyFileViaFileChannels(new File(SOURCE_FILE), new File(TARGET_FILE));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaFileChannels", Duration.between(start, LocalDateTime.now()));
        assertTrue(Files.exists(Paths.get(TARGET_FILE)));
    }

    @Test
    void copyFileViaApacheCommonsIO() throws IOException {
        LocalDateTime start = LocalDateTime.now();
        FileUtils.copyFileViaApacheCommonsIO(new File(SOURCE_FILE), new File(TARGET_FILE));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaApacheCommonsIO", Duration.between(start, LocalDateTime.now()));
        assertTrue(Files.exists(Paths.get(TARGET_FILE)));
    }

    @Test
    void copyFileViaJava7Files() throws IOException  {
        LocalDateTime start = LocalDateTime.now();
        FileUtils.copyFileViaJava7Files(new File(SOURCE_FILE), new File(TARGET_FILE));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaJava7Files", Duration.between(start, LocalDateTime.now()));
        assertTrue(Files.exists(Paths.get(TARGET_FILE)));
    }
}