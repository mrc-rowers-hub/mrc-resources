FROM maven:3.9.8-eclipse-temurin-22 AS build

WORKDIR /home/maven/src

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre

EXPOSE 8083

COPY --from=build /home/maven/src/target/mrc-resources.jar /app/mrc-resources.jar

CMD ["java", "-jar", "/app/mrc-resources.jar"]
