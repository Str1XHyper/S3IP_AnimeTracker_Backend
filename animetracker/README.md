# Animetracker

This project is made for a Fontys Individual project;

This project is made using quarkus and Hibernate/Panache


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and deploying the application

The application can be packaged using:
```shell script
./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true -Dquarkus.container-image.name=animetracker -Dquarkus.container-image.group=str1xhyper -Dquarkus.container-image.tag=latest
```
It produces a Docker container and it automatically pushes to https://hub.docker.com/.

It is now runnable using Docker by using
```shell script
docker run -d -p 8002:3000 --name animetracker --rm str1xhyper/animetracker:latest
```

## Debugging environment

When the application is running you can navigate to /q/swagger-ui for the debug environment
