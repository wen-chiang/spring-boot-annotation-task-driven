# Annotation Routine Design

This is a Spring Boot project using a custom annotation-driven workflow design for service orchestration.

The project does not rely on a large imperative workflow method. Instead, it uses custom annotations to declare route behavior, environment scope, endpoint type, and failure semantics. The service then selects the matching route list from the request metadata and runs the annotated routines in order.

This keeps the flow engine stable while making each route easy to add, remove, or test independently. A new workflow step only needs a new routine class plus the proper annotations; it does not require rewriting the central orchestration logic. That is why this design is easy to attach to real workflow implementations and easy to validate in isolation.

## Core idea

The execution flow is intentionally simple:

1. A controller or caller creates a request.
2. A service method delegates to `DaasServiceCore.processEndpoint(...)`.
3. The service gathers the routine list for the target environment.
4. The framework filters routines by annotation metadata and version.
5. Each routine executes only when it matches the endpoint annotation and runtime conditions.

This keeps the workflow logic declarative instead of hard-coded in large `if/else` chains.

## Why this is easy to extend

To add a new workflow step, a developer usually only needs to:

- create a new routine class
- annotate it with the relevant deploy, promote, and endpoint annotations
- put it into the matching routine list for the environment
- let the framework do the selection and ordering

The route is then included automatically according to the metadata, instead of requiring a new custom branch in the service.

## Routine registration pattern

The service holds environment-specific routine lists, for example:

```java
@Autowired(required = false)
@PromoteDev
private List<DaasRoutine> manageSecretRoutines;
```

The framework inspects those fields and includes only the list whose promote annotation matches the current environment, such as `dev`, `test`, or `prod`.

Once the list is selected, the service checks each routine and includes it only if:

- the routine matches the endpoint annotation being processed
- the routine passes version filtering
- the routine is not skipped by the skip policy
- the routine should run under the current success/failure state

## Annotation categories

The project separates annotation responsibilities by purpose so the route metadata reads clearly and stays easy to reason about.

### 1) Deploy annotations
These declare which deployment targets or infrastructure contexts the routine is valid for.

Examples:

- `@OpenshiftAccessible`
- `@CommonAccessible`
- `@AnsibleAccessible`
- `@ChefAccessible`

These are the routing/availability markers. A routine is not considered valid unless it matches the deployment class/package context needed by the flow.

### 2) Promote annotations
These declare which environment(s) can invoke the routine.

Examples:

- `@PromoteCI`
- `@PromoteDev`
- `@PromoteTrial`
- `@PromoteTest`
- `@PromoteUat`
- `@PromoteQa`
- `@PromoteProd`

The service reads the current `promoteEnv` and only loads the routine list that belongs to that environment.

### 3) Endpoint annotations
These declare which operation type or workflow stage the routine belongs to.

Examples:

- `@Publish`
- `@Status`
- `@Deployment`
- `@Log`
- `@ManageSecret`
- `@CleanUp`
- `@Promote`

This is the key part of the design: when a service method is called with a certain endpoint annotation, only routines that carry that annotation run for that endpoint.

### 4) Failure-condition annotations
These control when a routine should be included during a failed or partially failed flow.

Examples:

- `@EvenFailed`
- `@OnlyFailed`

These are used by `DaasServiceCore.routineProcessRequired(...)` to decide whether the routine should continue to run even after an earlier failure, or only when the previous state already failed.

## Example route class

This is a representative route class from the sample application:

```java
@Component
@Order(100)
// accept Deploy annotations
@OpenshiftAccessible
@CommonAccessible
@AnsibleAccessible
@ChefAccessible

// accept Promote annotations
@PromoteCI
@PromoteDev
@PromoteTrial
@PromoteTest
@PromoteUat
@PromoteQa
@PromoteProd

// accept Endpoint annotations
@CleanUp
@Deployment
@Log
@ManageSecret
@Promote
@Status
public class SampleRoutine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        String moreInfo = dataHolder.getResponseMsg().getMoreInfo();
        dataHolder.getResponseMsg().setMoreInfo(moreInfo + " " + getClass().getSimpleName());
        GeneralErrorUtil util = new GeneralErrorUtil(new Properties());
        util.add("dummy", "dummyString");
        this.updateErrorResponse(dataHolder, "00", util);
    }
}
```

This makes the route intent clear in one glance:

- it is valid for the accessible deployment types
- it is allowed in the selected promotion environment
- it belongs to the endpoint workflow being executed
- it contributes to the process status and response data

## Failure-condition behavior

### `@EvenFailed`

If a routine is marked with `@EvenFailed`, it is allowed to run even when a previous routine has failed.

This is useful when a workflow wants to continue and still perform explicit follow-up processing in a failure scenario.

### `@OnlyFailed`

If a routine is marked with `@OnlyFailed`, it runs only when the execution has already failed.

This allows the framework to include recovery, cleanup, or diagnostic routines only in failure branches without a custom service branch.

## Flow summary

In simple terms:

- list selected by environment
- routine filtered by endpoint annotation
- routine filtered by API/version compatibility
- routine included/excluded by success/failure markers
- routine executes in order
- response status and process log are updated as each routine runs

This means the implementation stays composable: a new route can be added as a new routine class without rewriting the workflow controller or service logic.

## Example from this repo

The sample application contains classes in `sampleApp/routine` such as:

- `SampleFirstRoutine`
- `SampleRoutine`
- `SampleEvenFailRoutine`
- `SampleOnlyFailRoutine`
- `SampleV2Routine`

These classes show how a simple set of annotations can produce different execution behavior:

- success path
- failure-aware path
- version-aware path
- ordered execution

## Conclusion

This design is suitable for workflow-style orchestration where each business step is represented as a small annotated routine. The core orchestration engine stays stable, while new behavior is added by creating small, isolated routine classes instead of large service methods.
