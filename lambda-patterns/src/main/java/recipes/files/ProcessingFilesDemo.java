package recipes.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:27
 * @since : 2019.08
 **/
public class ProcessingFilesDemo {

    private static final String FILE_PATH = "/usr/share/dict/british-english";

    public static void main(String[] args) {
        System.out.println("### Finding the 10 longest lines in the dictionary");
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            lines.filter(s -> s.length() > 15)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("### Determining number of words of each length");
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            lines.filter(s -> s.length() > 15)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.println(len + ": " + num));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of words of each length, in descending order");
        try (Stream<String> lines = Files.lines(Paths.get(FILE_PATH))) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 15)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n",
                            e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
