package com.tfkz.controller;

import com.google.gson.Gson;
import com.tfkz.common.Const;
import com.tfkz.common.ServerResponse;
import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;
import com.tfkz.services.impl.UserServiceImpl;
import com.tfkz.utils.MD5Utils;
import com.tfkz.utils.UrlSetUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.tfkz.utils.UrlSetUtils.ErroUrl;


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
            case "login.do":
                login(request,response);
                break;
            case "register.do":
                register(request, response);
                break;
            case "check_valid.do":
                check_valid(request,response);
                break;
            case "get_user_info.do":
                get_user_info(request,response);
                break;
            case"forget_get_question.do":
                forget_get_question(request,response);
                break;
            case "forget_check_answer.do":
                forget_check_answer(request,response);
                break;
            case "forget_reset_password.do":
                forget_reset_password(request,response);
                break;
            case "reset_password.do":
                reset_password(request,response);
                break;
            case "update_information.do":
                update_information(request,response);
                break;
            case "logout.do":
                logout(request,response);
                break;
            default:
                    UrlSetUtils.ErroUrl(request,response);
                break;
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
            request.getSession().removeAttribute(Const.CURRENTUSER);
            UrlSetUtils.BackToJson(ServerResponse.createServerResponseBySuccess(),response);
    }

    /**
     登录状态更新个人信息
     */
    private void update_information(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        HttpSession session = request.getSession();
        ServerResponse sr = userService.update_information(session,email,phone,question,answer);
        UrlSetUtils.BackToJson(sr,response);
    }

    /**
     登录中状态重置密码
     */
    private void reset_password(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;
        String passwordOld = request.getParameter("passwordOld");
        String passwordNew = request.getParameter("passwordNew");
        HttpSession session = request.getSession();
        sr = userService.reset_password(session,passwordOld,passwordNew);
        UrlSetUtils.BackToJson(sr,response);
    }

    /**
     忘记密码重设密码
     */
    private void forget_reset_password(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;
        String username = request.getParameter("username");
        String passwordNew = request.getParameter("passwordNew");
        String forgetToken = request.getParameter("forgetToken");
        sr = userService.forget_reset_password(username,passwordNew,forgetToken);
        UrlSetUtils.BackToJson(sr,response);
    }

    /**
     提交问题答案
     */
    private void forget_check_answer(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;
        String username = request.getParameter("username");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        sr = userService.forget_check_answer(username,question,answer);
        UrlSetUtils.BackToJson(sr,response);
    }

    /**
     忘记密码审核
     */
    private void forget_get_question(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;
        String username = request.getParameter("username");
        sr = userService.forget_get_question(username);
        UrlSetUtils.BackToJson(sr,response);
    }


    /**
     获取用户信息
     */
    private void get_user_info(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;
        HttpSession session = request.getSession();
        sr = userService.get_user_info(session);
        UrlSetUtils.BackToJson(sr,response);
    }


    /**
     检查用户名合法
     */
    private void check_valid(HttpServletRequest request, HttpServletResponse response) {
        ServerResponse sr = null;

        String str = request.getParameter("str");
        String type = request.getParameter("type");
        //返回校验信息
        sr = userService.check_valid(str,type);

        UrlSetUtils.BackToJson(sr,response);
    }

    /**
     注册用户
     */
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
        response.setHeader("Access-Control-Allow-Origin","*");
        ServerResponse sr = null;
        String uname = request.getParameter("username");
        String password = request.getParameter("password");

        //创建session
        HttpSession session = request.getSession();

        //发送参数到业务层，返回数据
        sr= userService.login(session,uname,password);
        UrlSetUtils.BackToJson(sr,response);

    }

}
