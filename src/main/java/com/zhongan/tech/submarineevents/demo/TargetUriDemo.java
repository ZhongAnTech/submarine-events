/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo")
public class TargetUriDemo {
	
    @RequestMapping(value="/toUpperCase", method = RequestMethod.GET)
    public Map<String,String> toUpperCase(@RequestParam("convertedReqName1") String newReqName1, @RequestParam("convertedReqName2") String newReqName2) {
    	Map<String,String> demoResponse = new HashMap<String,String>();
    	demoResponse.put("oriRespName1", newReqName1.toUpperCase()); 
    	demoResponse.put("oriRespName2", newReqName2.toUpperCase());
    	return demoResponse;
    }
    
}
