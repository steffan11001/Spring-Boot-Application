package com.example.microservices.nightTimeTemperature.controllers;

import com.example.microservices.Temperature;
import com.example.microservices.nightTimeTemperature.services.NightTimeTemperatureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class NightTimeTemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NightTimeTemperatureService nightTimeTemperatureService;

    @Test
    void shouldGetTempOnDayTime() throws Exception {
        when(nightTimeTemperatureService
                .callAPI(47.155371,27.596215))
                .thenReturn(new Temperature(6000));
        mockMvc.perform(get("/night-time-temperature?lat=47.155371&lng=27.596215"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"temperature\":6000}")));
    }
    void shouldGetTempOnNightTime() throws Exception {
        when(nightTimeTemperatureService
                .callAPI(47.155371, 27.596215))
                .thenReturn(new Temperature(6000));
        mockMvc.perform(get("/night-time-temperature?lat=47.155371&lng=27.596215"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"temperature\":2700}")));
    }
    void shouldGetTempOnTwilightTime() throws Exception {
        when(nightTimeTemperatureService
                .callAPI(47.155371, 27.596215))
                .thenReturn(new Temperature(6000));
        mockMvc.perform(get("/night-time-temperature?lat=47.155371&lng=27.596215"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"temperature\":2700}")));
    }
}