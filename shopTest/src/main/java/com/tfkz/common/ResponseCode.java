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

    /**
     * 用户登录失败状态码:密码错误
     * */
    public static final int ERROR_PASSWORD=1;

    /**
     * 用户不存在状态码
     */
    public static final int INEXISTENCE_USER = 101;

    /**
     * 用户注册失败状态码：用户已存在
     */
    public static final int EXIST_USER=1;

    /**
     *用户注册失败状态码：邮箱已存在
     */
    public static final int EXIST_EMAIL=2;

    /**
     *获取用户信息失败状态码：用户未登录
     */
    public static final int WITHOUT_LOGIN_USER=1;

    /**
     *忘记密码: 用户未设置找回密码问题
     */
    public static final int EMPTY_QUESTION=1;

    /**
     *token已经失效
     */
    public static final int TIMEOUT_TOKEN=103;

    /**
     * 非法的token
     */
    public static final int UNVALID_TOKEN=103;
    /*=====================商品相关==========================*/

    /**
     * 商品参数错误
     */
    public static final int ERROR_PAMAR = 1;

    /**
     * 商品已下架
     */
    public static final int NO_PRODUCT = 4;

}
