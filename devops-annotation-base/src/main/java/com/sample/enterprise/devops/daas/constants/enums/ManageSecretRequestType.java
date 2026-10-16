package com.sample.enterprise.devops.daas.constants.enums;

public enum ManageSecretRequestType {
    TLS("tls"), GENERIC("generic"), PRIVATEKEY("keystore");

    private String name;

    /**
     * Instantiates a new ManageSecretRequestType enum.
     *
     * @param name the name
     */
    ManageSecretRequestType(String name) {
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
        for (ManageSecretRequestType b : ManageSecretRequestType.values()) {
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
    public ManageSecretRequestType getByName(String name) {

        ManageSecretRequestType ans = null;
        for (ManageSecretRequestType b : ManageSecretRequestType.values()) {
            if (b.getName().equalsIgnoreCase(name)) {
                ans = b;
                break;
            }
        }
        return ans;
    }

}
