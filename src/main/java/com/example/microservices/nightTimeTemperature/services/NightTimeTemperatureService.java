package com.example.microservices.nightTimeTemperature.services;

import com.example.microservices.Temperature;
import com.example.microservices.response.SunResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import java.util.Date;

//In figura respectiva se vede ca daca ora locala este intre sunrise + twilight si
// sunset - twilight temeperatura culorii este 6000K, daca ora este intre sunset + twilight si
// sunrise - twiligh temperatura este 2700K. Raman cele doua sectiuni unde avem o variatie liniar
// crescatoare respectiv descrescatoare, acolo poti calcula temp in functie de temperaturile pe capetele
// pantelor (o ecuatie liniaira). Daca vrei putem sa facem un meet sa-ti explic mai mult.
@Service
//@ConfigurationProperties("night-time-temperature")
public class NightTimeTemperatureService {
    @Value("${night-time-temperature.uri}")
    private String uri;

    @Value("${night-time-temperature.env}")
    private String env;

    public Temperature callAPI(double lat, double  lng) throws JsonProcessingException {
//        uri= "http://localhost:8080/sunrise_sunset/api";
//            uri = "http://api.sunrise-sunset.org/json?lat="+Float.toString(lat)+"&lng="+Float.toString(lng);
        if(env.equals("qa"))
        {
            uri =uri+Double.toString(lat)+"&lng="+Double.toString(lng);
        }
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        SunResponse sunResponse = mapper.readValue(result, SunResponse.class);

        int tmp=calculateTemp(sunResponse);
        return new Temperature(tmp) ;

    }

    private int calculateTemp(SunResponse sunResponse)
    {
        LocalTime sunrise= LocalTime.parse(convertAM_PM_to_24H(sunResponse.getSunResultResponse().getSunrise()));
        LocalTime twilightBegin= LocalTime.parse(convertAM_PM_to_24H(sunResponse.getSunResultResponse().getCivil_twilight_begin()));
        LocalTime sunset= LocalTime.parse(convertAM_PM_to_24H(sunResponse.getSunResultResponse().getSunset()));
        LocalTime twilightEnd= LocalTime.parse(convertAM_PM_to_24H(sunResponse.getSunResultResponse().getCivil_twilight_end()));

        // LocalTime dayLength ...

        int twilightBeginHours = twilightBegin.getHour();
        int twilightBeginMinutes = twilightBegin.getMinute();
        int twilightBeginSeconds= twilightBegin.getSecond();
        int sunriseHours = sunrise.getHour();
        int sunriseMinutes = sunrise.getMinute();
        int sunriseSeconds=sunrise.getSecond();
        int sunsetHours = sunset.getHour();
        int sunsetMinutes = sunset.getMinute();
        int sunsetSeconds=sunset.getSecond();
        int twilightEndHours = twilightEnd.getHour();
        int twilightEndMinutes = twilightEnd.getMinute();
        int twilightEndSeconds= twilightEnd.getSecond();


        // hours to minutes
        twilightBeginMinutes=twilightBeginMinutes + 60 * twilightBeginHours;
        sunriseMinutes = sunriseMinutes + 60 * sunriseHours;
        sunsetMinutes =sunsetMinutes + 60* sunsetHours;
        twilightEndMinutes= twilightEndMinutes + 60 * twilightEndHours;
        // minutes to seconds
        twilightBeginSeconds=twilightBeginSeconds + 60 * twilightBeginMinutes;
        sunriseSeconds = sunriseSeconds + 60 * sunriseMinutes;
        sunsetSeconds = sunsetSeconds + 60* sunsetMinutes;
        twilightEndSeconds = twilightEndSeconds + 60* twilightEndMinutes;


        int sunriseDuration= (sunriseSeconds-twilightBeginSeconds)*2 + twilightBeginSeconds;


        LocalTime currentTime = LocalTime.now();
//        LocalTime currentTime = LocalTime.of(17,25,00);
        int currentTimeHours = currentTime.getHour();
        int currentTimeMinutes = currentTime.getMinute();
        int currentTimeSeconds = currentTime.getSecond();
        currentTimeMinutes = currentTimeMinutes+60*currentTimeHours;
        currentTimeSeconds = currentTimeSeconds + 60* currentTimeMinutes;
        if(currentTime.compareTo(twilightBegin) < 0 || currentTime.compareTo(twilightEnd) > 0)
        {
            return 2700;
        }
        if (currentTime.compareTo(LocalTime.ofSecondOfDay(sunriseDuration)) < 0)
        {
            return 3300*(currentTimeSeconds - twilightBeginSeconds)/(sunriseDuration - twilightBeginSeconds) + 2700;
        }
        if (currentTime.compareTo(LocalTime.ofSecondOfDay(sunsetSeconds * 2 - twilightEndSeconds)) < 0) {
            return 6000;
        }

        return -3300*(currentTimeSeconds - twilightEndSeconds+(twilightEndSeconds - sunsetSeconds)*2)/((twilightEndSeconds - sunsetSeconds)*2) + 6000;
    }
    private String convertAM_PM_to_24H(String input)
    {
        DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
        DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
        Date time= null;
        String output= null;
        try {
            time = df.parse(input);
            output = outputformat.format(time);

        }
        catch (ParseException pe)
        {
            pe.printStackTrace();
        }
        return output;
    }
}
