/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	
    /**
     * @Description Convert json object according to rule map
     * @param source  the json object to be converted
     * @param keyMap  rule map
     * @return the converted json object 
     */
	public static JSONObject convertJSONObject(JSONObject source, Map<String, String> keyMap) {
		
		JSONObject target = new JSONObject();
		
		for(Entry<String, String> e: keyMap.entrySet()) {
			String sourceKey = e.getKey();
			String[] sourceKeys = sourceKey.split("\\.");
			Object value = null;
			boolean sourceKeyExist = false;
			JSONObject subSource = source;
			for(int i = 0; i < sourceKeys.length; i++) {
				if(null == subSource.get(sourceKeys[i])) {
					break;
				}else {
					if(i != sourceKeys.length - 1) {
						subSource = subSource.getJSONObject(sourceKeys[i]);
					}else {
						sourceKeyExist = true;
						value = subSource.get(sourceKeys[i]);
					}
				}
			}
			if(!sourceKeyExist) continue;
			
			String targetKey = e.getValue();
			String[] targetKeys = targetKey.split("\\.");
			JSONObject subTarget = target;
			for(int i = 0; i < targetKeys.length; i++) {
				if(null == subTarget.getJSONObject(targetKeys[i])) {
					if(i != targetKeys.length - 1) {
						subTarget.put(targetKeys[i], new JSONObject());
						subTarget = subTarget.getJSONObject(targetKeys[i]);
					}else {
						subTarget.put(targetKeys[i], value);
					}
				}else {
					subTarget = subTarget.getJSONObject(targetKeys[i]);
				}
			}
		}
		return target;
	}
}
