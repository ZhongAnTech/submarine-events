/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.stream.handler;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.adapter.EventAdapter;
import com.zhongan.tech.submarineevents.consumer.RestAPIEventConsumer;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;
import com.zhongan.tech.submarineevents.exception.EventException;

import io.cloudevents.CloudEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EventHandler {
	
	@Autowired EventAdapter eventAdapter;
	  
	@Autowired RestAPIEventConsumer restAPIEventConsumer;
	
	@Bean 
	public Consumer<CloudEvent> restAPIConsume() { 
		
		 return (cloudEvent)->{
			 
			 log.info("Receive CloudEvent: {}", JSON.toJSONString(cloudEvent)); 
			 
			 try {
				//convert CloudEvent object to RestAPIEvent object
				 RestAPIEvent restAPIEvent = eventAdapter.convertCloudEvent(cloudEvent);
				 //Execute http call synchronously for RestAPIEvent object 
				 JSONObject response = restAPIEventConsumer.syncConsume(restAPIEvent);
				 //convert response
				 response = eventAdapter.convertResponse(cloudEvent.getType(), response);
				 log.info("response: {}", response);
			 } catch(EventException e) {
				 e.printStackTrace();
			 }
			 
		 };
		 
	  }
}
