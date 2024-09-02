package com.pwc.geo.routing.graph;

import com.pwc.geo.routing.dto.Country;
import com.pwc.geo.routing.exception.PathNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CountryGraphTest {

    private CountryGraph countryGraph;

    @BeforeEach
    public void setUp() {

        // Creating a list of Country objects
        List<Country> countries = Arrays.asList(
                new Country("CAN", Arrays.asList("USA")),
                new Country("USA", Arrays.asList("CAN", "MEX")),
                new Country("MEX", Arrays.asList("USA")),
                new Country("ALA", Arrays.asList()),

                new Country("CZE", Arrays.asList("AUT", "DEU", "POL", "SVK")),
                new Country("DEU", Arrays.asList("AUT", "BEL", "CZE", "DNK", "FRA", "LUX", "NLD", "POL", "CHE")),
                new Country("ITA", Arrays.asList("AUT", "FRA", "SMR", "SVN", "CHE", "VAT")),
                new Country("AUT", Arrays.asList("CZE", "DEU", "SVK", "SVN")),
                new Country("POL", Arrays.asList("BLR", "CZE", "DEU", "LTU", "RUS", "SVK", "UKR"))
        );

        countryGraph = new CountryGraph(countries);

    }

    @Test
    void findValidGraphCanMex() {
        assertNotNull(countryGraph.findValidGraph("CAN", "MEX"));
    }

    @Test
    void noValidGraphPolMex() {
        assertThrows(PathNotFoundException.class,
                () ->countryGraph.findValidGraph("POL", "MEX"));
    }

    @Test
    void wrongCountryCodeOrigin() {
        assertThrows(PathNotFoundException.class,
                () -> countryGraph.findValidGraph( "PL", "POL"));
    }

    @Test
    void wrongCountryCodeDestination() {
        assertThrows(PathNotFoundException.class,
                () -> countryGraph.findValidGraph("POL", "PL"));

    }

    @Test
    void noBordersDestination() {
        assertThrows(PathNotFoundException.class,
                () -> countryGraph.findValidGraph( "POL", "ALA"));
    }

    @Test
    void noBordersOrigin() {
        assertThrows(PathNotFoundException.class,
                () -> countryGraph.findValidGraph("ALA", "POL"));
    }

}
