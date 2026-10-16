package com.sample.enterprise.devops.daas.routine;

import com.sample.enterprise.devops.daas.annotation.deploy.*;
import com.sample.enterprise.devops.daas.annotation.endpoint.*;
import com.sample.enterprise.devops.daas.annotation.promote.*;
import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleApplication;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import com.sample.enterprise.devops.daas.sampleApp.routine.SampleRoutine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SampleApplication.class)
public class SampleApplicationTest {

    @Autowired
    private SampleRoutine routine;

    @Autowired
    private SampleDataHolder dataHolder;


    @Test
    public void verifyRoutineEndPointSuccess() {
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, Log.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, Promote.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, Status.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, ManageSecret.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, Deployment.class));
    }

    @Test
    public void verifyRoutineAccessibleSuccess() {
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, AnsibleAccessible.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, ChefAccessible.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, OpenshiftAccessible.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, CommonAccessible.class));
    }

    @Test
    public void verifyRoutinePromoteSuccess() {
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, PromoteDev.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, PromoteCI.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, PromoteTrial.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, PromoteProd.class));
        assertNotNull(AnnotationUtils.findAnnotation(SampleRoutine.class, PromoteQa.class));

    }

    @Test
    public void verifyRoutineEndPointErrorResponseSuccess() {
        DeploymentMessageBase message = new DeploymentMessageBase();
        dataHolder.initialRequest(message);
        routine.process();
        assertEquals(DeploymentConstants.FAIL_STATUS_KEY, dataHolder.getResponseMsg().getStatus());
    }

}
