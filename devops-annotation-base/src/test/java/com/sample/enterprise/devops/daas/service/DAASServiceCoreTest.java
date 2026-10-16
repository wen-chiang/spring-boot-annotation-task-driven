package com.sample.enterprise.devops.daas.service;

import com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAccessible;
import com.sample.enterprise.devops.daas.annotation.endpoint.CleanUp;
import com.sample.enterprise.devops.daas.annotation.endpoint.Publish;
import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.sampleApp.SampleApplication;
import com.sample.enterprise.devops.daas.sampleApp.SampleDataHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SampleApplication.class)

public class DAASServiceCoreTest {

    @Autowired(required = false)
    @OpenshiftAccessible
    private DaasServiceCore service;


    @Autowired(required = false)
    private SampleDataHolder dataHolder;


    @Test
    public void verifyServiceRoutineExceptionSuccess() {
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            DeploymentMessageBase message = new DeploymentMessageBase();
            message.getHeader().setPromoteEnv("dev");
            dataHolder.initialRequest(message);
            GeneralResponseMessage responseMsg = service.processEndpoint(CleanUp.class, request, response, message, "CLEANUP");
            assertNotNull(responseMsg);
            assertEquals(responseMsg.getMoreInfo(), "SampleFirstRoutine SampleRoutine SampleEvenFailRoutine SampleOnlyFailRoutine");
        } catch (Exception exp) {
            fail();
        }
    }

    @Test
    public void verifyServiceRoutineSuccess() {
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            DeploymentMessageBase message = new DeploymentMessageBase();
            message.getHeader().setPromoteEnv("dev");
            dataHolder.initialRequest(message);
            GeneralResponseMessage responseMsg = service.processEndpoint(Publish.class, request, response, message, "Publish");
            assertNotNull(responseMsg);
            assertEquals(responseMsg.getMoreInfo(), "SampleFirstRoutine SampleV2Routine SampleEvenFailRoutine");


        } catch (Exception exp) {
            fail();
        }
    }

    @Test
    public void verifyServiceRoutineV2Success() {
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            DeploymentMessageBase message = new DeploymentMessageBase();
            message.getHeader().setApiVersion("v2");
            message.getHeader().setPromoteEnv("dev");
            dataHolder.initialRequest(message);
            GeneralResponseMessage responseMsg = service.processEndpoint(Publish.class, request, response, message, "Publish");
            assertNotNull(responseMsg);
            assertEquals(responseMsg.getMoreInfo(), "SampleFirstRoutine SampleV2Routine SampleEvenFailRoutine");
        } catch (Exception exp) {
            fail();
        }
    }

    @Test
    public void verifyServiceRoutineV1Success() {
        try {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            DeploymentMessageBase message = new DeploymentMessageBase();
            message.getHeader().setApiVersion("v5");
            message.getHeader().setPromoteEnv("dev");
            dataHolder.initialRequest(message);
            GeneralResponseMessage responseMsg = service.processEndpoint(Publish.class, request, response, message, "Publish");
            assertNotNull(responseMsg);
            assertEquals(responseMsg.getMoreInfo(), "SampleFirstRoutine SampleEvenFailRoutine");
        } catch (Exception exp) {
            fail();
        }
    }


}
