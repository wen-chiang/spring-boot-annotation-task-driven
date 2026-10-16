package com.sample.enterprise.devops.daas.sampleApp.routine;

// --- deploy annotations: routing triggers and deployment capability markers ---
import com.sample.enterprise.devops.daas.annotation.deploy.*;
// --- endpoint annotations: the action categories this routine participates in ---
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
// --- promote annotations: environment promotion scope ---
import com.sample.enterprise.devops.daas.annotation.promote.*;
import com.sample.enterprise.devops.daas.proc.DaasRoutineBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.util.GeneralErrorUtil;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Main sample routine used to demonstrate response mutation and error response handling.
 *
 * <p>This routine is intentionally not a special-case implementation. It is a normal routine
 * discovered by the same annotation scan as the rest of the flow, and it shows that the framework
 * can append routine output and attach structured error metadata without a dedicated custom branch.
 */
@Component
@Order(100)
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
@Status
public class SampleRoutine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        // Show the route execution clearly in the SLF4J log output.
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        // The purpose of this routine is to illustrate that the orchestration engine can gather
        // several small routine contributions and merge them into the running response state.
        String moreInfo = dataHolder.getResponseMsg().getMoreInfo();
        dataHolder.getResponseMsg().setMoreInfo(moreInfo + " " + getClass().getSimpleName());
        GeneralErrorUtil util = new GeneralErrorUtil(new Properties());
        util.add("dummy", "dummyString");
        this.updateErrorResponse(dataHolder, "00", util);
    }
}
