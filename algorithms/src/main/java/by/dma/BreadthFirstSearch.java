package by.dma;

import java.util.*;

/**
 * Breadth-First Search algorithm for graphs.
 *
 * @author : Dzmitry Marudau
 * @created at : 00:59
 * @since : 2020.04
 **/
public class BreadthFirstSearch {

    private Map<String, List<String>> graph = new HashMap<>();

    public boolean search(String name) {
        Queue<String> searchQueue = new ArrayDeque<>(graph.get(name));

        // This list is how you keep track of which people you've searched before.
        List<String> searched = new ArrayList<>();

        while (!searchQueue.isEmpty()) {
            String current = searchQueue.poll();
            // Only search this current if you haven't already searched them
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
