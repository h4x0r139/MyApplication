package com.ecarx.bt.service;

import android.Manifest;
import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;


import cn.yinxm.lib.utils.log.LogUtil;

/**
 * 操作联系人数据
 */
public class ModifyDatabaseService extends IntentService {
    public static final String ACTION_CONTACTS_UPDATE = "action_contacts_UPDATE";

    private final static String TAG = ModifyDatabaseService.class.getSimpleName();
    private static final String ACTION_CLEAR = "ecarx.contacts.service.action.CLEAR";
    private static final String ACTION_CLEAR_UNREAD_CALL_LOG = "ecarx.contacts.service.action.CLEAR_UNREAD_CALL_LOG";

    public ModifyDatabaseService() {
        super(TAG);
    }

    /**
     * 清空数据
     */
    public static void startActionClear(Context context) {
       LogUtil.i(TAG, "startActionClear -- ");
        Intent intent = new Intent(context, ModifyDatabaseService.class);
        intent.setAction(ACTION_CLEAR);
        context.startService(intent);
    }

    /**
     * 修改未读通话记录
     */
    public static void clearUnreadCallLog(Context context) {
       LogUtil.i(TAG, "startActionClear -- ");
        Intent intent = new Intent(context, ModifyDatabaseService.class);
        intent.setAction(ACTION_CLEAR_UNREAD_CALL_LOG);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
           LogUtil.i(TAG, "action : " + action);
            if (ACTION_CLEAR.equals(action)) {
                deleteAllContacts();
            } else if (ACTION_CLEAR_UNREAD_CALL_LOG.equals(action)) {
                clearMissCallLogs();
            }
        }
    }

    /**
     * 清空联系人和通话记录
     */
    private void deleteAllContacts() {
       LogUtil.i(TAG, "start deleteAllContacts");
        int size = getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, null, null);
       LogUtil.d(TAG, "deleteAllContacts finish ,total delete " + size);
        sendBroadcast(new Intent(ACTION_CONTACTS_UPDATE));
       LogUtil.i(TAG, "start deleteAllCallLogs");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        int callLogSize = getContentResolver().delete(CallLog.Calls.CONTENT_URI, null, null);
       LogUtil.d(TAG, "deleteAllCallLogs finish ,total delete " + callLogSize);
    }

    /**
     * 清空联系人和通话记录
     */
    private void clearMissCallLogs() {
       LogUtil.i(TAG, "start updateMissCallLogs");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NEW, 0); // 0已看 1未看
        int updates = getContentResolver().update(CallLog.Calls.CONTENT_URI, values, CallLog.Calls.NEW + "=1", null);
       LogUtil.d(TAG, "updateMissCallLogs finish ,total update " + updates);
    }
}
