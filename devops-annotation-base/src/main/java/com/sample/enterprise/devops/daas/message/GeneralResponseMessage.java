package com.sample.enterprise.devops.daas.message;

import com.sample.enterprise.devops.daas.constants.DeploymentConstants;
import com.sample.enterprise.devops.daas.message.aware.HeaderInfoAware;
import com.sample.enterprise.devops.daas.message.model.GeneralResponseErrorDetail;
import com.sample.enterprise.devops.daas.message.model.GeneralResponseInfoDetail;
import com.sample.enterprise.devops.daas.message.model.GeneralResponseProcessDetail;
import com.sample.enterprise.devops.daas.message.model.MessageDispatchHeader;

public class GeneralResponseMessage implements HeaderInfoAware {
    private MessageDispatchHeader header;

    private String status = DeploymentConstants.PROGRESS_STATUS_KEY;
    private String moreInfo;
    private GeneralResponseInfoDetail infoDetail = new GeneralResponseInfoDetail();
    private GeneralResponseProcessDetail processDetail = new GeneralResponseProcessDetail();
    private GeneralResponseErrorDetail errorDetail = new GeneralResponseErrorDetail();

    /**
     * Gets the header.
     *
     * @return the header
     */
    public MessageDispatchHeader getHeader() {
        return header;
    }

    /**
     * Sets the header.
     *
     * @param header the new header
     */
    public void setHeader(MessageDispatchHeader header) {
        this.header = header;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the more info.
     *
     * @return the more info
     */
    public String getMoreInfo() {
        return moreInfo;
    }

    /**
     * Sets the more info.
     *
     * @param moreInfo the new more info
     */
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    /**
     * Gets the error detail.
     *
     * @return the error detail
     */
    public GeneralResponseErrorDetail getErrorDetail() {
        return errorDetail;
    }

    /**
     * Sets the error detail.
     *
     * @param errorDetail the new error detail
     */
    public void setErrorDetail(GeneralResponseErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    /**
     * Gets the process detail.
     *
     * @return the process detail
     */
    public GeneralResponseProcessDetail getProcessDetail() {
        return processDetail;
    }

    public GeneralResponseInfoDetail getInfoDetail() {
        return infoDetail;
    }


    /**
     * Sets the process detail.
     *
     * @param processDetail the new process detail
     */
    public void setProcessDetail(GeneralResponseProcessDetail processDetail) {
        this.processDetail = processDetail;
    }

    @Override
    public String toString() {
        return "GeneralResponseMessage{" + "header=" + header + ", status='" + status + '\'' + ", moreInfo='" + moreInfo + '\'' + ", processDetail=" + processDetail + ", errorDetail=" + errorDetail + '}';
    }
}
