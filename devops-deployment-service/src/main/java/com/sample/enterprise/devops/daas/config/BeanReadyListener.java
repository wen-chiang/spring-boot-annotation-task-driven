package com.sample.enterprise.devops.daas.config;

import com.sample.enterprise.devops.daas.annotation.deploy.AnsibleAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.ChefAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.CommonAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAccessible;
import com.sample.enterprise.devops.daas.constants.enums.ProcPropertyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class BeanReadyListener {

    @Autowired(required = true)
    @CommonAccessible(ProcPropertyType.INFO)
    private Properties commonInfoProp;

    @Autowired(required = true)
    @OpenshiftAccessible(ProcPropertyType.INFO)
    private Properties openshiftInfoProp;

    @Autowired(required = true)
    @AnsibleAccessible(ProcPropertyType.INFO)
    private Properties ansibleInfoProp;

    @Autowired(required = true)
    @ChefAccessible(ProcPropertyType.INFO)
    private Properties chefInfoProp;

    @Autowired()
    @Qualifier("commonInfoPropTemp")
    private Properties commonInfoPropTemp;

    @Autowired()
    @Qualifier("openshiftInfoPropTemp")
    private Properties openshiftInfoPropTemp;

    @Autowired()
    @Qualifier("ansibleInfoPropTemp")
    private Properties ansibleInfoPropTemp;

    @Autowired()
    @Qualifier("chefInfoPropTemp")
    private Properties chefInfoPropTemp;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {

        ansibleInfoProp.putAll(ansibleInfoPropTemp);
        chefInfoProp.putAll(chefInfoPropTemp);
        commonInfoProp.putAll(commonInfoPropTemp);
        openshiftInfoProp.putAll(openshiftInfoPropTemp);

    }
}
