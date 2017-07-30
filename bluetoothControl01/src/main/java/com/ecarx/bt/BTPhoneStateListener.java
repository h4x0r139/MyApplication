package com.ecarx.bt;

public interface BTPhoneStateListener {
    void inComingCall(String number);

    void onGoingCall(String number);

    void outGoingCall(String number);

    void hangupCall();

    void onProfileStateChange(int state);

    void onPhoneAudioStateChange(boolean isAudioFromPhone);
}
