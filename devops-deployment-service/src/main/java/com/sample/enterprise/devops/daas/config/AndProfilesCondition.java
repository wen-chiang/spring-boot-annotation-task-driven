package com.sample.enterprise.devops.daas.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class AndProfilesCondition implements Condition {

    public static final String VALUE = "value";
    public static final String DEFAULT_PROFILE = "default";

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        if (context.getEnvironment() == null) {
            return true;
        }
        MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Profile.class.getName());
        if (attrs == null) {
            return true;
        }
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();

        // add default
        activeProfiles = StringUtils.addStringToArray(activeProfiles, "default");

        String[] definedProfiles = (String[]) attrs.getFirst(VALUE);
        Set<String> allowedProfiles = new HashSet<>(1);
        Set<String> restrictedProfiles = new HashSet<>(1);
        for (String nextDefinedProfile : definedProfiles) {
            if (!nextDefinedProfile.isEmpty() && nextDefinedProfile.charAt(0) == '!') {
                restrictedProfiles.add(nextDefinedProfile.substring(1));
                continue;
            }
            allowedProfiles.add(nextDefinedProfile);
        }
        int activeAllowedCount = 0;
        for (String nextActiveProfile : activeProfiles) {

            // quick exit when one of active profiles is restricted
            if (restrictedProfiles.contains(nextActiveProfile)) {
                if (!nextActiveProfile.equals("default")) {
                    return false;
                } else if (activeProfiles.length == 1) {
                    return false;
                }
            }
            // just go ahead when there is no allowed profiles (just need to check that there is no active restricted profiles)
            if (allowedProfiles.isEmpty()) {
                continue;
            }
            if (allowedProfiles.contains(nextActiveProfile)) {
                activeAllowedCount++;
            }
        }
        return activeAllowedCount == allowedProfiles.size();
    }

}