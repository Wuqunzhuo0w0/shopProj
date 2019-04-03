package com.tfkz.controller;

import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;
import com.tfkz.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserController",value = "/user/*")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径
        String uri = request.getRequestURI();
        System.out.println(uri);
        String[] split = uri.split("/");
        System.out.println(split[split.length-1]);
        String method = split[split.length-1];
        switch (method){
            case "login":
                login(request,response);
                break;
        }
    }

    UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("psd");
        System.out.println(email+"====="+password);
        //把参数发到业务层处理，并返回数据
        UserIn user = userService.login(email,password);

        //创建Session
        HttpSession session = request.getSession();
        session.setAttribute("普通用户",user);

        //返回用户信息时候，不能把密码一块返回
        UserIn user2 = new UserIn();
        user2.setPassword("");
        user2.setUsername(user.getUsername());
        user2.setEmail(user.getEmail());
        //返回数据给浏览器
        try {
            System.out.println(user2.toString());
            request.getRequestDispatcher("/home.jsp").forward(request,response);
            response.getWriter().write(user2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
