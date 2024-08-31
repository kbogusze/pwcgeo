package com.pwc.geo.routing.cache;

import com.pwc.geo.routing.client.CountryClient;
import com.pwc.geo.routing.graph.CountryGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryCache {
    private CountryGraph countryGraph;

    @Autowired
    CountryCache(CountryClient countryClient) {
        this.countryGraph = new CountryGraph(countryClient.getCountries());
    }

    public CountryGraph getCountryGraph() {
        return countryGraph;
    }
}
