# Insurance Service Project (insurance-service)

## Introduction

The Insurance Service project is a demo project built using Spring Boot 3.1 and Java 17. It showcases the usage of
Micrometer, Web, and OpenTelemetry dependencies to implement distributed tracing in a microservice application. The
project contains two GET endpoints: `/policy` and `/claim`, both requiring a path parameter `id` representing the
customer ID. As this is a demo project, static maps are used for Policy and Claim data instead of fetching them from a
database. The `dev.knowledgecafe` package includes the `InsuranceController` class, defining the endpoints, and
the `ClaimService` and `PolicyService` classes, responsible for searching claims and policies based on the provided ID.
If no matching records are found, the services return null.

## Project Structure

The project follows the standard Spring Boot project structure:

- `src/main/java`: Contains the Java source code.
    - `dev.knowledgecafe`: Contains the main package.
        - `InsuranceServiceApplication.java`: The main entry point for the application.
        - `InsuranceController.java`: Defines the endpoints (`/policy` and `/claim`) and handles HTTP requests.
        - `ClaimService.java`: Implements claim-related logic and search functionality.
        - `PolicyService.java`: Implements policy-related logic and search functionality.
        - `OtlpConfig.java`: Configures the OpenTelemetry exporter to export traces and spans from endpoint URLs.

- `src/main/resources`: Contains the application properties and configuration files.
    - `application.properties`: Configures various properties for the application.

## Prerequisites

Before running the Insurance Service project, ensure the following software is installed on your system:

- Java Development Kit (JDK) 17 or higher
- Spring Boot 3.1
- Micrometer
- Web dependencies
- OpenTelemetry

## Running the Application

To run the Insurance Service application:

1. Clone the project from the repository to your local machine:

   ```
   git clone <repository-url>
   ```

2. Open the project in your preferred Java IDE.

3. Locate the `InsuranceServiceApplication.java` file in the `src/main/java/dev/knowledgecafe` package.

4. Run the `InsuranceServiceApplication` class as a Java application to start the Spring Boot application.

5. The application will start, and the endpoints `/policy` and `/claim` will be accessible on the specified port (
   default is 8080).

## Endpoints

The Insurance Service application exposes two endpoints:

1. `/policy/{id}`: Retrieves policy information based on the provided customer ID.

2. `/claim/{id}`: Retrieves claim details based on the provided customer ID.

Both endpoints require a valid customer ID as a path parameter. If the requested policy or claim is found, the
application will return the relevant data. Otherwise, it will return null.

## Distributed Tracing

The project demonstrates the implementation of distributed tracing using OpenTelemetry. The `OtlpConfig.java` class
defines a bean to export traces and spans from endpoint URLs, allowing you to monitor the trace flow across services.

## Conclusion

The Insurance Service project is a demo application illustrating the implementation of distributed tracing using
OpenTelemetry in Spring Boot 3.1 with Java 17. It serves as a valuable learning resource for developers interested in
understanding distributed tracing in microservice-based applications. If you have any questions or suggestions, feel
free to contribute or reach out to the project maintainers. Happy tracing!

# Swagger
See http://localhost:8090/swagger-ui/index.html