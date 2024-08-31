package com.pwc.geo.routing.controller;

import com.pwc.geo.routing.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService;

    @Autowired
    public RoutingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/{origin}/{destination}")
    public List<String> getRoute(@PathVariable String origin, @PathVariable String destination) {
        return routingService.getRoute(origin, destination);
    }
}