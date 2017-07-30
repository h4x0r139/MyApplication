package com.yinxm.bt;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ecarx.bluetooth.BluetoothAdapter;
import ecarx.bluetooth.BluetoothDevice;
import ecarx.bluetooth.BluetoothHeadset;
import ecarx.bluetooth.BluetoothProfile;
import ecarx.bluetooth.HfpClientCall;

/**
 * 功能：模仿ECarXBluetoothCarHeadset 监听蓝牙电话状态
 * Created by yinxm on 2017/7/30.
 */

public class BluetoothCarHeadSet {
    private static final String TAG = BluetoothCarHeadSet.class.getSimpleName();
    private final Context mContext;
    private BluetoothHeadset mService;
    private BluetoothDevice mDevice;
    private boolean isProfileReady = false;
    private static BluetoothCarHeadSet instance;
    private List<HeadsetEventListener> mHeadsetEventListeners = new ArrayList();
    private BluetoothProfile.ServiceListener listener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Log.d(TAG, "onServiceConnected...i="+i+", bluetoothProfile="+bluetoothProfile);

            BluetoothCarHeadSet.this.mService = (BluetoothHeadset)bluetoothProfile;
            BluetoothCarHeadSet.this.isProfileReady = true;
            BluetoothCarHeadSet.this.mService.registerEventListener(BluetoothCarHeadSet.this.mEventListener);
            BluetoothCarHeadSet.this.notifyHFPProfile(2);
        }

        public void onServiceDisconnected(int i) {
            Log.d(TAG, "onServiceDisconnected...");
            BluetoothCarHeadSet.this.isProfileReady = false;
            BluetoothCarHeadSet.this.notifyHFPProfile(0);
            if(null != BluetoothCarHeadSet.this.mService) {
                BluetoothCarHeadSet.this.mService.unregisterEventListener(BluetoothCarHeadSet.this.mEventListener);
            }

            BluetoothCarHeadSet.this.mService = null;
        }
    };
    BluetoothHeadset.EventListener mEventListener = new BluetoothHeadset.EventListener() {
        public void onHfpEvent(Event event, int i, String s) {
            Log.d(TAG, "event --->" + event + " i ---> [" + i + "] , s---> " + s);
            int ordinal = event.ordinal();
            Log.d(TAG, "ordinal=" +ordinal);
//            switch(BluetoothCarHeadSet.SyntheticClass_1.$SwitchMap$ecarx$bluetooth$BluetoothHeadset$EventListener$Event[event.ordinal()]) {
            switch(ordinal) {
                case 1:
                    BluetoothCarHeadSet.this.audioEstablished();
                    break;
                case 2:
                    BluetoothCarHeadSet.this.audioReleased();
                    break;
                case 3:
                    BluetoothCarHeadSet.this.inComingCall(s);
                    break;
                case 4:
                    BluetoothCarHeadSet.this.onGoingCall(s);
                    break;
                case 5:
                    BluetoothCarHeadSet.this.outGoingCall(s);
                case 6:
                default:
                    break;
                case 7:
                    BluetoothCarHeadSet.this.serviceEstablished();
                    break;
                case 8:
                    BluetoothCarHeadSet.this.serviceReleased();
                    BluetoothCarHeadSet.this.notifyHFPProfile(0);
                    break;
                case 9:
                    BluetoothCarHeadSet.this.standby(s);
                    break;
                case 10:
                    BluetoothCarHeadSet.this.ringFromPhone(i);
            }

        }
    };

    private BluetoothCarHeadSet(Context context) {
        this.mContext = context;
        this.bindHeadset();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    private void bindHeadset() {
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(this.mContext, this.listener, 1);
    }

    public static BluetoothCarHeadSet getInstance(Context context) {
        if(instance == null) {
            Class var1 = BluetoothCarHeadSet.class;
            synchronized(BluetoothCarHeadSet.class) {
                if(instance == null) {
                    instance = new BluetoothCarHeadSet(context);
                }
            }
        }

        return instance;
    }

    public boolean connectHeadset(BluetoothDevice device) {
        if(this.mService == null) {
            return false;
        } else {
            this.mDevice = device;
            List sinks = this.mService.getConnectedDevices();
            if(sinks != null) {
                Iterator i$ = sinks.iterator();

                while(i$.hasNext()) {
                    BluetoothDevice sink = (BluetoothDevice)i$.next();
                    this.mService.disconnect(sink);
                }
            }

            return this.mService.connect(device);
        }
    }

    public boolean disconnectHeadset(BluetoothDevice device) {
        if(this.mService == null) {
            return false;
        } else {
            this.mDevice = null;
            List deviceList = this.mService.getConnectedDevices();
            if(!deviceList.isEmpty() && ((BluetoothDevice)deviceList.get(0)).equals(device)) {
                if(this.mService.getPriority(device) > 100) {
                    this.mService.setPriority(device, 100);
                }

                return this.mService.disconnect(device);
            } else {
                return false;
            }
        }
    }

    public BluetoothDevice getCurrentHeadset() {
        return this.mDevice;
    }

    public int getPriority(BluetoothDevice device) {
        return this.mService.getPriority(device);
    }

    public void setPriority(BluetoothDevice device, int priority) {
        this.mService.setPriority(device, priority);
    }

    public void setCurrentHeadset(BluetoothDevice device) {
        this.mDevice = device;
    }

    public boolean isConnected(BluetoothDevice device) {
        return BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) == 2;
    }

    public int getConnectionStatus(BluetoothDevice device) {
        return this.mService == null?0:this.mService.getConnectionState(device);
    }

    public boolean isProfileReady() {
        return this.isProfileReady;
    }

    public boolean isConnected() {
        return this.getProfileConnectionState() == 2;
    }

    public int getProfileConnectionState() {
        return BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1);
    }

    public boolean isAudioOn() {
        return this.mService != null?this.mService.isAudioOn():false;
    }

    public void registerEventListener(BluetoothCarHeadSet.HeadsetEventListener listener) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            if(!this.mHeadsetEventListeners.contains(listener)) {
                this.mHeadsetEventListeners.add(listener);
            }

        }
    }

    public void unRegisterEventListener(BluetoothCarHeadSet.HeadsetEventListener listener) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            this.mHeadsetEventListeners.remove(listener);
        }
    }

    private void audioEstablished() {
        List var1 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.audioEstablished();
            }

        }
    }

    private void audioReleased() {
        List var1 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.audioReleased();
            }

        }
    }

    private void inComingCall(String number) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.inComingCall(number);
            }

        }
    }

    private void onGoingCall(String number) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.onGoingCall(number);
            }

        }
    }

    private void serviceEstablished() {
        List var1 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.serviceEstablished();
            }

        }
    }

    private void outGoingCall(String number) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.outGoingCall(number);
            }

        }
    }

    private void serviceReleased() {
        List var1 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.serviceReleased();
            }

        }
    }

    private void notifyHFPProfile(int state) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.onProfileStateChange(state);
            }

        }
    }

    private void standby(String s) {
        List var2 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.hangup(s);
            }

        }
    }

    private void ringFromPhone(int fromPhone) {
        boolean isFromPhone = false;
        if(fromPhone == 0) {
            isFromPhone = true;
        } else if(fromPhone == 1) {
            isFromPhone = false;
        }

        List var3 = this.mHeadsetEventListeners;
        synchronized(this.mHeadsetEventListeners) {
            Iterator i$ = this.mHeadsetEventListeners.iterator();

            while(i$.hasNext()) {
                BluetoothCarHeadSet.HeadsetEventListener mHeadsetEventListener = (BluetoothCarHeadSet.HeadsetEventListener)i$.next();
                mHeadsetEventListener.ringFromPhone(isFromPhone);
            }

        }
    }

    public boolean answerCall() {
        if(null != this.mService) {
            boolean flag = this.mService.answerCall();
            Log.d(TAG, " answerCall result : " + flag);
            return flag;
        } else {
            Log.d(TAG, " answerCall error, mService is null");
            return false;
        }
    }

    public boolean hangup() {
        if(null != this.mService) {
            boolean flag = this.mService.cancelCall();
            Log.d(TAG, " cancelCall result : " + flag);
            return flag;
        } else {
            Log.d(TAG, " hangup error, mService is null");
            return false;
        }
    }

    public boolean dial(String number) {
        if(null != this.mService) {
            boolean flag = this.mService.dial(number);
            Log.d(TAG, " dial result : " + flag);
            return flag;
        } else {
            Log.d(TAG, " dial error : " + number + " , mService is null");
            return false;
        }
    }

    public List<HfpClientCall> getHfpCallList() {
        if(null != this.mService) {
            return this.mService.getHfpCallList();
        } else {
            Log.d(TAG, " getHfpCallList error mService is null");
            return null;
        }
    }

    public boolean transferDTMF(char code) {
        boolean flag = false;
        if(null != this.mService) {
            flag = this.mService.transferDTMF(code);
            Log.d(TAG, " transferDTMF result : " + flag);
        } else {
            Log.d(TAG, " transferDTMF error, mService is null");
        }

        return flag;
    }

    public void acceptIncomingConnect(BluetoothDevice device) {
        if(null != this.mService) {
            boolean flag = this.mService.acceptIncomingConnect(device);
            Log.d(TAG, " acceptIncomingConnect result : " + flag);
        } else {
            Log.d(TAG, " transferDTMF error, mService is null");
        }

    }

    public boolean connectAudio() {
        boolean flag = false;
        if(null != this.mService) {
            flag = this.mService.connectAudio();
            Log.d(TAG, " connectAudio result : " + flag);
        } else {
            Log.d(TAG, " connectAudio error, mService is null");
        }

        return flag;
    }

    public boolean disConnectAudio() {
        boolean flag = false;
        if(null != this.mService) {
            flag = this.mService.disconnectAudio();
            Log.d(TAG, " disconnectAudio result : " + flag);
        } else {
            Log.d(TAG, " disconnectAudio error, mService is null");
        }

        return flag;
    }

    public boolean phoneMicMuteCtrl(boolean mute) {
        boolean flag = false;
        if(null != this.mService) {
            flag = this.mService.phoneMicMuteCtrl(mute);
            Log.d(TAG, " phoneMicMuteCtrl result : " + flag);
        } else {
            Log.d(TAG, " phoneMicMuteCtrl error, mService is null");
        }

        return flag;
    }

    public boolean getMicMuteState() {
        boolean flag = false;
        if(null != this.mService) {
            flag = this.mService.getMicMuteState();
            Log.d(TAG, " getMicMuteState result : " + flag);
        } else {
            Log.d(TAG, " getMicMuteState error, mService is null");
        }

        return flag;
    }

    public void roamChanged(boolean isroaming) {
        if(null != this.mService) {
            this.mService.roamChanged(isroaming);
        } else {
            Log.d(TAG, " roamChanged error, mService is null");
        }

    }

    public String getSubscriberNumber() {
        if(null != this.mService) {
            return this.mService.getSubscriberNumber();
        } else {
            Log.d(TAG, "getSubscriberNumber error, mService is null");
            return null;
        }
    }

    public void phoneStateChanged(int numActive, int numHeld, int callState, String number, int type) {
        if(null != this.mService) {
            this.mService.phoneStateChanged(numActive, numHeld, callState, number, type);
        } else {
            Log.d(TAG, " mService error, mService is null");
        }

    }

    public boolean startVoiceRecognition() {
        if(this.mService != null && this.mDevice != null) {
            boolean flag = this.mService.startVoiceRecognition(this.mDevice);
            Log.d(TAG, " startVoiceRecognition result : " + flag);
        } else {
            Log.d(TAG, " startVoiceRecognition error, mService is null");
        }

        return false;
    }

    public boolean stopVoiceRecognition() {
        if(this.mService != null && this.mDevice != null) {
            boolean flag = this.mService.stopVoiceRecognition(this.mDevice);
            Log.d(TAG, " stopVoiceRecognition result : " + flag);
        } else {
            Log.d(TAG, " stopVoiceRecognition error, mService is null");
        }

        return false;
    }

    public void startScoUsingVirtualVoiceCall() {
        if(this.mService != null && this.mDevice != null) {
            boolean flag = this.mService.startScoUsingVirtualVoiceCall(this.mDevice);
            Log.d(TAG, " startScoUsingVirtualVoiceCall result : " + flag);
        } else {
            Log.d(TAG, " startScoUsingVirtualVoiceCall error, mService is null");
        }

    }

    public void stopScoUsingVirtualVoiceCall() {
        if(this.mService != null && this.mDevice != null) {
            boolean flag = this.mService.stopScoUsingVirtualVoiceCall(this.mDevice);
            Log.d(TAG, " stopScoUsingVirtualVoiceCall result : " + flag);
        } else {
            Log.d(TAG, " stopScoUsingVirtualVoiceCall error, mService is null");
        }

    }

    public interface HeadsetEventListener {
        void audioEstablished();

        void audioReleased();

        void inComingCall(String var1);

        void outGoingCall(String var1);

        void onGoingCall(String var1);

        void serviceEstablished();

        void serviceReleased();

        void hangup(String var1);

        void ringFromPhone(boolean var1);

        void onProfileStateChange(int var1);
    }
}