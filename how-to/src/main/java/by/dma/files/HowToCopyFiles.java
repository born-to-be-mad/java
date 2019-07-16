package by.dma.files;

import java.io.File;
import java.io.IOException;

/**
 * @author dzmitry.marudau
 * @since 2019.7
 */
public class HowToCopyFiles {

    public static void main(String[] args) throws IOException {
        File source = new File("target/test-classes/TestDocument.pdf");

        long start = System.nanoTime();
        FileUtils.copyFileViaFileStreams(source, new File("target/test-classes/TestDocument_copyFileViaFileStreams.pdf"));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaFileStreams", (System.nanoTime() - start));

        start = System.nanoTime();
        FileUtils.copyFileViaFileChannels(source, new File("target/test-classes/copyFileViaFileChannels.pdf"));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaFileChannels", (System.nanoTime() - start));

        start = System.nanoTime();
        FileUtils.copyFileViaJava7Files(source, new File("target/test-classes/copyFileViaJava7Files.pdf"));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaJava7Files", (System.nanoTime() - start));

        start = System.nanoTime();
        FileUtils.copyFileViaApacheCommonsIO(source, new File("target/test-classes/copyFileViaApacheCommonsIO.pdf"));
        System.out.printf("Copy time via %s = %s%n", "copyFileViaApacheCommonsIO", (System.nanoTime() - start));
    }

}
