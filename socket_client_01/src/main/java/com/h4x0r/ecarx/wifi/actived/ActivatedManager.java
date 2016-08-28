package com.h4x0r.ecarx.wifi.actived;

/**
 * 激活管理器
 * Created by yinxm on 2016/7/6.
 */
public class ActivatedManager {

    private static ActivatedStatu mActivatedStatu = ActivatedStatu.WELCOME;

    public enum ActivatedStatu {
        WELCOME,BIND_DEVICE, BIND_SIMCARD, ACTIVATE_SUCCESS;
    }

    /**
     * 获取当前激活状态
     *
     * @return
     */
    public static ActivatedStatu getCurrentActivatedState() {
        return mActivatedStatu;
    }

    public static void updateActivatedStatu(ActivatedStatu mActivatedStatu) {
        ActivatedManager.mActivatedStatu = mActivatedStatu;
    }

}
