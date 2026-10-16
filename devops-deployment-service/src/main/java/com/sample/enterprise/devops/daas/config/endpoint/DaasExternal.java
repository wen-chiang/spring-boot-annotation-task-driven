package com.sample.enterprise.devops.daas.config.endpoint;

import com.sample.enterprise.devops.daas.util.Loggable;
import com.sample.enterprise.devops.openshift.utils.LogLevelPropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;


@Component
@Endpoint(id = "external")
public class DaasExternal implements Loggable {

    @Autowired(required = false)
    @Qualifier("routineSkipProp")
    private Properties routineSkipProp;

    public Properties getRoutineSkipProp() {
        return (routineSkipProp == null) ? new Properties() : routineSkipProp;
    }

    private String responseStr = "Refresh Daas.Property External Success";

    @ReadOperation
    public String invoke() {
        String filePath = System.getProperty("user.dir") + "/dynamic.properties";
        Properties props = new Properties();
        logger().info("Loading the External properties file: " + filePath);
        String routineSkip = "routine skip properties:";
        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
            Set<String> keys = props.stringPropertyNames();
            for (String key : keys) {
                if (key.startsWith("policy.routine.skip.")) {
                    getRoutineSkipProp().setProperty(key, props.getProperty(key));
                    routineSkip = routineSkip + "\n\t" + key + "=" + props.getProperty(key);
                }
                if (key.startsWith("openshift.oc.log.level")) {
                    LogLevelPropertiesUtil.getInstance().setLogLevel(props.getProperty(key));
                    routineSkip = routineSkip + "\n\t" + key + "=" + props.getProperty(key);
                }
            }
            responseStr = "[Success]:upload properties amount:" + props.size();
            responseStr = responseStr + "\n" + routineSkip;
        } catch (Exception io) {
            responseStr = "[Error]:" + io.getMessage();
        }
        return responseStr;
    }

}
