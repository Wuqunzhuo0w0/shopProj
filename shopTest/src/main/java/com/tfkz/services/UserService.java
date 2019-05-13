package com.tfkz.services;

import com.tfkz.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface UserService {
    //用户登录
    ServerResponse login(HttpSession session,String uname, String password);
    ServerResponse register(String uname,String psd,String email,String phone,String question,String answer);

    ServerResponse check_valid(String str,String type);

    ServerResponse get_user_info(HttpSession session);

    ServerResponse forget_get_question(String username);

    ServerResponse forget_check_answer(String username,String question,String answer);
}
