package com.sms.forum.result;

import com.sms.forum.utils.TimeUtils;

public class ApiResponse<T> {
    public int code = ResponseCode.OK;
    public String msg = "success";
    public T data;
    public long systemTime = System.currentTimeMillis();

    public ApiResponse() {

    }
}
