package com.sample.enterprise.devops.daas.config;

import com.sample.enterprise.devops.daas.annotation.deploy.AnsibleAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.ChefAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.CommonAccessible;
import com.sample.enterprise.devops.daas.annotation.deploy.OpenshiftAccessible;
import com.sample.enterprise.devops.daas.constants.enums.ProcPropertyType;
import com.sample.enterprise.devops.daas.util.DaasPropertyInfo;
import com.sample.enterprise.devops.daas.util.Loggable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.SystemPropertyUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;


@Configuration
public class PropertiesLoaderConfigurer extends PropertySourcesPlaceholderConfigurer implements Loggable {

    private static final String ENVIRONMENT_PROPERTIES = "environmentProperties";

    private MutablePropertySources propertySources;
    private Properties commonInfoProp = new Properties();
    private Properties openshiftInfoProp = new Properties();
    private Properties ansibleInfoProp = new Properties();
    private Properties chefInfoProp = new Properties();
    private Properties routineSkipProp = new Properties();


    @Bean(name = "commonInfoProp")
    @CommonAccessible(ProcPropertyType.INFO)
    public Properties getCommonInfoProp() {
        return commonInfoProp;
    }

    @Bean(name = "openshiftInfoProp")
    @OpenshiftAccessible(ProcPropertyType.INFO)
    public Properties getOpenshiftInfoProp() {
        return openshiftInfoProp;
    }

    @Bean(name = "ansibleInfoProp")
    @AnsibleAccessible(ProcPropertyType.INFO)
    public Properties getAnsibleInfoProp() {
        return ansibleInfoProp;
    }

    @Bean(name = "chefInfoProp")
    @ChefAccessible(ProcPropertyType.INFO)
    public Properties getChefInfoProp() {
        return chefInfoProp;
    }

    @Bean(name = "commonInfoPropTemp")
    public Properties getCommonInfoPropTemp() {
        return commonInfoProp;
    }

    @Bean(name = "openshiftInfoPropTemp")
    public Properties getOpenshiftInfoPropTemp() {
        return openshiftInfoProp;
    }

    @Bean(name = "ansibleInfoPropTemp")
    public Properties getAnsibleInfoPropTemp() {
        return ansibleInfoProp;
    }

    @Bean(name = "chefInfoPropTemp")
    public Properties getChefInfoPropTemp() {
        return chefInfoProp;
    }

    @Bean(name = "routineSkipProp")
    public Properties getRoutineSkipProp() {
        return routineSkipProp;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.support.PropertySourcesPlaceholderConfigurer#
     * postProcessBeanFactory(org.springframework.beans.factory.config.
     * ConfigurableListableBeanFactory)
     */
    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

        super.postProcessBeanFactory(beanFactory);

        Object sEnvironment = super.getAppliedPropertySources().get(ENVIRONMENT_PROPERTIES).getSource();

        MutablePropertySources mPropertySources = null;
        if (sEnvironment instanceof org.springframework.web.context.support.StandardServletEnvironment) {
            mPropertySources = ((org.springframework.web.context.support.StandardServletEnvironment) sEnvironment)
                    .getPropertySources();
        } else if (sEnvironment instanceof org.springframework.core.env.StandardEnvironment) {
            mPropertySources = ((org.springframework.core.env.StandardEnvironment) sEnvironment).getPropertySources();
        }

        setPropertySources(mPropertySources);

        Map<String, String> propertyMap = new HashMap<>();
        mPropertySources.forEach(propertySource -> {
            if (propertySource.getSource() instanceof Map) {
                logger().info("\t#######[" + propertySource.getName() + "]#######");
                @SuppressWarnings("unchecked") final Map<String, String> properties = mapValueAsString(
                        (Map<String, Object>) propertySource.getSource());
                properties.forEach(propertyMap::putIfAbsent);
            }
        });

        propertyMap.forEach((key, value) -> {
            if (key.startsWith("common.routine.")) {
                String propKey = key.replace("common.routine.", "");
                if (commonInfoProp != null) {
                    if (value.startsWith("secret.")) {
                        //start add secret key from daas.properties
                        DaasPropertyInfo propertyInfo = DaasPropertyInfo.instance();
                        String daasValue = propertyInfo.getProp().getProperty(value.replace("secret.", ""));
                        if (daasValue == null) {
                            logger().warn("no key:" + propKey + " in daas.properties");
                        } else {
                            commonInfoProp.setProperty(propKey, daasValue);
                        }
                    } else {
                        commonInfoProp.setProperty(propKey, SystemPropertyUtils.resolvePlaceholders(value.toString().trim()));
                    }
                    logger().info("\t -[CommonPropertyInfo.(" + propKey + ")]-->" + key + ":" + commonInfoProp.get(propKey));
                }

            } else if (key.startsWith("procedure.ansible.")) {
                String propKey = key.replace("procedure.ansible.", "");
                if (ansibleInfoProp != null) {
                    ansibleInfoProp.setProperty(propKey, value);
                    logger().info("\t -[AnsiblePropertyInfo.(" + propKey + ")]-->" + key + ":" + value);
                }
                logger().info("\t -[AnsiblePropertyInfo.(" + propKey + ")]-->" + key + ":" + value);
            } else if (key.startsWith("procedure.chef.")) {
                String propKey = key.replace("procedure.chef.", "");
                if (chefInfoProp != null) {
                    chefInfoProp.setProperty(propKey, value);
                    logger().info("\t -[ChefPropertyInfo.(" + propKey + ")]-->" + key + ":" + value);
                }
                logger().info("\t -[ChefPropertyInfo.(" + propKey + ")]-->" + key + ":" + value);
            } else if (key.startsWith("procedure.openshift.")) {
                String propKey = key.replace("procedure.openshift.", "");
                if (openshiftInfoProp != null) {
                    openshiftInfoProp.setProperty(propKey, value);
                    logger().info("\t -[OpenshiftPropertyInfo.(" + propKey + ")]-->" + key + ":" + value);
                }
            } else if (key.startsWith("daas.auth.")) {
                Properties prop = System.getProperties();
                DaasPropertyInfo propertyInfo = DaasPropertyInfo.instance();
                // add all the daas.ssl into system property
                if (value.startsWith("secret.")) {
                    //start add secret key from daas.properties
                    String daasValue = propertyInfo.getProp().getProperty(value.replace("secret.", ""));
                    if (daasValue == null) {
                        logger().warn("no key:" + key + " in daas.properties");
                    } else {
                        prop.setProperty(key, daasValue);
                    }
                } else {
                    prop.setProperty(key, SystemPropertyUtils.resolvePlaceholders(value.toString().trim()));
                }
            } else if (key.startsWith("policy.routine.")) {
                routineSkipProp.setProperty(key, SystemPropertyUtils.resolvePlaceholders(value.toString().trim()));
                logger().info("\t -[PolicyRoutine]-->" + key + ":" + value);
            }
        });

    }

    /**
     * Map value as string.
     *
     * @param map the map
     * @return the map
     */
    private Map<String, String> mapValueAsString(final Map<String, Object> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> toString(entry.getValue())));
    }

    private String toString(final Object object) {
        return Optional.ofNullable(object).map(Object::toString).orElse(null);
    }
}
