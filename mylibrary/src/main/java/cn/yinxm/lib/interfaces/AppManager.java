package cn.yinxm.lib.interfaces;

import android.content.Context;

import cn.yinxm.lib.interfaces.iml.AppAudioManagerIml;
import cn.yinxm.lib.interfaces.iml.AppConfigIml;
import cn.yinxm.lib.interfaces.iml.AppLocationIml;
import cn.yinxm.lib.interfaces.iml.AudioPlayControllerIml;
import cn.yinxm.lib.interfaces.iml.CallManagerIml;
import cn.yinxm.lib.interfaces.iml.LoginInfoIml;


/**
 * Created by yinxm on 2016/8/11.
 */
public  class AppManager {
    private AppManager(){}
    private Context applicationContext;
    private IAppConfig workEcConfig;
    private IAppAudioManager workEcAudioManager;
    private IAudioPlayController audioPlayController;
    private ICallManager callManager;
    private ILoginInfo loginInfo;
    private IAppLocation location;


    public static AppManager getInstance() {
        return AppManagerFactory.instance;
    }
    private static class AppManagerFactory{
        private static AppManager instance = new AppManager();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public IAppConfig getAppConfig() {
        if (workEcConfig == null) {
            workEcConfig = new AppConfigIml();
        }
        return workEcConfig;
    }

    public  void setAppConfig(IAppConfig workEcConfig) {
        this.workEcConfig = workEcConfig;
    }

    public IAppAudioManager getAppAudioManager() {
        if (workEcAudioManager == null) {
            workEcAudioManager = new AppAudioManagerIml();
        }
        return workEcAudioManager;
    }
    public void setAppAudioManager(IAppAudioManager workEcAudioManager) {
        this.workEcAudioManager = workEcAudioManager;
    }

    public IAudioPlayController getAudioPlayController() {
        if (audioPlayController == null) {
            audioPlayController = new AudioPlayControllerIml();
        }
        return audioPlayController;
    }

    public void setAudioPlayController(IAudioPlayController audioPlayController) {
        this.audioPlayController = audioPlayController;
    }

    public ICallManager getCallManager() {
        if (callManager == null) {
            callManager = new CallManagerIml();
        }
        return callManager;
    }

    public void setCallManager(ICallManager callManager) {
        this.callManager = callManager;
    }

    public ILoginInfo getLoginInfo() {
        if (loginInfo == null) {
            loginInfo = new LoginInfoIml();
        }
        return loginInfo;
    }
    public void setLoginInfo(ILoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public void setLocation(IAppLocation location) {
        this.location = location;
    }

    public IAppLocation getLocation() {
        if (location == null) {
            location = new AppLocationIml();
        }
        return location;
    }
}
