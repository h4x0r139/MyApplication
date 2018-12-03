package cn.yinxm.lib.media.player;

/**
 * Created by yinxm on 2017/1/12.
 * 功能: 播放完毕回调
 */

public interface OnPlayFinishCallback {
    public static final int CODE_SUCCESS = 1;//操作成功
    public static final int CODE_FAIL = 2;//操作失败
    /**
     * @param code CODE_SUCCESS，CODE_FAIL
     */
    public void onPlayfinish(int code);
}
