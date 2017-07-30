package com.ecarx.bt;

import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.ecarx.bt.model.BTCall;

import java.util.List;
import java.util.Vector;

import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.log.LogUtil;
import ecarx.app.CarSignalManager;
import ecarx.app.PhoneStateHelper;
import ecarx.app.SWCStateHelper;
import ecarx.bluetooth.BluetoothAdapterCreateHelper;
import ecarx.bluetooth.ECarXBluetoothCarHeadset;
import ecarx.vehicleconfig.DispinfoSettingStatus;
import ecarx.vehicleconfig.VehicleConfigManager;

public class BTPhoneManager {
    private final static String TAG = BTPhoneManager.class.getSimpleName();

    public BTPhoneApp.State state = BTPhoneApp.State.IDLE;

    private Context mContext;
    private List<BTPhoneStateListener> mCallbackList = new Vector<>();

    private ECarXBluetoothCarHeadset profile;

    private boolean isAudioFromPhone;
    private BTCall foregroundCall = null;

    private VehicleConfigManager mVehicleCfgMg = null;

    private String callNumber;
    private List<String> numberList = new Vector<>();

    public BTPhoneManager(Context context) {
        LogUtil.d(TAG, "HeadSetProfileManager...");
        this.mContext = context;

        mVehicleCfgMg = VehicleConfigManager.get(context);

        BluetoothAdapterCreateHelper.getDefaultAdapter(context, new BluetoothAdapterCreateHelper.AdapterListener() {
            @Override
            public void onAdapterCreate() {
                LogUtil.d(TAG, "callback in onAdapterCreate ...");
                profile = ECarXBluetoothCarHeadset.getInstance(mContext);
                profile.registerEventListener(getHeadsetEventListener());
            }
        });

        foregroundCall = new BTCall();

        if (mVehicleCfgMg == null) {
            LogUtil.d(TAG, "mVehicleCfgMg is null");
        }
        // Set ECHO mode false;
        CommonUtils.setECHOMode(context, false);
    }

    public void addPhoneStateListener(BTPhoneStateListener callback) {
        LogUtil.d(TAG, "addPhoneStateListener...callback = " + callback.toString());
        if (!mCallbackList.contains(callback)) {
            mCallbackList.add(callback);
        }
    }

    public void removePhoneStateListener(BTPhoneStateListener callback) {
        LogUtil.d(TAG, "removePhoneStateListener...callback = " + callback.toString());
        if (mCallbackList.contains(callback)) {
            mCallbackList.remove(callback);
        }
    }

    private ECarXBluetoothCarHeadset.HeadsetEventListener getHeadsetEventListener() {
        return new ECarXBluetoothCarHeadset.HeadsetEventListener() {
            @Override
            public void audioEstablished() {
                LogUtil.d(TAG, " audioEstablished ----> isAVNOFF = " + isAVNOff());
                if (isAVNOff() || PhoneStateHelper.getXCallPhoneState() != PhoneStateHelper.STATE_IDLE) {
                    disConnectAudio();
                } else {
                    isAudioFromPhone = false;
                    for (BTPhoneStateListener callback : mCallbackList) {
                        callback.onPhoneAudioStateChange(false);
                    }
                }
            }

            @Override
            public void audioReleased() {
                LogUtil.d(TAG, "----> audioReleased");
                isAudioFromPhone = true;
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.onPhoneAudioStateChange(true);
                }
            }

            @Override
            public void inComingCall(String number) {
                LogUtil.d(TAG, "----> inComingCall---> number = " + number);

                if (!numberList.contains(number)) {
                    numberList.add(number);
                }

                foregroundCall.clearData();
                foregroundCall.setNumber(number);
                foregroundCall.update(BTPhoneApp.State.INCOMING);
                LogUtil.d(TAG, "----> isAVNOFF = " + isAVNOff());
                // Add FF: AVN OFF下不响应来电
                if (isAVNOff()) {
                    return;
                }

                BTPhoneApp.mIsComingCall = true;
                if (state == BTPhoneApp.State.IDLE) {
                    state = BTPhoneApp.State.INCOMING;
                    launchPhoneScreen(state);
                } else {
                    state = BTPhoneApp.State.INCOMING;
                }
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.inComingCall(number);
                }
                callNumber = number;
                //save status
                CommonUtils.saveIdle(mContext, false);

                boolean ret = SWCStateHelper.setSWCState(SWCStateHelper.SWC_STATE_AVN);
                LogUtil.d(TAG, "SWCState == " + ret);
                if (mVehicleCfgMg == null) {
                    LogUtil.d(TAG, "----inComingCall----mVehicleCfgMg is null");
                    mVehicleCfgMg = VehicleConfigManager.get(mContext);
                }
                if (mVehicleCfgMg != null) {
                    mVehicleCfgMg.setDispinfoSettingStatus(DispinfoSettingStatus.CMD_ID_PHONE_STATUS, DispinfoSettingStatus.PHONE_STATUS_INCOMMING_CALL);
                } else {
                    LogUtil.d(TAG, "mVehicleCfgMg is null");
                }
            }

            @Override
            public void outGoingCall(String number) {
                if (!numberList.contains(number)) {
                    numberList.add(number);
                }

                LogUtil.d(TAG, "----> outGoingCall --->" + number);
                foregroundCall.clearData();
                foregroundCall.setNumber(number);
                foregroundCall.update(BTPhoneApp.State.OUTGOING);
                if (state == BTPhoneApp.State.IDLE) {
                    state = BTPhoneApp.State.OUTGOING;
                    launchPhoneScreen(state);
                } else {
                    state = BTPhoneApp.State.OUTGOING;
                }
                BTPhoneApp.mIsComingCall = false;
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.outGoingCall(number);
                }
                callNumber = number;
                // save status
                CommonUtils.saveIdle(mContext, false);
                boolean ret = SWCStateHelper.setSWCState(SWCStateHelper.SWC_STATE_AVN);
                LogUtil.d(TAG, "SWCState == " + ret);
                if (mVehicleCfgMg == null) {
                    LogUtil.d(TAG, "----outGoingCall----mVehicleCfgMg is null");
                    mVehicleCfgMg = VehicleConfigManager.get(mContext);
                }
                if (mVehicleCfgMg != null) {
                    mVehicleCfgMg.setDispinfoSettingStatus(DispinfoSettingStatus.CMD_ID_PHONE_STATUS, DispinfoSettingStatus.PHONE_STATUS_OUTGOING_CALL);
                } else {
                    LogUtil.d(TAG, "mVehicleCfgMg is null");
                }
            }

            @Override
            public void onGoingCall(String number) {
                LogUtil.d(TAG, "----> onGoingCall------->number = " + number + " callNumber " + callNumber);

                //如果号码不同清空数据,并重新查询
                if (!number.equals(callNumber)) {
                    foregroundCall.clearData();
                }
                foregroundCall.setNumber(number);
                foregroundCall.update(BTPhoneApp.State.ACTIVE);
                if (state == BTPhoneApp.State.IDLE) {
                    state = BTPhoneApp.State.ACTIVE;
                    launchPhoneScreen(state);
                } else {
                    state = BTPhoneApp.State.ACTIVE;
                }

                if (isAVNOff()) {
                    disConnectAudio();
                }
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.onGoingCall(number);
                }
                // 号码不同重新查询
                if (!number.equals(callNumber)) {
                    callNumber = number;
                }

                BTPhoneApp.mIsComingCall = false;
                // save status
                CommonUtils.saveIdle(mContext, false);
                boolean ret = SWCStateHelper.setSWCState(SWCStateHelper.SWC_STATE_AVN);
                LogUtil.d(TAG, "SWCState == " + ret);
                if (mVehicleCfgMg == null) {
                    LogUtil.d(TAG, "----onGoingCall----mVehicleCfgMg is null");
                    mVehicleCfgMg = VehicleConfigManager.get(mContext);
                }
                if (mVehicleCfgMg != null) {
                    mVehicleCfgMg.setDispinfoSettingStatus(DispinfoSettingStatus.CMD_ID_PHONE_STATUS, DispinfoSettingStatus.PHONE_STATUS_BUSY);
                } else {
                    LogUtil.d(TAG, "mVehicleCfgMg is null");
                }
            }

            @Override
            public void serviceEstablished() {
                LogUtil.d(TAG, "----> serviceEstablished");
            }

            @Override
            public void serviceReleased() {
                LogUtil.d(TAG, "----> serviceReleased");
            }

            @Override
            public void hangup(String number) {
                if (numberList.contains(number)) {
                    numberList.remove(number);
                }

                if (!numberList.isEmpty()) {
                    return;
                }

                LogUtil.d(TAG, "----> hangup" + "------->");
                state = BTPhoneApp.State.IDLE;
                foregroundCall.update(BTPhoneApp.State.IDLE);
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.hangupCall();
                }
                isAudioFromPhone = true;
                BTPhoneApp.mIsComingCall = false;
                // save status
                CommonUtils.saveIdle(mContext, true);
                boolean ret = SWCStateHelper.setSWCState(SWCStateHelper.SWC_STATE_INVALID);
                LogUtil.d(TAG, "SWCState == " + ret);
                if (mVehicleCfgMg == null) {
                    LogUtil.d(TAG, "----hangup----mVehicleCfgMg is null");
                    mVehicleCfgMg = VehicleConfigManager.get(mContext);
                }
                if (mVehicleCfgMg != null) {
                    mVehicleCfgMg.setDispinfoSettingStatus(DispinfoSettingStatus.CMD_ID_PHONE_STATUS, DispinfoSettingStatus.PHONE_STATUS_NO_CALL);
                } else {
                    LogUtil.d(TAG, "mVehicleCfgMg is null");
                }
            }

            @Override
            public void ringFromPhone(boolean isFromPhone) {
                LogUtil.d(TAG, "---->ringFromPhone ----->" + isFromPhone);
                // save status
                CommonUtils.saveIdle(mContext, false);
            }

            @Override
            public void onProfileStateChange(int proFileState) {
                LogUtil.d(TAG, "----> onProfileStateChange + state --->" + proFileState);
                if (proFileState == BluetoothProfile.STATE_DISCONNECTED
                        || proFileState == BluetoothProfile.STATE_DISCONNECTING) {
                    if (state == BTPhoneApp.State.IDLE) {
                        LogUtil.d(TAG, "----> onProfileStateChange + current state --->" + state.toString());
                        return;
                    }
                    boolean ret = SWCStateHelper.setSWCState(SWCStateHelper.SWC_STATE_INVALID);
                    LogUtil.d(TAG, "SWCState == " + ret);
                    if (mVehicleCfgMg == null) {
                        mVehicleCfgMg = VehicleConfigManager.get(mContext);
                    }
                    if (mVehicleCfgMg != null) {
                        mVehicleCfgMg.setDispinfoSettingStatus(DispinfoSettingStatus.CMD_ID_PHONE_STATUS, DispinfoSettingStatus.PHONE_STATUS_NO_CALL);
                    } else {
                        LogUtil.d(TAG, "mVehicleCfgMg is null");
                    }
                    state = BTPhoneApp.State.IDLE;
                    foregroundCall.update(BTPhoneApp.State.IDLE);
                    for (BTPhoneStateListener callback : mCallbackList) {
                        callback.hangupCall();
                    }
                    // save status
                    CommonUtils.saveIdle(mContext, true);
                }
                for (BTPhoneStateListener callback : mCallbackList) {
                    callback.onProfileStateChange(proFileState);
                }
                // save status
                CommonUtils.saveIdle(mContext, state == BTPhoneApp.State.IDLE);
            }
        };
    }

    public void launchPhoneScreen(BTPhoneApp.State state) {
        LogUtil.d(TAG, "state " + state);
//        if (isWeChatVoice()) return;
//        Intent intent = new Intent(mContext, CallingActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        mContext.startActivity(intent);
    }

    public boolean isWeChatVoice() {
        // FIXME: 17/2/15 iOS微信发送语音会触发来电，友商建议屏蔽本机号码相关逻辑。
        // FIXME: 17/3/2 Android微信发送语音，电话号码为空。by 张立来
        if (profile != null && foregroundCall != null) {
            final String phone = foregroundCall.getNumber();
            final String myPhone = profile.getSubscriberNumber();
            if (!StringUtil.isBlank(phone) && !StringUtil.isBlank(myPhone) && phone.endsWith(myPhone)) {
                LogUtil.d(TAG, phone + " iOS微信发送语音 " + myPhone);
                return true;
            }
            if (StringUtil.isBlank(phone)) {
                LogUtil.d(TAG, phone + " Android微信发送语音 " + myPhone);
                return true;
            }
        }
        return false;
    }

    /**
     * 拨打电话
     */
    public int dial(String number) {
        if (profile != null) {
            profile.dial(number);
            return 0;
        }
        return -1;
    }

    /**
     * 接听电话
     */
    public int answerCall() {
        if (profile != null) {
            profile.answerCall();
            return 0;
        }
        return -1;
    }

    public int hangup() {
        if (profile != null) {
            profile.hangup();
            return 0;
        }
        return -1;
    }

    /**
     * isAudioOn
     *
     * @return audio
     */
    public boolean isAudioOn() {
        return profile != null && profile.isAudioOn();
    }

    public void disConnectAudio() {
        LogUtil.d(TAG, "----disConnectAudio----");
        if (profile != null) {
            profile.disConnectAudio();
        }
    }

    public void connectAudio() {
        LogUtil.d(TAG, "----connectAudio----");
        if (profile != null) {
            profile.connectAudio();
        }
    }

    public int transferDTMF(char c) {
        LogUtil.d(TAG, "c " + String.valueOf(c));
        if (profile != null) {
            profile.transferDTMF(c);
            return 0;
        }
        return -1;
    }

    public boolean isConnect() {
        return profile != null && profile.isConnected();
    }

    public BTCall getForegroundCall() {
        return foregroundCall;
    }

    public BTPhoneApp.State getState() {
        return state;
    }

    public boolean isAudioFromPhone() {
        LogUtil.d(TAG, "" + isAudioFromPhone);
        return isAudioFromPhone;
    }

    public String[] getContactData(String number) {
        String[] numberAndName = new String[2];
//        if ("+".equals(number)) {
//            numberAndName[0] = "";
//            numberAndName[1] = BTPhoneApp.getInstance().getApplicationContext().getString(R.string.private_num);
//            return numberAndName;
//        }
//        SearchResult contact = BTPhoneApp.getInstance().getDBManager().findContactByPhone(number);
//        if (contact != null) {
//            numberAndName[0] = number;
//            numberAndName[1] = contact.name;
//        } else {
//            numberAndName[0] = number;
//            numberAndName[1] = "";
//        }
//        LogUtil.d(TAG, numberAndName[0] + "  " + numberAndName[1]);
        return numberAndName;
    }

    public boolean isAVNOff() {
        LogUtil.d(TAG, "---isAVNOff --- ");
        if (BTPhoneApp.getInstance().getService() != null) {
            LogUtil.d(TAG, "---AVNStatus = " + BTPhoneApp.getInstance().getService().getAVNStatus());
            return BTPhoneApp.getInstance().getService().getAVNStatus() == CarSignalManager.STATE_AVN_OFF || BTPhoneApp
                    .getInstance().getService().getAVNStatus() == CarSignalManager.STATE_AVN_CRANK;
        } else {
            return false;
        }
    }
}
