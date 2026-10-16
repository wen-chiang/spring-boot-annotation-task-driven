package com.sample.enterprise.devops.daas.sampleApp.controller;

import com.sample.enterprise.devops.daas.sampleApp.SampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SampleApplication.class)
@AutoConfigureMockMvc
public class SampleRoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void publishEndpointTriggersAnnotatedRoutines() throws Exception {
        mockMvc.perform(post("/sample/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"header\":{\"deployType\":\"openshift\",\"apiVersion\":\"v2\",\"promoteEnv\":\"dev\",\"correlationId\":\"sample-controller-test\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.moreInfo").value("SampleFirstRoutine SampleV2Routine SampleEvenFailRoutine"));
    }
}
