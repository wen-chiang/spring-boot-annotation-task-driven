package com.sample.enterprise.devops.daas.sampleApp.routine;

// --- deploy annotations: routing triggers and deployment capability markers ---
import com.sample.enterprise.devops.daas.annotation.deploy.*;
// --- endpoint annotations: the action categories this routine participates in ---
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
// --- promote annotations: environment promotion scope ---
import com.sample.enterprise.devops.daas.annotation.promote.*;
// --- routine-condition annotations: when this routine should run in failure flows ---
import com.sample.enterprise.devops.daas.annotation.routine.EvenFailed;
import com.sample.enterprise.devops.daas.proc.DaasRoutineBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Example of a routine that is intentionally marked as an even-failure condition.
 *
 * <p>This is a teaching example for the fail/continue semantics: a routine with the
 * {@link com.sample.enterprise.devops.daas.annotation.routine.EvenFailed} marker demonstrates
 * that a failure can still be part of the workflow without short-circuiting the entire process.
 */
@Component
@Order(210)
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
@EvenFailed
public class SampleEvenFailRoutine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        // Show the route execution clearly in the SLF4J log output.
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        // This routine intentionally contributes to the run while signalling a non-fatal failure mode.
        String moreInfo = dataHolder.getResponseMsg().getMoreInfo();
        dataHolder.getResponseMsg().setMoreInfo(moreInfo + " " + getClass().getSimpleName());
    }
}
