package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/12/16.
 * 功能: 通话管理器
 */

public interface ICallManager {
    /**
     *  是否正在通话中
     * @return
     */
    public boolean isCalling();

    /**
     *    是否电话占用通道:
     *    正在通话中、振铃、呼叫中
     * @return
     */
    public boolean isCallOnAudioChannel();

    /**
     *   是否电话占用通道:
     * @param isCallOnAudioChannel
     */
    public void setCallOnAudioChannel(boolean isCallOnAudioChannel);

    /**
     * 电话类型
     * @return
     */
    public CallType getCallType();
    /**
     * set电话类型
     * @param callType
     */
    public void setCallType(CallType callType);

    /**
     * get通话状态
     * @return
     */
    public CallState getCallState();

    /**
     * set通话状态
     * @param callState
     */
    public void setCallState(CallState callState);


    /**
     * Created by yinxm on 2016/12/16.
     * 功能: 电话枚举
     */
    enum CallType {
        TELEPHONE,//传统电话
        M800,//M800电话
        OTHER;//other
    }

    /**
     * Created by yinxm on 2016/12/16.
     * 功能: 通话状态
     */

    enum CallState {
        TO_CALL,//正在呼叫
        RINGING,//振铃
        CALLING,//正在通话中
        NOT_CALL;//未通话
    }
}
