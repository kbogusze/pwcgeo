package com.pwc.geo.routing.service;

import com.pwc.geo.routing.cache.CountryCache;
import com.pwc.geo.routing.graph.DijkstraAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoutingService {

    private final CountryCache countryCache;
    private final DijkstraAlgorithm dijkstraAlgorithm;

    @Autowired
    public RoutingService(CountryCache countryCache, DijkstraAlgorithm dijkstraAlgorithm) {
        this.countryCache = countryCache;
        this.dijkstraAlgorithm = dijkstraAlgorithm;
    }

    public List<String> getRoute(String origin, String destination) {
        List<String> result;

        if (origin.equals(destination)) {
            result = Collections.singletonList(origin);
        } else {
            result = dijkstraAlgorithm.findShortestPath(countryCache.getCountryGraph().findValidGraph(origin, destination), origin, destination);
        }

        return result;
    }
}
