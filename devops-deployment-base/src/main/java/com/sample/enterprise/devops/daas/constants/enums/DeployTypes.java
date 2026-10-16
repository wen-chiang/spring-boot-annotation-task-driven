package com.sample.enterprise.devops.daas.constants.enums;

public enum DeployTypes {
    CHEF("chef"), BLUEMIX("bluemix"), OPENSHIFT("openshift"), ANSIBLE("ansible");

    private final String name;

    /**
     * Instantiates a new deploy types.
     *
     * @param name the name
     */
    DeployTypes(String name) {
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
        for (DeployTypes b : DeployTypes.values()) {
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
    public DeployTypes getByName(String name) {

        DeployTypes ans = null;
        for (DeployTypes b : DeployTypes.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                ans = b;
                break;
            }
        }
        return ans;
    }

}
