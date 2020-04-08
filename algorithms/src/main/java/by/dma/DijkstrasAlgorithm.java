package by.dma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dijkstra’s algorithm is used to calculate the shortest path in a weighted graph.
 *
 * @author : Dzmitry Marudau
 * @created at : 01:02
 * @since : 2020.04
 **/
public class DijkstrasAlgorithm {
    // the graph, where key holds the neighbour
    private Map<String, Map<String, Double>> graph = new HashMap<>();

    // The costs table to store the costs for each node(how long it takes to get to that node from the start.)
    Map<String, Double> costs = new HashMap<>();

    // the parents table
    Map<String, String> parents = new HashMap<>();

    // an array to keep track of all the nodes already processed, because we don’t need to process a node more than once
    private List<String> processed = new ArrayList<>();

    public static void main(String[] args) {
        DijkstrasAlgorithm app = new DijkstrasAlgorithm();
        app.init();
        app.calculateCosts();

    }

    private String findLowestCostNode(Map<String, Double> costs) {
        Double lowestCost = Double.POSITIVE_INFINITY;
        String lowestCostNode = null;

        // Go through each node
        for (Map.Entry<String, Double> node : costs.entrySet()) {
            Double cost = node.getValue();
            // If it's the lowest cost so far and hasn't been processed yet...
            if (cost < lowestCost && !processed.contains(node.getKey())) {
                // ... set it as the new lowest-cost node.
                lowestCost = cost;
                lowestCostNode = node.getKey();
            }
        }

        return lowestCostNode;
    }

    private void calculateCosts() {

        String node = findLowestCostNode(costs);
        while (node != null) {
            Double cost = costs.get(node);
            // Go through all the neighbors of this node

            Map<String, Double> neighbors = graph.get(node);

            for (String n : neighbors.keySet()) {
                double newCost = cost + neighbors.get(n);
                // If it's cheaper to get to this neighbor by going through this node
                if (costs.get(n) > newCost) {
                    // ... update the cost for this node
                    costs.put(n, newCost);
                    // This node becomes the new parent for this neighbor.
                    parents.put(n, node);
                }
            }
            // Mark the node as processed
            processed.add(node);

            // Find the next node to process, and loop
            node = findLowestCostNode(costs);
        }

        System.out.println("Cost from the start to each node:");
        System.out.println(costs); // { a: 5, b: 2, fin: 6 }
    }

    private void init() {
        graph.put("start", new HashMap<>());

        graph.get("start")
                .put("a", 6.0);
        graph.get("start")
                .put("b", 2.0);

        graph.put("a", new HashMap<>());
        graph.get("a")
                .put("fin", 1.0);

        graph.put("b", new HashMap<>());
        graph.get("b")
                .put("a", 3.0);
        graph.get("b")
                .put("fin", 5.0);

        graph.put("fin", new HashMap<>());

        costs.put("a", 6.0);
        costs.put("b", 2.0);
        costs.put("fin", Double.POSITIVE_INFINITY);

        parents.put("a", "start");
        parents.put("b", "start");
        parents.put("fin", null);

    }
}
