# -------- Build stage --------
FROM maven:3.9.8-eclipse-temurin-22 AS build

WORKDIR /home/maven/src

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# -------- Runtime stage --------
FROM eclipse-temurin:22-jre

WORKDIR /app

EXPOSE 8083

# Copy jar from the build stage
COPY --from=build /home/maven/src/target/mrc-resources.jar ./mrc-resources.jar

# Copy wait-for-it script into container
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

# Use wait-for-it to ensure MySQL is ready
CMD ["./wait-for-it.sh", "mrc-mysql-2:3306", "--", "java", "-jar", "mrc-resources.jar"]
