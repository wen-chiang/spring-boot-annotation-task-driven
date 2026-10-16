package com.sample.enterprise.devops.daas.sampleApp;

import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.session.ProcedureSessionBase;
import com.sample.enterprise.devops.daas.proc.DaasDataHolder;
import org.springframework.stereotype.Component;

/**
 * Shared request/response/session container used by the sample service.
 *
 * <p>This object is intentionally small and test-focused: it carries the in-flight deployment
 * payload and lets the routine classes mutate the response while the service orchestrates the
 * overall execution path. The point is to show how routine metadata can drive behaviour without
 * embedding the workflow in a single large method.</p>
 */
@Component
public class SampleDataHolder extends DaasDataHolder<DeploymentMessageBase, GeneralResponseMessage, ProcedureSessionBase> {
}
