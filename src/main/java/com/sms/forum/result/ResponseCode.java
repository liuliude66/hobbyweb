package com.sms.forum.result;

public class ResponseCode {
    public final static int OK = 0;//成功
    public final static int INSERT_TEL_EX = -1;//插入电话号码异常
    public final static int INSERT_CONTENT_EX = -2;//插入短信内容异常
    public final static int OBTAIN_TEL_EX = -3;//获取号码异常
    public final static int LOGIN_ERROR_PASSWORD = -101;//登录的密码错误
    public final static int LOGIN_ERROR_USERNAME = -102;//登录的账号错误
    public final static int LOGIN_ERROR_NULL = -103;//登录的账号包含空信息
    public final static int LOGIN_ERROR_ONLY = -104;//登录的账号包含空信息
    public final static int SESSION_ERROR = -100;//session异常
}
