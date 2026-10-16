# Spring Boot Annotation Task Driven

This repository is a Spring Boot base library focused on proving the `DaasServiceCore` pattern using annotation-driven routines.

## Project structure

- `devops-deployment-base` - core library with annotations, utilities, and service orchestration code

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

To see the routine execution logs in the console while the proof tests run, use:

```bash
./gradlew.bat :devops-deployment-base:test --info
```

On macOS/Linux:

```bash
./gradlew :devops-deployment-base:test --info
```

This prints the sample route entry logs such as:

```text
[sampleApp.routine] Entered SampleFirstRoutine
[sampleApp.routine] Entered SampleV2Routine
[sampleApp.routine] Entered SampleEvenFailRoutine
```

## Notes

- This project intentionally keeps the runtime/service layer out of the build to focus on the core orchestration idea.
- The base module includes Spring Boot test coverage for the annotation and service-flow proof points.
- The annotation showcase includes a controller-triggered sample flow in `sampleApp/controller` that proves the routine design through HTTP request execution.
- The project uses Spring Boot 3.2.5 and Java 17.
