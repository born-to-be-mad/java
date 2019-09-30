package java9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:16
 * @since : 2019.09
 **/
public class DemonstrateSetImmutabilityTest {
    @Test
    public void showDuplicatesAdd() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    Set<String> names = Set.of("Java", "Scala", "Groovy", "Java", "Kotlin");
                }
        );
    }

    @Test
    public void immutableMapFromEntries() {
        Set<String> names = Set.of("Java", "Scala", "Groovy", "Clojure", "Kotlin");
        List<String> urls = List.of("http://www.oracle.com/technetwork/java/index.html",
                "http://groovy-lang.org/",
                "http://www.scala-lang.org/",
                "https://clojure.org/",
                "http://kotlinlang.org/");

        Map<String, String> jvmLanguages = Map.ofEntries(
                Map.entry("Java", "http://www.oracle.com/technetwork/java/index.html"),
                Map.entry("Groovy", "http://groovy-lang.org/"),
                Map.entry("Scala", "http://www.scala-lang.org/"),
                Map.entry("Clojure", "https://clojure.org/"),
                Map.entry("Kotlin", "http://kotlinlang.org/"));

        Set<String> keys = jvmLanguages.keySet();
        Collection<String> values = jvmLanguages.values();
        names.forEach(name -> assertTrue(keys.contains(name)));
        urls.forEach(url -> assertTrue(values.contains(url)));

        Map<String, String> javaMap = Map.of("Java",
                "http://www.oracle.com/technetwork/java/index.html",
                "Groovy",
                "http://groovy-lang.org/",
                "Scala",
                "http://www.scala-lang.org/",
                "Clojure",
                "https://clojure.org/",
                "Kotlin",
                "http://kotlinlang.org/");
        javaMap.forEach((name, url) ->
                assertTrue(jvmLanguages.containsKey(name)
                        &&
                        jvmLanguages.containsValue(url)));
    }
}
