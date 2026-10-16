package com.sample.enterprise.devops.daas.constants.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PromoteEnvTypes {
    CI("ci"), DEV("dev"), QA("qa"), TEST("test"), TRIAL("trial"), UAT("uat"), PROD("prod");

    private String name;

    /**
     * Instantiates a new promote env enum.
     *
     * @param name the name
     */
    PromoteEnvTypes(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Check by name.
     *
     * @param name the name
     * @return true, if successful
     */
    public boolean checkByName(String name) {

        boolean ans = false;
        for (PromoteEnvTypes b : PromoteEnvTypes.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                ans = true;
                break;
            }
        }
        return ans;
    }

    /**
     * Gets the by name.
     *
     * @param name the name
     * @return the by name
     */
    public PromoteEnvTypes getByName(String name) {
        PromoteEnvTypes ans = null;
        for (PromoteEnvTypes b : PromoteEnvTypes.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                ans = b;
                break;
            }
        }
        return ans;
    }

    public static List<String> getPromoteEnvHierarchy(String name) {
        if (PROD.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName(), QA.getName(), TEST.getName(), TRIAL.getName(), UAT.getName(), PROD.getName());
        } else if (UAT.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName(), QA.getName(), TEST.getName(), TRIAL.getName(), UAT.getName());
        } else if (TRIAL.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName(), QA.getName(), TEST.getName(), TRIAL.getName());
        } else if (TEST.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName(), QA.getName(), TEST.getName());
        } else if (QA.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName(), QA.getName());
        } else if (DEV.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName(), DEV.getName());
        } else if (CI.getName().equalsIgnoreCase(name)) {
            return Arrays.asList(CI.getName());
        } else {
            return Collections.emptyList();
        }
    }
}
