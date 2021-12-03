package by.dma;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Different ways to create sets.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class SetsFromMaps {

    public static void main(String[] args) {
        Set<?>[] sets = {
                new HashSet<>(), //java 2
                new TreeSet<>(), //java 2
                new LinkedHashSet<>(), //java 4
                new CopyOnWriteArraySet<>(),  // java 5
                EnumSet.allOf(Thread.State.class), // java 5
                new ConcurrentSkipListSet<>(), // java 6
                Collections.newSetFromMap(new ConcurrentHashMap<>()), //java 6
                ConcurrentHashMap.newKeySet() //java 8
        };
        System.out.println("Available sets in java:");
        Arrays.stream(sets).forEach(set -> System.out.println(set.getClass().getName()));

        Map<?, ?>[] maps = {
                new Hashtable<>(), // java 1
                new WeakHashMap<>(), //java 2
                new IdentityHashMap<>(), // java 4
                new ConcurrentHashMap<>() //java 5
        };
        System.out.println("Available maps in java:");
        Arrays.stream(maps).forEach(map -> System.out.println(map.getClass().getName()));
    }
}
