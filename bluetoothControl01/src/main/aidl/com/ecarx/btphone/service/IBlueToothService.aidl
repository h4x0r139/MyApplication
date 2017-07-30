// IBlueToothService.aidl
package com.ecarx.btphone.service;

import com.ecarx.btphone.service.IBlueToothServiceCallback;

interface IBlueToothService {
    // 是否正在同步
    boolean isSyncing();
    // 手动同步
    boolean startSync();

    void registerCallback(IBlueToothServiceCallback cb);

    void unregisterCallback(IBlueToothServiceCallback cb);
}
