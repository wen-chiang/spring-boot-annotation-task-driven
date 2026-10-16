package com.sample.enterprise.devops.daas.sampleApp.routine;

// --- deploy annotations: routing triggers and deployment capability markers ---
import com.sample.enterprise.devops.daas.annotation.deploy.*;
// --- endpoint annotations: the action categories this routine participates in ---
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
// --- promote annotations: environment promotion scope ---
import com.sample.enterprise.devops.daas.annotation.promote.*;
import com.sample.enterprise.devops.daas.proc.DaasRoutineBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Version-aware routine used to demonstrate annotation-based version filtering.
 *
 * <p>This class is included in the sample suite to show that execution is not only driven by the
 * routine order and endpoint annotation, but also by version metadata such as
 * {@link com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAcceptVersion} and
 * {@link com.sample.enterprise.devops.daas.annotation.deploy.AnsibleAcceptVersion}. The routine is
 * valid for multiple versions, which makes it a clear example of the framework's filter model.</p>
 */
@Component
@Order(110)
// -----------------------------------------------------------------------------
// Deploy annotations: declares where this routine is valid and which deployment
// flows it belongs to.
// -----------------------------------------------------------------------------
@OpenshiftAccessible
@OpenshiftAcceptVersion(versions = {"v2", "v1", "v3"})
@CommonAccessible
@AnsibleAccessible
@AnsibleAcceptVersion(versions = {"v2", "v1", "v3"})
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
@Publish
public class SampleV2Routine extends DaasRoutineBase implements Loggable {

    @Autowired(required = false)
    private SampleDataHolder dataHolder;

    @Override
    public void process() {
        // Show the route execution clearly in the SLF4J log output.
        logger().info("[sampleApp.routine] Entered {}", getClass().getSimpleName());

        // This routine is intentionally version-compatible so the tests can prove that filtering is
        // annotation-driven rather than a custom service rule.
        String moreInfo = dataHolder.getResponseMsg().getMoreInfo();
        dataHolder.getResponseMsg().setMoreInfo(moreInfo + " " + getClass().getSimpleName());
    }
}
