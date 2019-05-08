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
        UserIn uExist = ud.selectByUsername(uname);
        if(uExist==null){
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.INEXISTENCE_USER.getCode(),Const.ReponseCodeEnum.INEXISTENCE_USER.getDescrib());
            return sr;
        }
        //去数据库中判断密码是否正确
        UserIn u = ud.selectByUnameAndPsd(uname,psd);
        if(u == null){
            //返回错误状态码和信息
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.ERROR_PASSWORD.getCode(),Const.ReponseCodeEnum.ERROR_PASSWORD.getDescrib());
            return sr;
        }


        //返回用户详细信息的数据时，不能把密码一块返回
        UserIn u2 = new UserIn();
        u2.setId(u.getId());
        u2.setUsername(u.getUsername());
        u2.setPassword("");
        u2.setEmail(u.getEmail());
        u2.setPhone(u.getPhone());
        u2.setRole(u.getRole());

        //保存session
        session.setAttribute(Const.RoleEnum.ROLE_CUSTOMER.getDescrib(),u2);

        sr = ServerResponse.createServerResponseBySuccess(u2);
        //成功返回数据
        return sr;
    }

    @Override
    public ServerResponse register(String uname, String psd, String email, String phone, String question, String answer) {
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
        UserIn uExist = ud.selectByUsername(uname);
        if(uExist!=null){
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EXIST_USER.getCode(),Const.ReponseCodeEnum.EXIST_USER.getDescrib());
            return sr;
        }

        //去判断邮箱是否已经注册
        UserIn eExist = ud.selectByEmail(uname);
        if(eExist!=null){
            sr = ServerResponse.createServerResponseByError(
                    Const.ReponseCodeEnum.EXIST_EMAIL.getCode(),Const.ReponseCodeEnum.EXIST_EMAIL.getDescrib());
            return sr;
        }

        int reg_code=0;
        reg_code = ud.insertByUnameAndPsd(uname,psd,email,phone,question,answer);

        if(reg_code>0){
            //说明用户注册成功了
            sr = ServerResponse.createServerResponseBySuccess(Const.ReponseCodeEnum.SUCCESS_USER.getDescrib());
            return sr;
        }

        sr = ServerResponse.createServerResponseByError("用户注册失败");
        return sr;
    }

    @Override
    public ServerResponse check_valid(String str,String type) {

        ServerResponse sr = null;
        //检查用户名是否为空
        /*
        if(uname==""||uname==null){
            sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getDescrib());
            return sr;
        }*/

        //检查用户 |  邮箱是否已存在
        UserIn check;
        if(type.equals("uname")) {
            check = ud.selectByUsername(str);
            if (check != null) {
                System.out.println("check!=null");
                sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EXIST_USER.getCode(),
                        Const.ReponseCodeEnum.EXIST_USER.getDescrib());
                return sr;
            }
        }else if (type.equals("email")){
            check = ud.selectByEmail(str);
            if(check != null){
                System.out.println("check!=null");
                sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EXIST_EMAIL.getCode(),
                        Const.ReponseCodeEnum.EXIST_EMAIL.getDescrib());
                return sr;
            }
        }
        //用户名校验成功 并返回状态码
        sr = ServerResponse.createServerResponseBySuccess(Const.ReponseCodeEnum.SUCCESS_MSG.getDescrib());
        return sr;
    }

    @Override
    public ServerResponse get_user_info(HttpSession session) {
        ServerResponse sr = null;
        UserIn user = (UserIn) session.getAttribute(Const.RoleEnum.ROLE_CUSTOMER.getDescrib());
        if(user==null){
            sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.WITHOUT_LOGIN_USER.getCode(),
                    Const.ReponseCodeEnum.WITHOUT_LOGIN_USER.getDescrib());
            return  sr;
        }
        sr = ServerResponse.createServerResponseBySuccess(user);
        return sr;
    }

    @Override
    public ServerResponse forget_get_question(String username) {
        ServerResponse sr = null;
        if(username==null){
            sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getDescrib());
            return sr;
        }
        UserIn user = ud.selectByUsername(username);
        if(user==null){
            sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.INEXISTENCE_USER.getCode(),
                    Const.ReponseCodeEnum.INEXISTENCE_USER.getDescrib());
            return sr;
        }else {
            if(user.getQuestion()==null){
                sr = ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.UNINITIALIZE_QIESTION.getCode(),
                        Const.ReponseCodeEnum.UNINITIALIZE_QIESTION.getDescrib());
                return sr;
            }
        }
        sr = ServerResponse.createServerResponseBySuccess(user.getQuestion());
        return sr;
    }


}
