FROM openjdk:8

WORKDIR /app

VOLUME /app/tmp

COPY ./mvnw .
COPY ./.mvn .mvn
COPY ./pom.xml .

RUN ./mvnw dependency:go-offline

COPY ./src ./src

EXPOSE 9100

RUN ./mvnw clean package
CMD ["java", "-jar", "./target/oauth-service-0.0.1-SNAPSHOT.jar"]