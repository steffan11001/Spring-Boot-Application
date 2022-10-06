# Spring-Boot-Application

## Description

The spring boot application has a microservice called nightTimeTemperature which call an external API. The microservice calculate the screen temperature based on 
latitude and longitude and return an object with temperature value.

## How to run it
Application has multiple profiles:
    * dev
    * prod
    * local\
To set a profil use *--env-file* argument and **.env** files like forward command. 
```sh
docker compose --env-file .\.env.dev up
```