package com.tfkz.controller;

import com.google.gson.Gson;
import com.tfkz.common.ServerResponse;
import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;
import com.tfkz.services.impl.UserServiceImpl;
import com.tfkz.utils.UrlSetUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserController",value = "/user/*")
public class UserController extends HttpServlet {

    UserService userService = new UserServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径
        String method = UrlSetUtils.getMethodByUrl(request);
        switch (method){
            case "login":
                login(request,response);
                break;
            case "register":
                register(request, response);
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;

        String uname = request.getParameter("uname");
        String password = request.getParameter("psd");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String question = request.getParameter("quest");
        String answer = request.getParameter("answer");

        sr = userService.register(uname,password,email,phone,question,answer);

        UrlSetUtils.BackToJson(sr,response);

    }


    public void login(HttpServletRequest request, HttpServletResponse response){
        ServerResponse sr = null;

        String uname = request.getParameter("uname");
        String password = request.getParameter("psd");

        //创建session
        HttpSession session = request.getSession();

        //发送参数到业务层，返回数据
        sr= userService.login(session,uname,password);
        UrlSetUtils.BackToJson(sr,response);

    }

}
