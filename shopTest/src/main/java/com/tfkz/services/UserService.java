package com.tfkz.services;

import com.tfkz.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface UserService {
    //用户登录
    ServerResponse login(HttpSession session,String uname, String password);
}
