package recipes.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:27
 * @since : 2019.08
 **/
public class ProcessingFilesDemo {
    public static void main(String[] args) {
        System.out.println("### Finding the 10 longest lines in the dictionary");
        try (Stream<String> lines = Files.lines(Paths.get("/home/dma/java_error_in_IDEA_4455.log"))) {
            lines.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
