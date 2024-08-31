package com.pwc.geo.routing.graph;

import java.util.List;
import java.util.Map;

public interface DijkstraAlgorithm {
    List<String> findShortestPath(Map<String, List<String>> graph, String origin, String destination);
}
