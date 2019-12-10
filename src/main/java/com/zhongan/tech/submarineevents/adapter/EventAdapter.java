/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.adapter;

import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;
import com.zhongan.tech.submarineevents.exception.EventException;

import io.cloudevents.CloudEvent;

public interface EventAdapter {
	
    /**
     * @Description Convert CloudEvent object to RestAPIEvent object
     * @param cloudEvent  CloudEvent object  
     * @return RestAPIEvent object
     * @throws convert error
     */
	RestAPIEvent convertCloudEvent(CloudEvent<?> cloudEvent) throws EventException;
	
    /**
     * @Description Convert response
     * @param type  type of event adapter rule  
     * @param response  original response
     * @return converted response
     * @throws convert error
     */
	JSONObject convertResponse(String type, JSONObject response) throws EventException;
}
