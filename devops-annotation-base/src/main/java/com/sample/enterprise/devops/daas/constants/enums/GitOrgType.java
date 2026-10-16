package com.sample.enterprise.devops.daas.constants.enums;

public enum GitOrgType {

    DC_TEST("DC-test");

    private String name;

    GitOrgType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean checkByName(String name) {

        boolean ans = false;
        for (GitOrgType b : GitOrgType.values()) {
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
    public GitOrgType getByName(String name) {

        GitOrgType ans = null;
        for (GitOrgType b : GitOrgType.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                ans = b;
                break;
            }
        }
        return ans;
    }

    }
