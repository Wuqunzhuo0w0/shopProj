package com.tfkz.services.impl;

import com.google.gson.Gson;
import com.tfkz.common.Const;
import com.tfkz.common.ServerResponse;
import com.tfkz.dao.UserDao;
import com.tfkz.dao.impl.UserDaoImpl;
import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    UserDao ud = new UserDaoImpl();

    @Override
    public ServerResponse login(HttpSession session, String uname, String psd) {
        ServerResponse sr = null;

        if(uname == null || uname.equals("")){
            //返回错误状态码和信息
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),Const.ReponseCodeEnum.EMPTY_USERNAME.getDescrib());
            return sr;
        }

        //判断密码是否为空
        if(psd == null || psd.equals("")){
            //返回错误状态码和信息
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EMPTY_PASSWORD.getCode(),Const.ReponseCodeEnum.EMPTY_PASSWORD.getDescrib());
            return sr;
        }

        //去数据中判断是否存在该用户
//        int a = ud.selectByUname(uname);
        //去数据库中判断密码是否正确
        UserIn u = ud.selectByUnameAndPsd(uname,psd);
        if(u == null){
            //返回错误状态码和信息
            return null;
        }

        //保存session
        session.setAttribute(Const.RoleEnum.ROLE_CUSTOMER.getDescrib(),u);

        //返回用户详细信息的数据时，不能把密码一块返回
        UserIn u2 = new UserIn();
        u2.setUsername(u.getUsername());
        u2.setPassword("");
        u2.setEmail(u.getEmail());
        u2.setPhone(u.getPhone());
        u2.setRole(u.getRole());

        sr = ServerResponse.createServerResponseBySuccess(u2);
        //成功返回数据
        return sr;
    }



}
