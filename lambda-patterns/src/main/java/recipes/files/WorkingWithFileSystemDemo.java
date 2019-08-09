package recipes.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author : Dzmitry Marudau
 * @created at : 23:57
 * @since : 2019.08
 **/
public class WorkingWithFileSystemDemo {

    private static final String FILE_PATH = "src/main/java/recipes";

    public static void main(String[] args) {
        try (Stream<Path> list = Files.list(Paths.get(FILE_PATH))) {
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("### Walking the Filesystem");
        try (Stream<Path> list = Files.list(Paths.get(FILE_PATH))) {
            list.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("### Finding the non-directory files");
        try (Stream<Path> paths =
                     Files.find(Paths.get(FILE_PATH), Integer.MAX_VALUE,
                             (path, attributes) -> !attributes.isDirectory())) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
