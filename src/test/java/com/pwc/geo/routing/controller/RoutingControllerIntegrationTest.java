package com.pwc.geo.routing.controller;

import com.pwc.geo.routing.exception.PathNotFoundException;
import com.pwc.geo.routing.service.RoutingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoutingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RoutingService routingService;

    @Test
    void getRoutePolIta() throws Exception {
        String origin = "POL";
        String destination = "ITA";
        when(routingService.getRoute(origin, destination)).thenReturn(Arrays.asList("POL", "CZE", "AUT", "ITA"));

        mockMvc.perform(get("/routing/{origin}/{destination}", origin, destination))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"POL\", \"CZE\", \"AUT\", \"ITA\"]"));
    }

    @Test
    void getRoutePolUsa() throws Exception {
        String origin = "POL";
        String destination = "USA";
        when(routingService.getRoute(origin, destination)).thenThrow(new PathNotFoundException("Test"));

        mockMvc.perform(get("/routing/{origin}/{destination}", origin, destination))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRoutePolPol() throws Exception {
        String origin = "POL";
        String destination = "POL";
        when(routingService.getRoute(origin, destination)).thenThrow(new PathNotFoundException("Test"));

        mockMvc.perform(get("/routing/{origin}/{destination}", origin, destination))
                .andDo(print())
                .andExpect(status().isOk());
    }
}