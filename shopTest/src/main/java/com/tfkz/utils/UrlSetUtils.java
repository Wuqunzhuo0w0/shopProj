package com.tfkz.utils;

import com.google.gson.Gson;
import com.tfkz.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlSetUtils {

    public static Gson gson = new Gson();
    /**
     * 获取前端返回的连接URL字符串，并切割获得最后的方法名称
     */
    public static  String getMethodByUrl(HttpServletRequest request){
        String uri = request.getRequestURI();
        String[] split = uri.split("/");
        String method = split[split.length-1];
        return method;
    }

    /**
     * 成功请求返回数据统一json处理
     */
    public static void BackToJson(ServerResponse sr,HttpServletResponse response){
        //把数据返回给前台
        //数据转换成json数据格式

        String s = gson.toJson(sr);
        //数据返回给浏览器
        response.setContentType("text/json;charset=utf-8");

        try {
            response.getWriter().println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求错误统一返回数据
     */
    public static void ErroUrl(HttpServletRequest request, HttpServletResponse response){
        ServerResponse sr = ServerResponse.createServerResponseByError(999,"请求路径错误");
        //把数据返回给前台
        //数据转换成json数据格式
        BackToJson(sr,response);
    }
}
