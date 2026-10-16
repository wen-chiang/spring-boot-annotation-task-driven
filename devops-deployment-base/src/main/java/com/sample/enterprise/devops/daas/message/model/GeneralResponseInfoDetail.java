package com.sample.enterprise.devops.daas.message.model;

import java.util.ArrayList;
import java.util.List;

public class GeneralResponseInfoDetail {

    private String profileBaseRouteUri;
    private String branchBaseRouteUri;
    private String chooseRouteUri;
    private String infoSecret;
    private List<ClientSecretDetail> clientSecret = new ArrayList<>();
    private String exportContent;

    public String getProfileBaseRouteUri() {
        return profileBaseRouteUri;
    }

    public void setProfileBaseRouteUri(String profileBaseRouteUri) {
        this.profileBaseRouteUri = profileBaseRouteUri;
    }

    public String getBranchBaseRouteUri() {
        return branchBaseRouteUri;
    }

    public void setBranchBaseRouteUri(String branchBaseRouteUri) {
        this.branchBaseRouteUri = branchBaseRouteUri;
    }

    public String getChooseRouteUri() {
        return chooseRouteUri;
    }

    public void setChooseRouteUri(String chooseRouteUri) {
        this.chooseRouteUri = chooseRouteUri;
    }

    public String getInfoSecret() {
        return infoSecret;
    }

    public void setInfoSecret(String infoSecret) {
        this.infoSecret = infoSecret;
    }

    public List<ClientSecretDetail> getClientSecret() {
        return clientSecret;
    }

    public String getExportContent() {
        return exportContent;
    }

    public void setExportContent(String exportContent) {
        this.exportContent = exportContent;
    }
}
