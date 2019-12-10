/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.dto;

import java.net.URI;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

import org.springframework.http.HttpMethod;

@Data
public class RestAPIEvent {
	
	/*
	 * cloudEvent type
	 */
	private String type;
	
	/*
	 * cloudEvent id
	 */
	private String id;
	
	/*
	 * cloudEvent source
	 */
	private URI source;
	
	/*
	 * restful http api
	 */
	private URI target;
	
	/*
	 * http method
	 */
	private HttpMethod httpMethod;
	
	/*
	 * request of restful http api
	 */
	private JSONObject request;
}
