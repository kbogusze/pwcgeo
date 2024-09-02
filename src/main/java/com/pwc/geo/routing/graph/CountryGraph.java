package com.pwc.geo.routing.graph;

import com.pwc.geo.routing.dto.Country;
import com.pwc.geo.routing.exception.PathNotFoundException;

import java.util.*;

public class CountryGraph {
    private static final String INVALID_COUNTRY_CODE = "Origin or destination not a valid country code";
    private static final String INVALID_ORIGIN = "Is not possible to find route from this origin";
    private static final String INVALID_DESTINATION = "Is not possible to find route to this destination";
    private static final String ROUTE_NOT_FOUND = "Not possible to find route between origin and destination";

    private final Set<String> countrySet;
    private final Map<String, List<String>> countryWithBorders;
    private final List<Map<String, List<String>>> countryGraphList;

    public CountryGraph(final List<Country> countries) {
        this.countrySet = extractCountrySet(countries);
        this.countryWithBorders = extractCountryWithBorders(countries);
        this.countryGraphList = new ArrayList<>();
        generateAllSubGraphs();
    }

    private Set<String> extractCountrySet(List<Country> countries) {
        Set<String> result = new HashSet<>();
        for (Country country : countries) {
            result.add(country.getCca3());
        }
        return result;
    }

    private Map<String, List<String>> extractCountryWithBorders(List<Country> countries) {
        Map<String, List<String>> result = new HashMap<>();
        for (Country country : countries) {
            if (country.getBorders() != null && !country.getBorders().isEmpty()) {
                result.put(country.getCca3(), country.getBorders());
            }
        }
        return result;
    }

    private void generateAllSubGraphs() {
        Queue<String> nodes = new ArrayDeque<>();
        for (String country : countryWithBorders.keySet()) {
            if (countryGraphList.stream().noneMatch(m -> m.containsKey(country))) {
                Map<String, List<String>> subGraph = new HashMap<>();
                countryGraphList.add(subGraph);
                buildSubGraph(subGraph, country, nodes);
            }
        }
    }

    private void buildSubGraph(Map<String, List<String>> subGraph, String country, Queue<String> nodes) {
        subGraph.put(country, countryWithBorders.get(country));
        nodes.addAll(countryWithBorders.get(country));
        while (!nodes.isEmpty()) {
            String node = nodes.poll();
            if (!subGraph.containsKey(node)) {
                subGraph.put(node, countryWithBorders.get(node));
                if (countryWithBorders.get(node) != null) {
                    nodes.addAll(countryWithBorders.get(node));
                }
            }
        }
    }

    public Map<String, List<String>> findValidGraph(String origin, String destination) {
        validateCountryCodes(origin, destination);
        return getCountryGraphRoute(origin, destination)
                .orElseThrow(() -> new PathNotFoundException(ROUTE_NOT_FOUND));
    }

    private void validateCountryCodes(String origin, String destination) {
        if (!countrySet.contains(origin) || !countrySet.contains(destination)) {
            throw new PathNotFoundException(INVALID_COUNTRY_CODE);
        }
        if (!countryWithBorders.containsKey(origin)) {
            throw new PathNotFoundException(INVALID_ORIGIN);
        }
        if (!countryWithBorders.containsKey(destination)) {
            throw new PathNotFoundException(INVALID_DESTINATION);
        }
    }

    private Optional<Map<String, List<String>>> getCountryGraphRoute(String origin, String destination) {
        return countryGraphList.stream()
                .filter(m -> m.containsKey(origin) && m.containsKey(destination))
                .findFirst();
    }
}