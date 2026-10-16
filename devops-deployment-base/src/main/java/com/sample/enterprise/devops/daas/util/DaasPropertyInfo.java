package com.sample.enterprise.devops.daas.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaasPropertyInfo implements Loggable {

    private static DaasPropertyInfo instance = null;
    private final Properties prop = new Properties();

    private DaasPropertyInfo() {
        try {
            refresh();
        } catch (Exception exp) {
            logger().error("DaasPropertyInfo", exp);
        }
    }

    /**
     * Refresh.
     *
     * @throws Exception the exception
     */
    public void refresh() throws Exception {
        String dir = System.getProperty("user.dir");
        InputStream in = new FileInputStream(dir + "/../secrets/daas.properties");
        prop.load(in);
        in.close();
    }

    public static DaasPropertyInfo instance() {
        if (instance == null) {
            instance = new DaasPropertyInfo();
        }
        return instance;
    }

    public Properties getProp() {
        return prop;
    }

}