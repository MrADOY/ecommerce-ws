#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

#
# Package stage
#
FROM openjdk:8-jre-alpine
COPY --from=build /home/app/target/ecommerce-thorntail.jar /usr/local/lib/ecommerce-thorntail.jar

ENV DOCKERIZE_VERSION v0.6.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz
EXPOSE 8080
ENTRYPOINT ["dockerize", "-wait", "tcp://db:3306", "-timeout", "60m","java", "-jar", "-Djava.net.preferIPv4Stack=true", "/usr/local/lib/ecommerce-thorntail.jar"]
