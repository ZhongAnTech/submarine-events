/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.exception;

import com.zhongan.tech.submarineevents.enums.ResultCode;

import lombok.Data;

@Data
public class EventException extends Exception {
	
	private ResultCode resultCode;
	
	private static final long serialVersionUID = 580665819760410455L;

    public EventException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "EventException [ errCode is " + this.resultCode.getCode() + ", errMsg is " + this.resultCode.getMessage() + "]";
    }


}
