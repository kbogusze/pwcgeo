package com.pwc.geo.routing.graph;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DijkstraAlgorithmImplementation implements DijkstraAlgorithm {

    public List<String> findShortestPath(Map<String, List<String>> graph, String origin, String destination) {

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> nodes = initializeQueue(graph, origin, distances);

        while (!nodes.isEmpty()) {
            String currentNode = nodes.poll();

            if (currentNode.equals(destination)) {
                return constructPath(previousNodes, destination);
            } else if (graph.get(currentNode) != null) {
                for (String neighbor : graph.getOrDefault(currentNode, Collections.emptyList())) {
                    int newDist = distances.get(currentNode) + 1; // Assuming all edges have weight 1
                    if (distances.get(neighbor)!= null && newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        previousNodes.put(neighbor, currentNode);
                        nodes.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList(); // Return empty list if no path found
    }

    private static PriorityQueue<String> initializeQueue(Map<String, List<String>> graph, String origin, Map<String, Integer> distances) {
        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String node : graph.keySet()) {
            if (node.equals(origin)) {
                distances.put(node, 0);
            } else {
                distances.put(node, Integer.MAX_VALUE);
            }
            nodes.add(node);
        }
        return nodes;
    }

    private List<String> constructPath(Map<String, String> previousNodes, String destination) {
        List<String> path = new ArrayList<>();
        for (String at = destination; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
