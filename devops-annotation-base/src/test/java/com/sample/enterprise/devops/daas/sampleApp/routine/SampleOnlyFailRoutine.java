package com.sample.enterprise.devops.daas.sampleApp.routine;

// --- deploy annotations: routing triggers and deployment capability markers ---
import com.sample.enterprise.devops.daas.annotation.deploy.*;
// --- endpoint annotations: the action categories this routine participates in ---
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
// --- promote annotations: environment promotion scope ---
import com.sample.enterprise.devops.daas.annotation.promote.*;
// --- routine-condition annotations: when this routine should run in failure flows ---
import com.sample.enterprise.devops.daas.annotation.routine.OnlyFailed;
import com.sample.enterprise.devops.daas.proc.DaasRoutineBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Failure-only routine used to show selective execution semantics.
 *
 * <p>The {@link com.sample.enterprise.devops.daas.annotation.routine.OnlyFailed} marker is a
 * good demonstration of the framework's ability to filter routines based on prior error state.
 * This makes the failure path visible without requiring special-case branching in the service.
 */
@Component
@Order(220)
// -----------------------------------------------------------------------------
// Deploy annotations: declares where this routine is valid and which deployment
// flows it belongs to.
// -----------------------------------------------------------------------------
@OpenshiftAccessible
@CommonAccessible
@AnsibleAccessible
@ChefAccessible
// -----------------------------------------------------------------------------
// Promote annotations: shows which environments can invoke this routine.
// -----------------------------------------------------------------------------
@PromoteCI
@PromoteDev
@PromoteTrial
@PromoteTest
@PromoteUat
@PromoteQa
@PromoteProd
// -----------------------------------------------------------------------------
// Endpoint annotations: the operational actions this routine participates in.
// -----------------------------------------------------------------------------
@CleanUp
@Deployment
@Log
@ManageSecret
@Promote
@Publish
@Status
// -----------------------------------------------------------------------------
// Routine-condition annotations: special failure semantics for this routine.
// -----------------------------------------------------------------------------
@OnlyFailed
public class SampleOnlyFailRoutine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        // Show the route execution clearly in the SLF4J log output.
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        // This routine is intended to run only in the failure branch, demonstrating the annotation
        // based filtering model rather than service-level IF statements.
        String moreInfo = dataHolder.getResponseMsg().getMoreInfo();
        dataHolder.getResponseMsg().setMoreInfo(moreInfo + " " + getClass().getSimpleName());
    }
}
