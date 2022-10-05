package com.example.microservices.nightTimeTemperature.controllers;

import com.example.microservices.Temperature;
import com.example.microservices.nightTimeTemperature.services.NightTimeTemperatureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/night-time-temperature")
public class NightTimeTemperatureController {

    @Autowired
    private NightTimeTemperatureService nightTimeTemperatureService;

    @GetMapping("/night-time-temperature")
    public Temperature getTemp(@RequestParam double lat, @RequestParam double lng) throws JsonProcessingException {
        return nightTimeTemperatureService.callAPI(lat, lng);
    }
    @GetMapping("/hello")
    public String hello()
    {
        return "Hello";
    }
}
