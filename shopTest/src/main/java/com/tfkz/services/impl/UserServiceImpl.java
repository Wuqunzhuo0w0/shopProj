package com.tfkz.services.impl;

import com.tfkz.common.Const;
import com.tfkz.common.ResponseCode;
import com.tfkz.common.ServerResponse;
import com.tfkz.dao.UserDao;
import com.tfkz.dao.impl.UserDaoImpl;
import com.tfkz.domin.pojo.UserIn;
import com.tfkz.services.UserService;
import com.tfkz.utils.MD5Utils;
import com.tfkz.utils.TokenCache;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
        UserIn u = ud.selectByUnameAndPsd(uname,MD5Utils.getMD5Code(psd));
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
        u2.setCreateTime(u.getCreateTime());
        u2.setUpdateTime(u.getUpdateTime());

        //保存session
        session.setAttribute(Const.CURRENTUSER,u2);

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
        reg_code = ud.insertByUnameAndPsd(uname, MD5Utils.getMD5Code(psd),email,phone,question,answer);

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
        UserIn user = (UserIn) session.getAttribute(Const.CURRENTUSER);
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

    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        ServerResponse sr = null;
        //step1:参数校验
        if(username==null||username.equals("")){
            return ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EMPTY_USERNAME.getCode(),
                    Const.ReponseCodeEnum.EMPTY_USERNAME.getDescrib());
        }
        if(question==null||question.equals("")){
            return ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EMPTY_QUESTION.getCode(),
                    Const.ReponseCodeEnum.EMPTY_QUESTION.getDescrib());
        }
        if(answer==null||answer.equals("")){
            return ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.EMPTY_ANSWER.getCode(),
                    Const.ReponseCodeEnum.EMPTY_ANSWER.getDescrib());
        }
        //step2:根据username,question,answer查询
        UserIn result= ud.selectByUsernameAndQuestionAndAnswer(username,question,answer);
        if(result==null){
            //答案错误
            return ServerResponse.createServerResponseByError("答案错误");
        }
        //step3:服务端生成一个token保存并将token返回给客户端。
        String  forgetToken= UUID.randomUUID().toString();
        //guava cache
        TokenCache.set(username,forgetToken);
        //Redius 缓冲池 后续添加.
        sr = ServerResponse.createServerResponseBySuccess(forgetToken);
        return sr;
    }

    @Override
    public ServerResponse forget_reset_password(String username, String passwordNew, String forgetToken) {
        if(username==null||username.equals("")){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if(passwordNew==null||passwordNew.equals("")){
            return ServerResponse.createServerResponseByError("密码不能为空");
        }
        if(forgetToken==null||forgetToken.equals("")){
            return ServerResponse.createServerResponseByError("token不能为空");
        }

        //step2:token校验
        String token= TokenCache.get(username);
        if(token==null){
            return ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.TIMEOUT_TOKEN.getCode(),
                    Const.ReponseCodeEnum.TIMEOUT_TOKEN.getDescrib());
        }
        if(!token.equals(forgetToken)){
            return ServerResponse.createServerResponseByError(Const.ReponseCodeEnum.UNVALID_TOKEN.getCode(),
                    Const.ReponseCodeEnum.UNVALID_TOKEN.getDescrib());
        }

        //step3:修改密码
        int result= ud.updateUserPassowrd(username,MD5Utils.getMD5Code(passwordNew));
        if(result>0){
            return ServerResponse.createServerResponseBySuccess();
        }
        return ServerResponse.createServerResponseByError(ResponseCode.ERROR_PASSWORD,"密码修改失败");
    }

    @Override
    public ServerResponse reset_password(HttpSession session, String passwordOld, String passwordNew) {

        if(passwordOld==null||passwordOld.equals("")){
            return ServerResponse.createServerResponseByError("用户名旧密码不能为空");
        }
        if(passwordNew==null||passwordNew.equals("")){
            return ServerResponse.createServerResponseByError("用户新密码不能为空");
        }
        UserIn userInfo=(UserIn) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError(ResponseCode.WITHOUT_LOGIN_USER,"用户未登录，无法获取当前用户信息");
        }
        UserIn userIn = ud.selectByUnameAndPsd(userInfo.getUsername(),MD5Utils.getMD5Code(passwordOld));
        if(userIn == null){
            return  ServerResponse.createServerResponseByError(ResponseCode.ERROR_PASSWORD,"旧密码输入错误");
        }
        Integer result;
        result = ud.updateUserPassowrd(userInfo.getUsername(),MD5Utils.getMD5Code(passwordNew));
        if(result>0){
            return ServerResponse.createServerResponseBySuccess("修改密码成功");
        }else return ServerResponse.createServerResponseByError("密码修改失败");

    }


}
