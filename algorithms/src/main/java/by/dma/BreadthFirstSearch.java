package by.dma;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Breadth-First Search algorithm for graphs.
 * It tells if there’s a path from a node A to a node B.
 * If there’s a path, breadth-first search will find the shortest path
 *
 * @author : Dzmitry Marudau
 * @created at : 00:59
 * @since : 2020.04
 **/
public class BreadthFirstSearch {

    private Map<String, List<String>> graph = new HashMap<>();

    public boolean search(String name) {
        /**
         * You need to check people in the order they were added to the search list,
         * so the search list needs to be a queue.
         * Otherwise, you won’t get the shortest path.
        */
        Queue<String> searchQueue = new ArrayDeque<>(graph.get(name));

        // This list is how you keep track of which people you've searched before.
        List<String> searched = new ArrayList<>();

        while (!searchQueue.isEmpty()) {
            String current = searchQueue.poll();
            // Only search this current if you haven't already searched them
            // Once you check someone, make sure you don’t check them again.
            // Otherwise, you might end up in an infinite loop
            if (!searched.contains(current)) {
                if (isCorrectPerson(current)) {
                    System.out.printf("The required node(%s) is found!%n", current);
                    return true;
                } else {
                    searchQueue.addAll(graph.get(current));
                    // Marks this current as searched
                    searched.add(current);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        BreadthFirstSearch app = new BreadthFirstSearch();
        app.initPaths();
        System.out.printf("Search result for %s: %s%n", "you", app.search("you"));
        System.out.printf("Search result for %s: %s%n", "alice", app.search("alice"));
    }

    private static boolean isCorrectPerson(String name) {
        return name.endsWith("m");
    }

    private void initPaths() {
        graph.put("you", Arrays.asList("alice", "bob", "claire"));
        graph.put("bob", Arrays.asList("anuj", "peggy"));
        graph.put("alice", Collections.singletonList("peggy"));
        graph.put("claire", Arrays.asList("thom", "jonny"));
        graph.put("anuj", Collections.emptyList());
        graph.put("peggy", Collections.emptyList());
        graph.put("thom", Collections.emptyList());
        graph.put("jonny", Collections.emptyList());
    }
}
