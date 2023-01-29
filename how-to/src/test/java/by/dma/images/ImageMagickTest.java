package by.dma.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2023.01
 */
public class ImageMagickTest {

    public static final String TEST_IMAGE = "pexels-pixabay-47367.jpg";
    public ImageMagickExecutor imageMagickExecutor = new ImageMagickExecutor();

    @Test
    @EnabledIfImageMagickIsInstalled
    void thumbnail_creation_works(@TempDir Path testDir) throws IOException {
        Path originalImage = copyTestImageTo(testDir.resolve("large.jpg"));
        Path thumbnail = testDir.resolve("thumbnail.jpg");

        imageMagickExecutor.createThumbnail(originalImage, thumbnail);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(thumbnail).exists();
        softly.assertThat(Files.size(thumbnail)).isLessThan(Files.size(originalImage) / 2);
        softly.assertThat(getDimensions(thumbnail).width()).isEqualTo(300);
        softly.assertAll();
    }

    private Dimensions getDimensions(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            BufferedImage read = ImageIO.read(is);
            return new Dimensions(read.getWidth(), read.getHeight());
        } catch (IOException e) {
            return new Dimensions(-1, -1);
        }
    }

    public record Dimensions(int width, int height) {

    }

    private static Path copyTestImageTo(Path targetFile) {
        try (InputStream resourceAsStream = ImageMagickTest.class.getResourceAsStream("/" + TEST_IMAGE)) {
            Files.copy(resourceAsStream, targetFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied test image to: = " + targetFile.toAbsolutePath());
            return targetFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
