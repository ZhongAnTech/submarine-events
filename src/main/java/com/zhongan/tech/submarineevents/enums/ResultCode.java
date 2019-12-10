/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum  ResultCode implements Serializable {

    SUCCESS("200", "Success"),
	
	EVENT_ADAPTER_RULE_NOT_EXIST("1001","Event adapter rule doesn't exist");

    private String code;

    private String message;

}
