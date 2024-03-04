# Use a base image with JDK 8 installed (which includes JRE)
FROM gradle:8.6.0-jdk AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files to the container
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle

# Copy the source code to the container
COPY src ./src

# Build the application
RUN chmod +x ./gradlew

# Run the tests and generate Allure report
RUN gradle build || true
RUN gradle allureReport


CMD ["gradle", "test"]