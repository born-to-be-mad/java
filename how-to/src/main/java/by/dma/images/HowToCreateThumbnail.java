package by.dma.images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Creates thumbnail images from a directory of original images via `ImageMagick`
 * (`convert` utility is installed as part of ImageMagick).
 *
 * @author dzmitry.marudau
 * @since 2023.01
 */
public class HowToCreateThumbnail {

    static String userHome = System.getProperty("user.home");
    static Path thumbnailsDir = Path.of(userHome).resolve(".photos");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(thumbnailsDir);

        String directory = args.length == 1 ? args[0] : ".";
        Path sourceDir = Path.of(directory);

        AtomicInteger counter = new AtomicInteger();
        long start = System.currentTimeMillis();

        try (Stream<Path> files = Files.walk(sourceDir)
                                       .filter(Files::isRegularFile)
                                       .filter(HowToCreateThumbnail::isImage)) {
            files.forEach(f -> {
                counter.incrementAndGet();
                new ImageMagickExecutor().createThumbnail(f, thumbnailsDir.resolve(f.getFileName()));
            });
        }

        long end = System.currentTimeMillis();
        System.out.println(
                "Converted " + counter + " images to thumbnails. Took " + ((end - start) * 0.001) + "seconds");
    }

    private static boolean isImage(Path path) {
        try {
            String mimeType = Files.probeContentType(path);
            return mimeType != null && mimeType.contains("image");
        } catch (IOException e) {
            return false;
        }
    }

}
