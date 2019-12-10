/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;

public class BaseBean implements Serializable, Cloneable {

    private static final long serialVersionUID = -265741933689599525L;
   
    private String requestId = UUID.randomUUID().toString().replace("-", "");

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }
}
