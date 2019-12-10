/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.utils;

import com.alibaba.fastjson.JSONObject;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateManager {

	@Autowired
	private RestTemplate restTemplate;
	
    /**
     * @Description Execute http call
     * @param uri  http request location
     * @param httpMethod  http method
     * @param req  http request
     * @return http response
     */
	public JSONObject call(URI uri, HttpMethod httpMethod, JSONObject req) {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri.toString());
		
		if(!httpMethod.equals(HttpMethod.POST)) {
			Map<String, String> params = JSONObject.toJavaObject(req, Map.class);
			if(null != params && !params.isEmpty()) {
				for(Entry<String, String> e: params.entrySet()) {
					builder.queryParam(e.getKey(), e.getValue());
				}
			}
		}
		
		HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(req);

		ResponseEntity<JSONObject> response = restTemplate.exchange(builder.toUriString(), httpMethod, request, JSONObject.class);
		
		return response.getBody();
	}
	
}
