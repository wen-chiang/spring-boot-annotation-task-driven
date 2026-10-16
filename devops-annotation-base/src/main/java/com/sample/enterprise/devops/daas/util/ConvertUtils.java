package com.sample.enterprise.devops.daas.util;

import com.sample.enterprise.devops.daas.message.*;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.model.GeneralRequestBase;

import java.util.UUID;

public class ConvertUtils {

    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final GeneralRequestBase request) {
        final OpenshiftDeploymentMessage wrapper = new OpenshiftDeploymentMessage();
        if (request != null) {

            wrapper.getHeader().setCorrelationId(UUID.randomUUID().toString());
            wrapper.getHeader().setDeployType("openshift");

            //carry incoming message in
            wrapper.getHeader().setPromoteEnv(request.getProfile());
            wrapper.getJenkinsInfo().setApplicationName(request.getApplicationName());
            wrapper.getJenkinsInfo().setApplicationShortName(request.getProjectName());
            wrapper.getJenkinsInfo().setZone(request.getZone());
            wrapper.getJenkinsInfo().setProjectName(request.getProjectName());
            wrapper.getJenkinsInfo().setCluster(request.getCluster());
            wrapper.setWrapObj(request);
            return wrapper;
        }
        return wrapper;
    }


    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final DeploymentMessageBase request) {
        final OpenshiftDeploymentMessage wrapper = new OpenshiftDeploymentMessage();
        if (request != null) {
            wrapper.setHeader(request.getHeader());
            wrapper.setJenkinsInfo(request.getJenkinsInfo());
            wrapper.setWrapObj(request);
            return wrapper;
        }
        return wrapper;
    }

    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final OpenshiftImportMessage request) {
        OpenshiftDeploymentMessage wrapper = toOpenShiftDeploymentMessage((DeploymentMessageBase) request);
        if (request != null) {
            wrapper.setProjectConfig(request.getDeploymentConfig());
        }
        return wrapper;
    }

    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final OpenshiftExportMessage request) {
        OpenshiftDeploymentMessage wrapper = toOpenShiftDeploymentMessage((DeploymentMessageBase) request);
        if (request != null) {

        }
        return wrapper;
    }

    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final OpenshiftRollbackMessage request) {
        OpenshiftDeploymentMessage wrapper = toOpenShiftDeploymentMessage((DeploymentMessageBase) request);
        if (request != null) {

        }
        return wrapper;
    }
    public static final OpenshiftDeploymentMessage toOpenShiftDeploymentMessage(final OpenshiftRebuildImageMessage request) {
        OpenshiftDeploymentMessage wrapper = new OpenshiftDeploymentMessage();
        if (request != null) {
            wrapper.setHeader(request.getHeader());
            wrapper.setJenkinsInfo(request.getJenkinsInfo());
            wrapper.setWrapObj(request);
            return wrapper;
        }
        return wrapper;
    }
}
