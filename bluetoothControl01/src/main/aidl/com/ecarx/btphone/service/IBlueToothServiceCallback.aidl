// IBlueToothServiceCallback.aidl
package com.ecarx.btphone.service;

// Declare any non-default types here with import statements

interface IBlueToothServiceCallback {
    void startSync();

    void endSync();

    void failSync();
}
