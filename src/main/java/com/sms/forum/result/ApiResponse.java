package com.sms.forum.result;

public class ApiResponse<T> {
    public int code = ResponseCode.OK;
    public String msg = "success";
    public T data;
    public long systemTime;

    public ApiResponse() {

    }
}
