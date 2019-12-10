/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.config;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.zhongan.tech.submarineevents.dto.EventAdapterRule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EventAdapterRuleMapConfig {
	
	@Autowired
	EventAdapterRules eventAdapterRules;
	
	@RefreshScope
	@Bean
	public Map<String, EventAdapterRule> initEventAdapterRuleMap() {
		log.info("Start to init event adapter rule map");
		Map<String, EventAdapterRule> eventAdapterRuleMap = eventAdapterRules.convertToEventAdapterRuleMap();
		printEventAdapterRules(eventAdapterRuleMap);
		return eventAdapterRuleMap;
	}
	
	private void printEventAdapterRules(Map<String, EventAdapterRule> eventAdapterRuleMap) {
		if(null == eventAdapterRuleMap || eventAdapterRuleMap.isEmpty()) {
			log.info("Event adapter rule map is null or empty");
			return;
		}
		log.info("Event adapter rule map:");
		for(Entry<String, EventAdapterRule> e : eventAdapterRuleMap.entrySet()) {
			log.info(e.getKey()+" : "+JSON.toJSONString(e.getValue()));
		}
	}
}
