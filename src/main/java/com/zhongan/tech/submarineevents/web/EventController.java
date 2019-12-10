/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.adapter.EventAdapter;
import com.zhongan.tech.submarineevents.consumer.RestAPIEventConsumer;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;
import com.zhongan.tech.submarineevents.enums.ResultCode;
import com.zhongan.tech.submarineevents.exception.EventException;
import com.zhongan.tech.submarineevents.vo.BaseResult;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/event")
public class EventController {
	
	@Autowired
	EventAdapter eventAdapter;
	
	@Autowired
	RestAPIEventConsumer restAPIEventConsumer;
	
    /**
     * @Description Get CloudEvent object, then call restful http api for received event
     * @param cloudEvent  CloudEvent object
     * @return http response
     */
    @RequestMapping(value="/send", method = RequestMethod.POST)
    public BaseResult<?> sendEvent(@RequestBody CloudEvent<?> cloudEvent) {
    	
    	log.info("EventController receive CloudEvent: {}",  JSON.toJSONString(cloudEvent));
    	
		try {
			//convert CloudEvent object to RestAPIEvent object
			RestAPIEvent restAPIEvent = eventAdapter.convertCloudEvent(cloudEvent);
			//Execute http call synchronously for RestAPIEvent object
			JSONObject response = restAPIEventConsumer.syncConsume(restAPIEvent);
			//convert response
			response = eventAdapter.convertResponse(cloudEvent.getType(), response);
			BaseResult<?> baseResult = BaseResult.success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), response);
			return baseResult;
		} catch (EventException e) {
			BaseResult<?> baseResult = BaseResult.fail(e.getResultCode().getCode(), e.getResultCode().getMessage(), null);
			return baseResult;
		}
		
    }
}
