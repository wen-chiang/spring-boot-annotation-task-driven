package com.sample.enterprise.devops.daas.sampleApp.routine;

// --- deploy annotations: routing triggers and deployment capability markers ---
import com.sample.enterprise.devops.daas.annotation.deploy.*;
// --- endpoint annotations: the action categories this routine participates in ---
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
// --- promote annotations: environment promotion scope ---
import com.sample.enterprise.devops.daas.annotation.promote.*;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.proc.DaasRoutineBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * First routine in the sample flow.
 *
 * <p>This class shows the simplest successful routine: it is discovered by the common deployment
 * annotations and marks the request as submitted. It demonstrates that a routine can contribute to
 * the response state without being hard-wired to a large controller or service method.</p>
 */
@Component
@Order(10)
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
public class SampleFirstRoutine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        // Show the route execution clearly in the SLF4J log output.
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        // This routine is intentionally tiny: it proves that the framework can execute a routine
        // purely based on its annotations and update the shared session/response state.
        dataHolder.getSession().getProperties().setProperty(DeploymentConstants.LOGIC_STATUS, DeploymentConstants.SUBMITTED_STATUS_KEY);
        dataHolder.getResponseMsg().setMoreInfo(getClass().getSimpleName());
    }
}
