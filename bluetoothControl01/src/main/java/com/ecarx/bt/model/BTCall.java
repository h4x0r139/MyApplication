package com.ecarx.bt.model;

import android.os.SystemClock;

import com.ecarx.bt.BTPhoneApp;

import cn.yinxm.lib.utils.log.LogUtil;


public class BTCall {

    private static final String TAG = BTCall.class.getSimpleName();

    public boolean isMissed = true;
    private BTPhoneApp.State state = BTPhoneApp.State.IDLE;

    private String number = "";

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long connectTimeReal;

    private long duration;

    private long connectTime;

    private boolean disconnected;

    private long disconnectTime;

    private boolean isIncoming;

    public boolean isIncoming() {
        return isIncoming;
    }

    public BTPhoneApp.State getState() {
        return state;
    }

    public boolean update(BTPhoneApp.State newState) {
        LogUtil.d(TAG, " new state = " + newState + ", current state = " + state);
        // State newState;
        boolean changed = false;
        boolean wasConnectingInOrOut = isConnectingInOrOut();
        boolean wasDisconnected = isDisconnected();

        if (newState == BTPhoneApp.State.INCOMING) {
            isIncoming = true;
        }
        if (state == BTPhoneApp.State.IDLE && newState == BTPhoneApp.State.ACTIVE) {
            onConnectedInOrOut();
        }

        if (newState != state) {
            state = newState;
            changed = true;
            if (newState == BTPhoneApp.State.ACTIVE) {
                isMissed = false;
            }
        }
        if (wasConnectingInOrOut && !isConnectingInOrOut()) {
            onConnectedInOrOut();
        }
        if (!wasDisconnected && isDisconnected()) {
            onDisconnect();
        }

        return changed;
    }

    private void clearDisconnected() {
        state = BTPhoneApp.State.IDLE;
    }

    public long getDurationMillis() {
        if (connectTimeReal == 0) {
            return 0;
        } else if (duration == 0) {
            return SystemClock.elapsedRealtime() - connectTimeReal;
        } else {
            return duration;
        }
    }

    /**
     * An incoming or outgoing call has connected
     */
    private void onConnectedInOrOut() {
        connectTime = System.currentTimeMillis();
        connectTimeReal = SystemClock.elapsedRealtime();
        duration = 0;
        LogUtil.d(TAG, "onConnectedInOrOut: connectTime=" + connectTime);
    }

    private void onDisconnect() {
        if (!disconnected) {
            disconnectTime = System.currentTimeMillis();
            duration = SystemClock.elapsedRealtime() - connectTimeReal;
            disconnected = true;
            LogUtil.d(TAG, "[BTCall] onDisconnect()... disconnectTime = " + disconnectTime);
        }
    }

    /**
     * "connecting" means "has never been ACTIVE" for both incoming and outgoing
     * calls
     */
    private boolean isConnectingInOrOut() {
        return state == BTPhoneApp.State.INCOMING || state == BTPhoneApp.State.OUTGOING;
    }

    private boolean isDisconnected() {
        return state == BTPhoneApp.State.IDLE;
    }

    public void clearData() {
        LogUtil.d(TAG, "[BTCall] clearData()... ");
        this.clearDisconnected();
        setName("");
        setNumber("");
        this.connectTimeReal = 0;
        this.duration = 0;
        this.connectTime = 0;
        this.disconnectTime = 0;
        this.isIncoming = false;
        this.isMissed = true;
    }
}
