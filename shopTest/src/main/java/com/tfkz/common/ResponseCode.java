package com.tfkz.common;


/**
 * 维护状态码
 */
public class ResponseCode {
    /**
     * 成功的状态码
     * */
    public static final  int SUCESS=0;

    /**
     * 失败时通用状态码
     * */
    public static  final int ERROR=100;

    /*
     * 用户登录失败状态码:密码错误
     * */
    public static final int ERROR_PASSWORD=1;

    /*用户不存在状态码*/
    public static final int INEXISTENCE_USER = 101;


}
