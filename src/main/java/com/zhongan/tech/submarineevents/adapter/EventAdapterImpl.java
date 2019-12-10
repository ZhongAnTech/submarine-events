/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.adapter;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.dto.EventAdapterRule;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;
import com.zhongan.tech.submarineevents.enums.ResultCode;
import com.zhongan.tech.submarineevents.exception.EventException;
import com.zhongan.tech.submarineevents.utils.JSONUtil;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventAdapterImpl implements EventAdapter{

	@Autowired
	Map<String, EventAdapterRule> eventAdapterRuleMap;
	
	@Override
	public RestAPIEvent convertCloudEvent(CloudEvent<?> cloudEvent) throws EventException {
		EventAdapterRule eventAdapterRule = eventAdapterRuleMap.get(cloudEvent.getType());
    	if(null != eventAdapterRule) {
    		RestAPIEvent restAPIEvent = new RestAPIEvent();
    		restAPIEvent.setType(cloudEvent.getType());
    		restAPIEvent.setId(cloudEvent.getId());
    		restAPIEvent.setSource(cloudEvent.getSource());
    		restAPIEvent.setTarget(eventAdapterRule.getTargetUri());
    		restAPIEvent.setHttpMethod(eventAdapterRule.getHttpMethod());
        	if(Optional.empty() != cloudEvent.getData()) {
        		JSONObject sourceRequest = JSONObject.parseObject(JSON.toJSONString(cloudEvent.getData().get()));
        		if(null != eventAdapterRule.getRequestKeyMap()) {
        			JSONObject targetRequest = JSONUtil.convertJSONObject(sourceRequest, eventAdapterRule.getRequestKeyMap());
        			restAPIEvent.setRequest(targetRequest);
        		}else {
        			restAPIEvent.setRequest(sourceRequest);
        		}
        	}
        	log.info("Success to convert CloudEvent to RestAPIEvent: {}", JSON.toJSONString(restAPIEvent));
        	return restAPIEvent;
    	}else {
    		log.error("Adapter rule of {} doesn't exist", cloudEvent.getType());
    		throw new EventException(ResultCode.EVENT_ADAPTER_RULE_NOT_EXIST);
    	}
	}

	@Override
	public JSONObject convertResponse(String type, JSONObject response) throws EventException {
		EventAdapterRule eventAdapterRule = eventAdapterRuleMap.get(type);
    	if(null != eventAdapterRule) {
    		if(null != eventAdapterRule.getResponseKeyMap() && null != response) {
    			return JSONUtil.convertJSONObject(response, eventAdapterRule.getResponseKeyMap());
    		}else {
    			return response;
    		}
    	}else {
    		log.error("Adapter rule of {} doesn't exist", type);
    		throw new EventException(ResultCode.EVENT_ADAPTER_RULE_NOT_EXIST);
    	}
	}

}
