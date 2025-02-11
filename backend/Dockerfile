# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# 1️⃣ Install dependencies first (cached separately)
COPY pom.xml .
RUN mvn dependency:go-offline  

# 2️⃣ Copy source code separately (avoiding unnecessary recompilation)
COPY src ./src

# 3️⃣ Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# 4️⃣ Copy only the built JAR from the first stage
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# 5️⃣ Run the application
CMD ["java", "-jar", "app.jar"]

