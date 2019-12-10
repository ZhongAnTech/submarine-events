/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.dto.EventAdapterRule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/*
 * event-adapter-rules in config file
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "event-adapter-rules")
@Data
public class EventAdapterRules {
	
	List<Map<String,String>> rule;
	
    /**
     * @Description Convert event-adapter-rules in config file to EventAdapterRule map
     * @return EventAdapterRule map
     */
	public Map<String, EventAdapterRule> convertToEventAdapterRuleMap() {
		Map<String, EventAdapterRule> eventAdapterRuleMap = new HashMap<String, EventAdapterRule>(rule.size());
		for(Map<String, String> properties : rule) {
    		try {
    			eventAdapterRuleMap.put(properties.get("type"), convertToEventAdapterRule(properties));
				log.info("Success to load adapter rule of {}", properties.get("type"));
			} catch (Exception e) {
				log.error("Fail to load adapter rule of {} : {}", properties.get("type"), e.getMessage());
			}
    	}
		return eventAdapterRuleMap;
	}
	
    /**
     * @Description Convert rule properties in config file to EventAdapterRule object
     * @param properties  rule properties in config file
     * @return EventAdapterRule object
     * @throws convert error 
     */
	private EventAdapterRule convertToEventAdapterRule(Map<String, String> properties) throws URISyntaxException {
		EventAdapterRule eventAdapterRule = new EventAdapterRule();
		
		eventAdapterRule.setType(properties.get("type"));
		eventAdapterRule.setTargetUri(new URI(properties.get("targetUri")));
		eventAdapterRule.setHttpMethod(HttpMethod.valueOf(properties.get("httpMethod")));
		
		String requestKeyMap = properties.get("requestKeyMap");
		if(null != requestKeyMap && requestKeyMap.trim().length() > 0) {
			JSONObject jsonObject = JSONObject.parseObject(requestKeyMap);
			eventAdapterRule.setRequestKeyMap(JSONObject.toJavaObject(jsonObject, Map.class));
		}
		
		String responseKeyMap = properties.get("responseKeyMap");
		if(null != responseKeyMap && responseKeyMap.trim().length() > 0) {
			JSONObject jsonObject = JSONObject.parseObject(responseKeyMap);
			eventAdapterRule.setResponseKeyMap(JSONObject.toJavaObject(jsonObject, Map.class));
		}
		
		return eventAdapterRule;
	}
}
