/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.consumer;

import com.alibaba.fastjson.JSONObject;
import com.zhongan.tech.submarineevents.dto.RestAPIEvent;

public interface RestAPIEventConsumer {
	
    /**
     * @Description Execute http call synchronously for RestAPIEvent object
     * @param restAPIEvent  RestAPIEvent object  
     * @return http response body
     */
	JSONObject syncConsume(RestAPIEvent restAPIEvent);
}
