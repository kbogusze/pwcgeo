package com.pwc.geo.routing.graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraAlgorithmImplementationTest {

    @Test
    void findValidPathCanMex() {
        Map<String,List<String>> countries = new HashMap<>();

        countries.put("CAN", Arrays.asList("USA"));
        countries.put("USA", Arrays.asList("CAN", "MEX"));
        countries.put("MEX", Arrays.asList("USA"));

        List<String> expectedPath = Arrays.asList("CAN", "USA", "MEX");
        List<String> actualPath = new DijkstraAlgorithmImplementation().findShortestPath(countries, "CAN", "MEX");

        assertEquals(expectedPath, actualPath);
    }

    @Test
    void findValidPathPolIta() {
        Map<String,List<String>> countries = new HashMap<>();

        countries.put("CZE", Arrays.asList("AUT", "DEU", "POL", "SVK"));
        countries.put("DEU", Arrays.asList("AUT", "BEL", "CZE", "DNK", "FRA", "LUX", "NLD", "POL", "CHE"));
        countries.put("ITA", Arrays.asList("AUT", "FRA", "SMR", "SVN", "CHE", "VAT"));
        countries.put("AUT", Arrays.asList("CZE", "DEU", "SVK", "ITA"));
        countries.put("POL", Arrays.asList("BLR", "CZE", "DEU", "LTU", "RUS", "SVK", "UKR"));

        List<String> expectedPath = Arrays.asList("POL", "CZE", "AUT", "ITA");
        List<String> actualPath = new DijkstraAlgorithmImplementation().findShortestPath(countries, "POL", "ITA");

        assertEquals(expectedPath, actualPath);
    }

    @Test
    void defendAgainstNull() {
        List<String> actualPath = new DijkstraAlgorithmImplementation().findShortestPath(null, "CAN", "MEX");
        assertTrue(actualPath.isEmpty());
    }

}