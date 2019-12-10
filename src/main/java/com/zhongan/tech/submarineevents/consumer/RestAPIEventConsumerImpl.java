/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;
import com.zhongan.tech.submarineevents.utils.RestTemplateManager;

@Service
public class RestAPIEventConsumerImpl implements RestAPIEventConsumer{

	@Autowired
	RestTemplateManager restTemplateManager;
	
	@Override
	public JSONObject syncConsume(RestAPIEvent restAPIEvent) {
		return restTemplateManager.call(restAPIEvent.getTarget(), restAPIEvent.getHttpMethod(), restAPIEvent.getRequest());
	}
}
