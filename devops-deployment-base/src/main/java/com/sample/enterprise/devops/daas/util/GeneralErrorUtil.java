package com.sample.enterprise.devops.daas.util;

import com.sample.enterprise.devops.daas.message.model.GeneralResponseErrorDetail;
import org.apache.commons.text.StringSubstitutor;

import java.util.*;

public class GeneralErrorUtil {

    public static final String SUFFIX_MESSAGE = ".message";
    public static final String SUFFIX_ADMIN_RESPONSE = ".adminResponse";
    public static final String SUFFIX_USER_RESPONSE = ".userResponse";

    private Map<String, String> valuesMap = new HashMap<>();
    private Properties prop;
    private String userResponseTag = null;

    public void setUserResponseTag(String UserResponseTag) {
        userResponseTag = UserResponseTag;
    }

    /**
     * Instantiates a new General error util.
     *
     * @param prop the prop
     */
    public GeneralErrorUtil(Properties prop) {
        this.prop = prop;
    }

    /**
     * Add.
     *
     * @param key   the key
     * @param value the value
     */
    public void add(String key, String value) {
        valuesMap.put(key, value);
    }

    /**
     * Put.
     *
     * @param map the map
     */
    public void put(Map<String, String> map) {
        valuesMap.putAll(map);
    }

    /**
     * Store error detail.
     *
     * @param errorDetail the error detail
     * @param codeKey     the code key
     */
    public void storeErrorDetail(GeneralResponseErrorDetail errorDetail, String codeKey) {

        errorDetail.setErrorCode(codeKey);
        errorDetail.setErrorMessage(messageSubstite(getErrorProperty(codeKey, prop, SUFFIX_MESSAGE), valuesMap));
        errorDetail.getAdminResponse()
                .addAll(responseSubstite(getErrorProperty(codeKey, prop, SUFFIX_ADMIN_RESPONSE), valuesMap));
        errorDetail.getUserResponse()
                .addAll(responseSubstite(getErrorProperty(codeKey, prop, SUFFIX_USER_RESPONSE), valuesMap));

    }

    public void storeSessionErrorDetail(GeneralResponseErrorDetail errorDetail, String codeKey) {

        errorDetail.setErrorCode(codeKey);
        errorDetail.setErrorMessage(messageSubstite(getErrorProperty(codeKey, prop, SUFFIX_MESSAGE), valuesMap));
        errorDetail.getAdminResponse()
                .addAll(responseSubstite(getErrorProperty(codeKey, prop, SUFFIX_ADMIN_RESPONSE), valuesMap));
        errorDetail.getUserResponse()
                .addAll(responseSubstite(getErrorProperty(codeKey, prop, SUFFIX_USER_RESPONSE), valuesMap));


    }

    private String getErrorProperty(String errorKey, Properties prop, String suffix) {
        return prop.getProperty(errorKey + suffix, "");
    }

    private String messageSubstite(String message, Map<String, String> valuesMap) {

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(message);
    }

    private List<String> responseSubstite(String message, Map<String, String> valuesMap) {

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String resolvedString = sub.replace(message);
        String[] msgs = resolvedString.split("\n");
        if(userResponseTag != null) {
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = userResponseTag + msgs[i];
            }
        }
        return Arrays.asList(msgs);
    }
}
