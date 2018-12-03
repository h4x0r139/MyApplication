package cn.yinxm.lib.interfaces.iml;

import java.io.Serializable;

import cn.yinxm.lib.interfaces.ILoginInfo;
import cn.yinxm.lib.utils.StringUtil;


/**
 * Created by yinxm on 2016/11/23.
 * 功能:
 */

public class LoginInfoIml implements ILoginInfo, Serializable {

    private boolean isLogin;
    private String loginId;//登录账号
    private String mobile;//手机号
    private String userId;//用户唯一标识
    private String mark;//服务器登录标识
    private String userName;//用户昵称
    private String avatar;//用户头像
    private Gender gender = Gender.Undefined;//性别
    private Object ext;//扩展信息


    @Override
    public boolean isLogin() {
        return isLogin;
    }

    @Override
    public String getLoginId() {
        if (StringUtil.isBlank(loginId)) {
            return "";
        }
        return loginId;
    }

    @Override
    public String getUserId() {
        if (StringUtil.isBlank(userId)) {
        return "";
    }
        return this.userId;
    }

    @Override
    public String getMark() {
        if (StringUtil.isBlank(mark)) {
            return "";
        }
        return this.mark;
    }

    @Override
    public String getMobile() {
        if (StringUtil.isBlank(mobile)) {
            return "";
        }
        return mobile;
    }

    @Override
    public String getUserName() {
        if (StringUtil.isBlank(userName)) {
            return "";
        }
        return this.userName;
    }

    @Override
    public String getAvatar() {
        if (StringUtil.isBlank(avatar)) {
            return "";
        }
        return this.avatar;
    }

    @Override
    public Gender getGender() {
        if (gender == null) {
            return Gender.Undefined;
        }
        return this.gender;
    }

    @Override
    public Object getExt() {
        return this.ext;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }
}
