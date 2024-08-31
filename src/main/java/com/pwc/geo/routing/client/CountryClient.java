package com.pwc.geo.routing.client;

import com.pwc.geo.routing.config.FeignConfig;
import com.pwc.geo.routing.dto.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "countryClient", url = "https://raw.githubusercontent.com/mledoze/countries/master" , configuration = FeignConfig.class)
public interface CountryClient {

    @GetMapping(value = "/countries.json" ,produces = MediaType.TEXT_PLAIN_VALUE)
    List<Country> getCountries();
}