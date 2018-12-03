package cn.yinxm.lib.interfaces.iml;

import java.io.Serializable;

import cn.yinxm.lib.interfaces.ICallManager;
import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2016/12/16.
 * 功能:
 */

public class CallManagerIml implements ICallManager, Serializable {
    private CallType mCallType;
    private CallState mCallState;
    private boolean isCallOnAudioChannel;


    @Override
    public boolean isCalling() {
        boolean flag = false;
        if (isCallOnAudioChannel && CallState.CALLING == mCallState) {
            flag = true;
        }
        LogUtil.d("isCalling="+flag+", isCallOnAudioChannel="+isCallOnAudioChannel+", mCallState="+mCallState+", mCallType="+mCallType);
        return flag;
    }

    @Override
    public boolean isCallOnAudioChannel() {
        return this.isCallOnAudioChannel;
    }

    @Override
    public void setCallOnAudioChannel(boolean isCallOnAudioChannel) {
        this.isCallOnAudioChannel = isCallOnAudioChannel;
    }

    @Override
    public CallType getCallType() {
        return this.mCallType;
    }

    @Override
    public void setCallType(CallType callType) {
        this.mCallType = callType;
    }

    @Override
    public CallState getCallState() {
        return this.mCallState;
    }

    @Override
    public void setCallState(CallState callStateTemp) {
        LogUtil.d("mCallState="+mCallState+", callStateTemp="+callStateTemp);
        if (callStateTemp != null && callStateTemp != mCallState) {
            if (CallState.NOT_CALL == callStateTemp) {
                LogUtil.d("remove CallOnAudioChannel");
                setCallOnAudioChannel(false);
            }else {
                setCallOnAudioChannel(true);
            }
            this.mCallState = callStateTemp;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallManagerIml{");
        sb.append("mCallType=").append(mCallType);
        sb.append(", mCallState=").append(mCallState);
        sb.append(", isCallOnAudioChannel=").append(isCallOnAudioChannel);
        sb.append('}');
        return sb.toString();
    }
}
