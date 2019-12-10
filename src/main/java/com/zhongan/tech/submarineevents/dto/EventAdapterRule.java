/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.dto;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpMethod;

import lombok.Data;

@Data
public class EventAdapterRule {
	
	/*
	 * cloudEvent type
	 */
	private String type;
	
	/*
	 * restful http api
	 */
	private URI targetUri;
	
	/*
	 * http method
	 */
	private HttpMethod httpMethod;
	
	/*
	 * rule map which convert CloudEvent.data to request of restful http api
	 */
	private Map<String, String> requestKeyMap;
	
	/*
	 * rule map which convert response of restful http api
	 */
	private Map<String, String> responseKeyMap;
}
