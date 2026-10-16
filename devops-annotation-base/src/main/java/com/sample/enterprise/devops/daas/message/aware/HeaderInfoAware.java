package com.sample.enterprise.devops.daas.message.aware;

import com.sample.enterprise.devops.daas.message.model.MessageDispatchHeader;

public interface HeaderInfoAware {

    /**
     * Gets the header.
     *
     * @return the header
     */
    MessageDispatchHeader getHeader();

    /**
     * Sets the header.
     *
     * @param header the new header
     */
    void setHeader(MessageDispatchHeader header);

}
