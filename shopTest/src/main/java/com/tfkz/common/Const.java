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