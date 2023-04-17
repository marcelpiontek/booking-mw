# build stage
FROM maven:3-amazoncorretto-19 as build-stage
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

# production stage
FROM azul/zulu-openjdk-alpine:19-jre
COPY --from=build-stage target/booking-mw-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]