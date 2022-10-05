package com.example.microservices.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class SunResultResponse {
    @JsonProperty("sunrise")
    private String sunrise;
    @JsonProperty("sunset")

    private String  sunset;
    @JsonProperty("solar_noon")
    private String solar_noon;
    @JsonProperty("day_length")

    private String day_length;
    @JsonProperty("civil_twilight_begin")

    private String civil_twilight_begin;
    @JsonProperty("civil_twilight_end")

    private String civil_twilight_end;
    @JsonProperty("nautical_twilight_begin")

    private String nautical_twilight_begin;
    @JsonProperty("nautical_twilight_end")

    private String nautical_twilight_end;
    @JsonProperty("astronomical_twilight_begin")

    private String astronomical_twilight_begin;
    @JsonProperty("astronomical_twilight_end")

    private String astronomical_twilight_end;

    private SunResultResponse() {
    }
    public SunResultResponse(String sunrise, String sunset, String solar_noon, String day_length, String civil_twilight_begin, String civil_twilight_end, String nautical_twilight_begin, String nautical_twilight_end, String astronomical_twilight_begin, String astronomical_twilight_end) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.solar_noon = solar_noon;
        this.day_length = day_length;
        this.civil_twilight_begin = civil_twilight_begin;
        this.civil_twilight_end = civil_twilight_end;
        this.nautical_twilight_begin = nautical_twilight_begin;
        this.nautical_twilight_end = nautical_twilight_end;
        this.astronomical_twilight_begin = astronomical_twilight_begin;
        this.astronomical_twilight_end = astronomical_twilight_end;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getSolar_noon() {
        return solar_noon;
    }

    public String getDay_length() {
        return day_length;
    }

    public String getCivil_twilight_begin() {
        return civil_twilight_begin;
    }

    public String getCivil_twilight_end() {
        return civil_twilight_end;
    }

    public String getNautical_twilight_begin() {
        return nautical_twilight_begin;
    }

    public String getNautical_twilight_end() {
        return nautical_twilight_end;
    }

    public String getAstronomical_twilight_begin() {
        return astronomical_twilight_begin;
    }

    public String getAstronomical_twilight_end() {
        return astronomical_twilight_end;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setSolar_noon(String solar_noon) {
        this.solar_noon = solar_noon;
    }

    public void setDay_length(String day_length) {
        this.day_length = day_length;
    }

    public void setCivil_twilight_begin(String civil_twilight_begin) {
        this.civil_twilight_begin = civil_twilight_begin;
    }

    public void setCivil_twilight_end(String civil_twilight_end) {
        this.civil_twilight_end = civil_twilight_end;
    }

    public void setNautical_twilight_begin(String nautical_twilight_begin) {
        this.nautical_twilight_begin = nautical_twilight_begin;
    }

    public void setNautical_twilight_end(String nautical_twilight_end) {
        this.nautical_twilight_end = nautical_twilight_end;
    }

    public void setAstronomical_twilight_begin(String astronomical_twilight_begin) {
        this.astronomical_twilight_begin = astronomical_twilight_begin;
    }

    public void setAstronomical_twilight_end(String astronomical_twilight_end) {
        this.astronomical_twilight_end = astronomical_twilight_end;
    }
}
