### STAGE 1: Build ###
FROM gradle:6.5.0-jdk8 as build

COPY . /home/gradle/project/
WORKDIR /home/gradle/project/

USER root
RUN chown -R gradle /home/gradle/project
USER gradle

RUN gradle clean build

### STAGE 2: Production Environment ###
FROM openjdk:8u262-jdk

WORKDIR /usr/app

COPY --from=build /home/gradle/project/build/libs .

EXPOSE 80
EXPOSE 443

ENV SPRING_CONFIG_LOCATION=

CMD ["java", "-jar", "/usr/app/truyencv-collector-0.0.1-SNAPSHOT.jar"]
