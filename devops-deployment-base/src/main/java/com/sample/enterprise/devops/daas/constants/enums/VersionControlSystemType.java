package com.sample.enterprise.devops.daas.constants.enums;

public enum VersionControlSystemType {

    GIT("GIT"), SVN("SVN");

    private String name;

    VersionControlSystemType(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Check by name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean checkByName(String name) {
        for (VersionControlSystemType b : VersionControlSystemType.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
