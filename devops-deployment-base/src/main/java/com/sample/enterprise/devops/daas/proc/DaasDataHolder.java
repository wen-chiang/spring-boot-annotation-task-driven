package com.sample.enterprise.devops.daas.proc;

import com.sample.enterprise.devops.daas.message.GeneralResponseMessage;
import com.sample.enterprise.devops.daas.message.model.DeploymentMessageBase;
import com.sample.enterprise.devops.daas.message.session.ProcedureSessionBase;

public class DaasDataHolder<T extends DeploymentMessageBase, O extends GeneralResponseMessage, G extends ProcedureSessionBase> {

    private T message;
    private ProcedureSessionBase session;
    private GeneralResponseMessage response;

    /**
     * Initial request.
     *
     * @param msg the msg
     */
    public void initialRequest(T msg) {
        message = msg;
        session = new ProcedureSessionBase();
        response = new GeneralResponseMessage();
        response.setHeader(message.getHeader());
    }

    public T getRequestMsg() {
        return message;
    }

    public void setRequestMsg(T message) {
        this.message = message;
    }

    public ProcedureSessionBase getSession() {
        return session;
    }

    public void setSession(ProcedureSessionBase session) {
        this.session = session;
    }

    public GeneralResponseMessage getResponseMsg() {
        return response;
    }

    public void setResponseMsg(GeneralResponseMessage response) {
        this.response = response;
    }
}
