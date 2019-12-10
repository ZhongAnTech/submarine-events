/*
 * Copyright 2019 Zhongan.com All right reserved. This software is the
 * confidential and proprietary information of Zhongan.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Zhongan.com.
 */
package com.zhongan.tech.submarineevents.vo;

import lombok.Data;

@Data
public class BaseResult<T> extends BaseBean {

    private static final long serialVersionUID = -6440697160058238736L;

    private boolean success;

    private String code;

    private String msg;
    
    private T data;
    
    public BaseResult(boolean success, String code, String msg, T data){
    	super();
    	this.success = success;
    	this.code = code;
    	this.msg = msg;
    	this.data = data;
    }

    public static <T> BaseResult<T> success(String code, String msg, T data) {
        BaseResult<T> result = new BaseResult<T>(true, code, msg, data);
        return result;
    }

    public static <T> BaseResult<T> fail(String code, String msg, T data) {
    	BaseResult<T> result = new BaseResult<T>(false, code, msg, data);
        return result;
    }
}
