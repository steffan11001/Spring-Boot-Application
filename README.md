# Spring-Boot-Application

## Description

A spring boot application which calculate the screen temperature         return a JSON with an object 

## How to run it

### Application has multiple profiles

* dev;
* prod;
* local.
To set profil use *--env-file* argument and **.env** files 
```sh
docker compose --env-file .\.env.dev up
```