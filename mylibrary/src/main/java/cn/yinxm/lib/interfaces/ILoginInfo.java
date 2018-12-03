package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/11/23.
 * 功能: 登录信息接口
 */

public interface ILoginInfo {
    /**
     * 是否登录
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录id
     * @return
     */
    String getLoginId();

    /**
     * 获取userId
     * @return
     */
    String getUserId();

    /**
     * 获取mark
     * @return
     */
    String getMark();

    /**
     * 获取手机号
     * @return
     */
    String getMobile();

    /**
     * 获取用户名、昵称
     * @return
     */
    String getUserName();

    /**
     * 获取头像
     * @return
     */
    String getAvatar();

    /**
     * 获取性别
     * @return
     */
    Gender getGender();

    /**
     * 获取扩展信息
     * @return
     */
    Object getExt();


    enum Gender {
        Male,
        Female,
        Undefined;

        private Gender() {
        }
    }
}
