package by.dma.jar;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class HowToReadJar {

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            JarFile jarFile = new JarFile(arg);
            System.out.printf("### Reading %s...%n", arg);
            printEntries(jarFile);
            System.out.printf("### %s DONE %n", arg);
        }

    }

    /**
     * org/springframework/core/io/support/PathMatchingResourcePatternResolver.java:649
     */
    private static void printEntries(JarFile jarFile) {
        for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements(); ) {
            JarEntry entry = entries.nextElement();
            String entryPath = entry.getName();
            if (!entryPath.endsWith("/")) {
                System.out.println(entryPath);
            }
        }
    }
}
