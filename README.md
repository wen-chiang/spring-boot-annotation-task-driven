# Spring Boot Annotation Task Driven

This repository contains a multi-module Spring Boot project for a demo REST API service.

## Project structure

- `devops-deployment-base` - shared library module with utilities, annotations, and core services
- `devops-deployment-service` - Spring Boot REST API application

## Requirements

- Java 17+
- Gradle wrapper included in the repo
- Internet access to download Maven dependencies

## Build

From the repository root:

```bash
./gradlew.bat clean build
```

On macOS/Linux:

```bash
./gradlew clean build
```

## Run the service

From the repository root:

```bash
./gradlew.bat :devops-deployment-service:bootRun
```

On macOS/Linux:

```bash
./gradlew :devops-deployment-service:bootRun
```

The app listens on:

- http://localhost:3320

Swagger UI:

- http://localhost:3320/swagger-ui/index.html

OpenAPI JSON:

- http://localhost:3320/v3/api-docs

## Notes

- This project is configured as a demo REST API server and does not require secret authentication.
- It uses Spring Boot 3.2.5 and Java 17.
- The application uses SpringDoc OpenAPI for Swagger UI.

## Troubleshooting

If Gradle is blocked by a cached old dependency state, run:

```bash
./gradlew.bat --refresh-dependencies
```

If you see missing property file warnings for `../secrets/daas.properties`, they are non-fatal for the demo app startup and do not block the Swagger UI.
