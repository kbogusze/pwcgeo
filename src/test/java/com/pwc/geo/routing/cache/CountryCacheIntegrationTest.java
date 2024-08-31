package com.pwc.geo.routing.cache;

import com.pwc.geo.routing.client.CountryClient;
import com.pwc.geo.routing.dto.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CountryCacheIntegrationTest {

    @Autowired
    private CountryClient countryClient;

    @Test
    void getCountriesJson() {
        List<Country> countries = countryClient.getCountries();
        assertFalse(countries.isEmpty(), "The countries list should not be empty");
    }
}
