package com.sample.enterprise.devops.daas.config.endpoint;

import com.sample.enterprise.devops.daas.util.DaasPropertyInfo;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "secret")
public class DaasSecrets implements Loggable {

    private String responseStr = "Refresh Daas.Property Success";

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.boot.actuate.endpoint.Endpoint#invoke()
     */
    @ReadOperation
    public String invoke() {

        try {
            DaasPropertyInfo.instance().refresh();
        } catch (Exception e) {
            logger.error("[DaasSecrets]", e);
            responseStr = "[Error]:" + e.getMessage();
        }

        return responseStr;
    }

}
