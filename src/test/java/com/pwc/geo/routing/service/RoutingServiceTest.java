package com.pwc.geo.routing.service;

import com.pwc.geo.routing.cache.CountryCache;
import com.pwc.geo.routing.graph.DijkstraAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoutingServiceTest {

    private RoutingService routingService;
    private CountryCache countryCache;
    private DijkstraAlgorithm dijkstraAlgorithm;

    @BeforeEach
    void setUp() {
        countryCache = Mockito.mock(CountryCache.class);
        dijkstraAlgorithm = Mockito.mock(DijkstraAlgorithm.class);
        routingService = new RoutingService(countryCache, dijkstraAlgorithm);
    }

    @Test
    void getRoutePolPol() {
        String origin = "POL";
        String destination = "POL";
        List<String> expectedPath = Collections.singletonList("POL");

        List<String> actualPath = routingService.getRoute(origin, destination);
        assertEquals(expectedPath, actualPath);
    }

}