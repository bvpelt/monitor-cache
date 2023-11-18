# Customer Service Module

## Introduction

The Customer Service module is a part of the Distributed Trace project, which showcases the usage of Spring Boot 3.1 and
Java 17, along with Micrometer, Web, and OpenTelemetry dependencies. This module serves as a demo project to demonstrate
distributed tracing in microservice architecture. It provides a single GET endpoint, `/customer`, which requires a path
parameter `id` representing the customer ID. The project utilizes RestTemplate to call the endpoints `/policy`
and `/claim` from the Insurance Service and combines their responses into a `CustomerPojo` object returned from the
service. The `dev.knowledgecafe` package contains the `CustomerController`, `CustomerPojo`, and `OtlpConfiguration`
classes. The `application.yml` file in the resources package defines logging configuration to include traceID and SpanID
in each log message, as well as specifying the port and Spring Actuator configuration.

## Project Structure

The project follows the standard Spring Boot project structure:

- `src/main/java`: Contains the Java source code.
    - `dev.knowledgecafe`: Contains the main package.
        - `CustomerServiceApplication.java`: The main entry point for the application.
        - `CustomerController.java`: Defines the `/customer` endpoint and handles HTTP requests.
        - `CustomerPojo.java`: Represents the combined response from the Insurance Service endpoints.
        - `OtlpConfiguration.java`: Configures OpenTelemetry for distributed tracing.

- `src/main/resources`: Contains the application properties and configuration files.
    - `application.yml`: Configures various properties for the application, including logging and port configuration.

## Prerequisites

Before running the Customer Service module, ensure the following software is installed on your system:

- Java Development Kit (JDK) 17 or higher
- Spring Boot 3.1
- Micrometer
- Web dependencies
- OpenTelemetry

## Running the Application

To run the Customer Service module:

1. Clone the entire Distributed Trace project from the repository to your local machine:

   ```
   git clone <repository-url>
   ```

2. Open the project in your preferred Java IDE.

3. Locate the `CustomerServiceApplication.java` file in the `src/main/java/dev/knowledgecafe` package.

4. Run the `CustomerServiceApplication` class as a Java application to start the Spring Boot application.

5. The application will start, and the `/customer` endpoint will be accessible on the specified port (default is 8080).

## Endpoint

The Customer Service application exposes a single endpoint:

1. `/customer/{id}`: Retrieves customer information based on the provided customer ID. The endpoint internally calls the
   Insurance Service's `/policy` and `/claim` endpoints to gather relevant data and combines the responses into
   a `CustomerPojo` object returned by the service.

The endpoint requires a valid customer ID as a path parameter.

## Distributed Tracing

The project demonstrates the implementation of distributed tracing using OpenTelemetry. The `OtlpConfiguration.java`
class configures OpenTelemetry for distributed tracing in the application.

## Logging Configuration

The `application.yml` file in the resources package defines logging configuration, enabling traceID and SpanID to be
included in each log message. It also specifies the port configuration and Spring Actuator settings.

## Conclusion

The Customer Service module of the Distributed Trace project is a demo application illustrating distributed tracing
using OpenTelemetry in Spring Boot 3.1 with Java 17. It provides a single `/customer` endpoint that communicates with
the Insurance Service to gather policy and claim information. This project serves as an educational resource for
developers interested in implementing distributed tracing in microservice-based applications. If you have any questions
or suggestions, feel free to contribute or reach out to the project maintainers. Happy tracing!