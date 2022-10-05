package com.example.microservices.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SunResponse {
    private SunResultResponse results;
    @JsonProperty("status")
    private String status;

    public SunResponse() {
    }

    public SunResponse(SunResultResponse sunResultResponse, String status) {
        this.results = sunResultResponse;
        this.status = status;
    }

    @JsonProperty("results")
    public SunResultResponse getSunResultResponse() {
        return results;
    }


    public void setSunResultResponse(SunResultResponse sunResultResponse) {
        this.results = sunResultResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
