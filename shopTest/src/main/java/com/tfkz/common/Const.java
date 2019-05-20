package com.tfkz.common;

public class Const {
    public static final String CURRENTUSER = "current_user";

    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    public static final String AUTOLOGINTOKEN = "autoLoginToken";

    public static final String JESSESSIONID_COOKIE = "JESSIONID_COOKIE";

    public enum ReponseCodeEnum {

        NEED_LOGIN(2, "需要登录"),
        NO_PRIVILEGE(3, "无权限操作"),
        //状态信息
        ERROR_PASSWORD(ResponseCode.ERROR_PASSWORD, "密码错误"),
        EMPTY_USERNAME(ResponseCode.ERROR, "用户名不能为空"),
        EMPTY_PASSWORD(ResponseCode.ERROR, "密码不能为空"),
        EMPTY_QUESTION(ResponseCode.ERROR, "问题不能为空"),
        EMPTY_ANSWER(ResponseCode.ERROR, "答案不能为空"),
        INEXISTENCE_USER(ResponseCode.INEXISTENCE_USER, "用户名不存在"),
        EXIST_USER(ResponseCode.EXIST_USER,"用户名已存在"),
        EXIST_EMAIL(ResponseCode.EXIST_EMAIL,"邮箱已注册"),
        SUCCESS_USER(ResponseCode.SUCESS,"用户注册成功"),
        SUCCESS_MSG(ResponseCode.SUCESS,"校验成功"),
        WITHOUT_LOGIN_USER(ResponseCode.WITHOUT_LOGIN_USER,"用户未登录，无法获取当前用户信息"),
        UNINITIALIZE_QIESTION(ResponseCode.EMPTY_QUESTION,"忘记密码 用户未设置找回密码问题"),
        TIMEOUT_TOKEN(ResponseCode.TIMEOUT_TOKEN,"token已经失效"),
        UNVALID_TOKEN(ResponseCode.UNVALID_TOKEN,"非法的token"),
        ;

        private int code;
        private String describ;
        ReponseCodeEnum(int code, String describ) {
            this.code = code;
            this.describ = describ;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setDescrib(String describ) {
            this.describ = describ;
        }

        public String getDescrib() {
            return describ;
        }
    }

    public  enum ProductStatusEnum{

        PRODUCT_ONLINE(1,"在售"),
        PRODUCT_OFFLINE(2,"下架"),
        PRODUCT_DELETE(3,"删除"),
        ERROR_PAMAR(ResponseCode.ERROR_PAMAR,"参数错误"),
        NO_PRODUCT(ResponseCode.NO_PRODUCT,"该商品已下架")

        ;
        private  int  code;
        private String desc;
        private ProductStatusEnum(int code,String desc){
            this.code=code;
            this.desc=desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public  enum  RoleEnum{

        ROLE_ADMIN(0,"管理员"),
        ROLE_CUSTOMER(1,"普通用户")
        ;

        private  int  code;
        private String describ;
        private RoleEnum(int code,String describ){
            this.code=code;
            this.describ=describ;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescrib() {
            return describ;
        }

        public void setDescrib(String desc) {
            this.describ = desc;
        }
    }
}