package com.tfkz.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status; //返回前端的状态码
    private T data;     //返回前端的数据
    private String message;  //当status=0，封装了错误信息

    private ServerResponse(){

    }

    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    private ServerResponse(int status, T data , String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /**
     *
     *接口调用成功时回调
     */

    public static ServerResponse createServerResponseBySuccess(){
        return new ServerResponse(ResponseCode.SUCESS);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data) {
        return new ServerResponse(ResponseCode.SUCESS, data);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data, String msg) {
        return new ServerResponse(ResponseCode.SUCESS, data, msg);
    }


    /**
     *
     * 接口调用失败时回调
     */

    public static ServerResponse createServerResponseByError(){
        return  new ServerResponse(ResponseCode.ERROR);
    }

    public static ServerResponse createServerResponseByError(String message){
        return  new ServerResponse(ResponseCode.ERROR,message);
    }

    public static  ServerResponse createServerResponseByError(int status){
        return  new ServerResponse(status);
    }

    public static ServerResponse createServerResponseByError(int status,String message){
        return  new ServerResponse(status,message);
    }
}
