package by.dma.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * How to read files properly in the directory.
 * <p>
 * The explanation of this resource leak comes with "API Note" on the bottom of some methods in java.nio.files.Files
 * class. TL;DR these methods return custom impl of Stream that requires Closable#close() call to terminate internal IO.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
public class HowToListFilesInDirectory {

    public static void main(String[] args) throws IOException {
        String directory = "directory";
        // bad way
        List<Path> filesInDirectoryLeak = Files.list(Paths.get(directory))
                                               .filter(Files::isRegularFile)
                                               .toList();
        //proper way
        try (Stream<Path> stream = Files.list(Paths.get(directory))) {
            List<Path> filesInDirectoryCorrect = stream
                    .filter(Files::isRegularFile).toList();
        }

    }
}
