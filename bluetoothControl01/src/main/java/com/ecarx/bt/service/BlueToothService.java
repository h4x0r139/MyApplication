package com.ecarx.bt.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;

import com.ecarx.bt.model.CallLogData;
import com.ecarx.bt.model.ContactData;
import com.ecarx.btphone.service.IBlueToothService;
import com.ecarx.btphone.service.IBlueToothServiceCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.yinxm.lib.utils.PinyinUtils;
import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.log.LogUtil;
import ecarx.bluetooth.BluetoothAdapter;
import ecarx.bluetooth.BluetoothCallLog;
import ecarx.bluetooth.BluetoothContact;
import ecarx.bluetooth.BluetoothHeadset;
import ecarx.bluetooth.BluetoothPbap;
import ecarx.bluetooth.BluetoothPbapClient;
import ecarx.bluetooth.BluetoothProfile;
import ecarx.bluetooth.ECarXBluetoothPbapSupport;

import static ecarx.bluetooth.BluetoothPbapClient.EventListener.SyncState.SYNC_STATE_STARTED;

/**
 * Created by kongnan on 16/12/14.
 * 用于同步联系人、通话记录
 */
public class BlueToothService extends Service {

    private static final String TAG = BlueToothService.class.getSimpleName();

    public final static int SYNC_CONTACT_DATA_MAX_COUNT = 2000;
    public final static int SYNC_CALL_RECORDS_MAX_COUNT = 100;

    private ExecutorService contactThreadPool = null;
    private ExecutorService callLogThreadPool = null;
    private List<ContactData> contactsList = null;
    private List<CallLogData> callLogList = null;

    /**
     * 是否同步完成
     */
    private boolean isSyncContactsFinish = false;

    /**
     * 同步通话记录结束
     */
    private boolean isSyncLogsFinish = false;

    /**
     * 实时监听蓝牙开关的状态
     */
    public boolean isOpenBluetooth = false;

    /**
     * 实时监听蓝牙连接的状态
     */
    public boolean isBTConnection = false;

    /**
     * 是否同步中
     */
    private boolean isSyncing = false;

    private ECarXBluetoothPbapSupport support;

    final RemoteCallbackList<IBlueToothServiceCallback> mCallbacks = new RemoteCallbackList<>();

    private IBlueToothService.Stub mBinder = new IBlueToothService.Stub() {
        @Override
        public boolean isSyncing() throws RemoteException {
//            if (support != null) {
//                // support.getSyncState() 这个方法就是个废物！
//                final boolean isSyncing = support.isPbapDownloading();
//                LogUtil.d(TAG, "support.isPbapDownloading() " + isSyncing);
//                if (isSyncing)// 蓝牙正在同步中
//                    return true;
//            }
//            return false;
            // 写入数据库的时间算到蓝牙同步中。
            return isSyncing;
        }

        @Override
        public boolean startSync() throws RemoteException {
            if (!isSyncing) {
                LogUtil.d(TAG, "Start sync");
                getContactsList();
            } else {
                LogUtil.d(TAG, "Syncing Please wait...");
            }
            return isSyncing;
        }

        @Override
        public void registerCallback(IBlueToothServiceCallback cb) throws RemoteException {
            if (cb != null) mCallbacks.register(cb);
        }

        @Override
        public void unregisterCallback(IBlueToothServiceCallback cb) throws RemoteException {
            if (cb != null) mCallbacks.unregister(cb);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "BlueToothService.onStartCommand");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, " onCreate..." + ecarx.os.Build.getDeviceType());
        refreshBluetoothOpenState();
        support = ECarXBluetoothPbapSupport.getInstance(this);
        support.setCurrentClient(callback);
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothPbap.PBAP_SYNC_FINISHED);
        filter.addAction(BluetoothPbap.PBAP_SYNC_FAILED);
        filter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onDestroy() {
        mCallbacks.kill();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.d(TAG, " action onReceive = " + action);
            switch (action) {
                case BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED:
                    isSyncing = false;
                    int newState = intent.getIntExtra(BluetoothProfile.EXTRA_STATE, 0);
                    int oldState = intent.getIntExtra(BluetoothProfile.EXTRA_PREVIOUS_STATE, 0);
                    LogUtil.d(TAG, "oldState----> " + oldState + " newState----> " + newState);
                    if (newState == BluetoothProfile.STATE_CONNECTED) {
                        LogUtil.d(TAG, "---startSync-- ");
                        // 蓝牙连接上了，同步联系人数据
                        isBTConnection = true;
                        getContactsList();
                    } else if (oldState == BluetoothProfile.STATE_CONNECTED &&
                            newState == BluetoothProfile.STATE_DISCONNECTED) {
                        // 蓝牙断开了
                        LogUtil.d(TAG, "---cancelSync-- ");
                        isBTConnection = false;
                        support.cancelSync();
                        clearData();
                        updateSync(1);
                    }
                    break;
                case BluetoothPbap.PBAP_SYNC_FINISHED:
                    if (isOpenBluetooth && isBTConnection) {
                        new Thread() {
                            @Override
                            public void run() {
                                Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
                                LogUtil.d(TAG, "app call notifyContactsSyncFinished");
                                if (!isSyncContactsFinish && !isSyncLogsFinish) {
                                    long beginTime = System.currentTimeMillis();
                                    saveContacts();
                                    LogUtil.d(TAG, "写入联系人数据库用时：" + (System.currentTimeMillis() - beginTime) / 1000);
                                    isSyncing = false;
                                    getCalLogs();
                                } else {
                                    long beginTime = System.currentTimeMillis();
                                    saveCallLogs();
                                    LogUtil.d(TAG, "写入通话记录数据库用时：" + (System.currentTimeMillis() - beginTime) / 1000);
                                    isSyncing = false;
                                }
                                if (!isOpenBluetooth && !isBTConnection) {
                                    // 如果蓝牙断开前正在写入数据，则马上清理掉
                                    ModifyDatabaseService.startActionClear(BlueToothService.this);
                                }
                            }
                        }.start();
                    } else {
                        clearData();
                        updateSync(1);
                    }
                    break;
                case BluetoothPbap.PBAP_SYNC_FAILED:
                    clearData();
                    updateSync(2);
                    break;
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    isSyncing = false;
                    refreshBluetoothOpenState();
                    break;
            }
        }
    };

    private synchronized void clearData() {
        isSyncing = false;
        try {
            if (contactThreadPool != null)
                contactThreadPool.shutdown();
            if (callLogThreadPool != null)
                callLogThreadPool.shutdown();
            if (contactsList != null)
                contactsList.clear();
            if (callLogList != null)
                callLogList.clear();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ModifyDatabaseService.startActionClear(BlueToothService.this);
    }

    private void refreshBluetoothOpenState() {
        try {
            int blueState = BluetoothAdapter.getDefaultAdapter().getState();
            if (blueState == BluetoothAdapter.STATE_ON ||
                    blueState == BluetoothAdapter.STATE_TURNING_ON) {
                // 蓝牙打开
                LogUtil.d(TAG, "BluetoothOpen");
                isOpenBluetooth = true;
            } else if (blueState == BluetoothAdapter.STATE_OFF ||
                    blueState == BluetoothAdapter.STATE_TURNING_OFF) {
                // 蓝牙关闭
                LogUtil.d(TAG, "BluetoothClose");
                isOpenBluetooth = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isOpenBluetooth = false;
        }
    }

    /**
     * 获取到通讯录信息
     */
    public synchronized void getContactsList() {
        try {
            ModifyDatabaseService.startActionClear(this); // 拉取之前一定先清理一遍已存在联系人
            isSyncContactsFinish = false;
            isSyncLogsFinish = false;
            contactThreadPool = Executors.newCachedThreadPool();
            contactsList = Collections.synchronizedList(new ArrayList<ContactData>());
            isSyncing = support.startContactSyncWithLimit(SYNC_CONTACT_DATA_MAX_COUNT);
            LogUtil.d(TAG, "Call startContactSync result : " + isSyncing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取到通话记录信息
     */
    public synchronized void getCalLogs() {
        try {
            isSyncLogsFinish = false;
            callLogThreadPool = Executors.newCachedThreadPool();
            callLogList = Collections.synchronizedList(new ArrayList<CallLogData>());
            isSyncing = support.startCallLogSync(SYNC_CALL_RECORDS_MAX_COUNT);
            LogUtil.d(TAG, "Call startCallLogSync result : " + isSyncing);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回调方法
     */
    private ECarXBluetoothPbapSupport.Callback callback = new ECarXBluetoothPbapSupport.Callback() {

        private static final String TAG = "Callback";

        @Override
        public void onSyncStateChanged(BluetoothPbapClient.EventListener.SyncState oldState,
                                       BluetoothPbapClient.EventListener.SyncState newState) {
            LogUtil.d(TAG, "onSyncStateChanged syncState" + oldState + " syncState1 " + newState);
            if (newState == SYNC_STATE_STARTED) {
                updateSync(0);
            }
//            else if (newState == SYNC_STATE_FINISHED || newState == SYNC_STATE_ABORTED) {
//                endSync();
//            }
        }

        @Override
        public void onContactItemCountDetermined(int i) {
            LogUtil.d(TAG, "onContactItemCountDetermined " + i);
        }

        @Override
        public void onContactItemFetched(final BluetoothContact bluetoothContact) {
//            LogUtil.d(TAG, "onContactItemFetched --> get contact item ");
            if (!isOpenBluetooth) {
                LogUtil.d(TAG, "--PIM-bluetoothservice-isConnected , false");
                return;
            }
            try {
                if (!contactThreadPool.isShutdown()) {
                    contactThreadPool.execute(new CreateContactJob(bluetoothContact));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCallLogItemCountDetermined(int i) {
            LogUtil.d(TAG, "onCallLogItemCountDetermined " + i);
        }

        @Override
        public void onCallLogItemFetched(final BluetoothCallLog bluetoothCallLog) {
//            LogUtil.d(TAG, "onCallLogItemFetched --> get callLog item ");
            if (!isOpenBluetooth) {
                LogUtil.d(TAG, "--PIM-bluetoothservice-isConnected , false");
                return;
            }
            try {
                if (!callLogThreadPool.isShutdown()) {
                    callLogThreadPool.execute(new CreateCallLogJob(bluetoothCallLog));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onContactCleanDatabase(boolean b) {
            // 每次连接蓝牙设备只能同步一次联系人！
            // 再次同步需要断开后重连才可以同步联系人。
            // 想多次同步联系人搞了很多次都没成功。@张立来
        }
    };

    private class CreateContactJob implements Runnable {
        private BluetoothContact bluetoothContact;

        CreateContactJob(BluetoothContact contact) {
            this.bluetoothContact = contact;
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
            bluetoothContactToPhoneContact(bluetoothContact);
        }
    }

    /**
     * 将蓝牙联系人转换为手机联系人
     */
    public void bluetoothContactToPhoneContact(BluetoothContact contact) {
        if (contact != null) {
//            String tmp = contact.getFirstName() + "|" + contact.getMiddleName()
//                    + "|" + contact.getLastName() + " = "
//                    + Arrays.deepToString(contact.getNumberArray());
//            LogUtil.d(TAG, "--PIM-onContactSync , " + tmp);

            ContactData contactData = new ContactData();
            String name;
            if (StringUtil.checkChinese(contact.getLastName())) {
                name = contact.getLastName() + contact.getMiddleName() + contact.getFirstName();
            } else {
                name = contact.getFirstName() + contact.getMiddleName() + contact.getLastName();
            }
            if (TextUtils.isEmpty(name)) {
                String[] array = contact.getNumberArray();
                if ((array != null) && (array.length > 0)) {
                    name = array[0];
                }
            }
            contactData.setName(convertDefaultName(name));
            List<Integer> phoneNumTypeList = new ArrayList<>();
            List<String> phoneNumList = new ArrayList<>();
            if (contact.getPhoneTypeArray() != null && contact.getNumberArray() != null
                    && contact.getPhoneTypeArray().length == contact.getNumberArray().length) {
                for (int i = 0; i < contact.getPhoneTypeArray().length; i++) {
                    String telephoneNum = contact.getNumberArray()[i];
                    int type = contact.getPhoneTypeArray()[i];
                    phoneNumList.add(StringUtil.getPureNumbers(telephoneNum));
                    phoneNumTypeList.add(initType(type));
                }
            }
            contactData.setPhoneNumTypeList(phoneNumTypeList);
            contactData.setPhoneNumList(phoneNumList);
            contactData.setLetter(PinyinUtils.cn2FirstSpell(name, false));
            contactsList.add(contactData);
        }
    }

    /**
     * 转换名称，当没有名称的时候，配置默认名称
     *
     * @param name 联系人姓名
     * @return 联系人姓名
     */
    private String convertDefaultName(String name) {
        if (null == name || "".equals(name)) {
//            name = getResources().getString(R.string.no_name_contact_str);
            name = "未知";
        }
        return name;
    }

    /**
     * 初始化信息
     */
    private int initType(int type) {
        switch (type) {
            case BluetoothContact.NUMBER_TYPE_HOME:
                return ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
            case BluetoothContact.NUMBER_TYPE_WORK:
                return ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN;
            case BluetoothContact.NUMBER_TYPE_CELL:
                return ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
            default:
                return ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
        }
    }

    private class CreateCallLogJob implements Runnable {

        private BluetoothCallLog bluetoothCallLog;

        CreateCallLogJob(BluetoothCallLog callLog) {
            this.bluetoothCallLog = callLog;
        }

        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
            bluetoothCallLogToPhoneCallLog(bluetoothCallLog);
        }
    }

    /**
     * 将蓝牙通话记录转换为手机通话记录
     */
    public void bluetoothCallLogToPhoneCallLog(BluetoothCallLog callLog) {
        if (callLog != null) {
//            String tmp = callLog.firstName + "|" + callLog.middleName
//                    + "|" + callLog.lastName + " = " + callLog.number
//                    + " = " + callLog.type + " = " + callLog.time;
//            LogUtil.d(TAG, "--PIM-onCallLogSync , " + tmp);

            String name;
            if (StringUtil.checkChinese(callLog.lastName)) {
                name = callLog.lastName + callLog.middleName + callLog.firstName;
            } else {
                name = callLog.firstName + callLog.middleName + callLog.lastName;
            }
            if (StringUtil.isBlank(name)) {
                name = "";
            }
            int type = callLog.type;
            String time = callLog.time;
            if (!TextUtils.isEmpty(time)) {
                time = time.replaceAll("[^0-9]", "");
                if (time.length() != 14)
                    return;
            } else {
                return;
            }
            String telNum = callLog.number;
            CallLogData info = new CallLogData();
            info.phone = telNum;
//            info.date = StringUtil.toBTDate(time);
            info.duration = 0;
            info.type = type;
            info.name = name;
            callLogList.add(info);
        }
    }

    private synchronized void saveContacts() {
        try {
            if (contactThreadPool == null) return;
            contactThreadPool.shutdown();
            while (true) {
                if (contactThreadPool.isTerminated()) {
                    // FIXME: 16/12/16 android.os.TransactionTooLargeException
                    // 保存到数据库,每次存储200个
                    List<ContactData> copy = new ArrayList<>();
                    for (ContactData aContactsList : contactsList) {
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                        copy.add(aContactsList);
                        if (copy.size() >= 200) {
                            addContacts(copy);
                            copy.clear();
                        }
                    }
                    if (copy.size() != 0) {
                        addContacts(copy);
                    }
                    LogUtil.i(TAG, "addContacts all  finish <----" + contactsList.size());
                    sendBroadcast(new Intent(ModifyDatabaseService.ACTION_CONTACTS_UPDATE));
                    // end
                    isSyncContactsFinish = true;
                    break;
                }
                Thread.sleep(200);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量添加联系人
     */
    private void addContacts(List<ContactData> list) throws RemoteException, OperationApplicationException {
        LogUtil.d(TAG, "addContacts --> start addContacts " + list.size());
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        for (ContactData item : list) {
            int index = ops.size();
            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.AGGREGATION_MODE, ContactsContract.RawContacts.AGGREGATION_MODE_DISABLED)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .withYieldAllowed(true).build());
            //添加姓名
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, index)
                    .withValue(ContactsContract.Contacts.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, item.getName())
                    .withYieldAllowed(true).build());

            int phoneSize = item.getPhoneNumList().size();
            for (int i = 0; i < phoneSize; i++) {
                int phoneType = item.getPhoneNumTypeList().get(i);
                String phoneNum = item.getPhoneNumList().get(i);
                //添加号码
                ContentProviderOperation opePhone = ContentProviderOperation.
                        newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Contacts.Data.RAW_CONTACT_ID, index)
                        .withValue(ContactsContract.Contacts.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, phoneType)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNum)
                        .build();
                ops.add(opePhone);
            }
        }
        LogUtil.d(TAG, " addContacts & one group applyBatch start ...");
        ContentProviderResult[] results = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        LogUtil.d(TAG, " addContacts & one group applyBatch finish ..." + results.length);
    }

    private synchronized void saveCallLogs() {
        LogUtil.i(TAG, "start save CallLogs <----");
        try {
            if (callLogThreadPool == null) return;
            callLogThreadPool.shutdown();
            while (true) {
                if (callLogThreadPool.isTerminated()) {
                    Collections.sort(callLogList, new Comparator<CallLogData>() {
                        public int compare(CallLogData d1, CallLogData d2) {
                            return Long.compare(d2.date, d1.date);
                        }
                    });
                    addCallLogs(callLogList);
                    LogUtil.i(TAG, "saveCallLogs finish <----" + callLogList.size());
                    isSyncLogsFinish = true;
                    updateSync(1);
                    break;
                }
                Thread.sleep(200);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量添加通话记录
     */
    private void addCallLogs(List<CallLogData> list) throws RemoteException, OperationApplicationException {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ContentValues values = new ContentValues();
        for (CallLogData callLog : list) {
            values.clear();
            values.put(CallLog.Calls.CACHED_NAME, callLog.name);
            values.put(CallLog.Calls.NUMBER, callLog.phone);
            values.put(CallLog.Calls.TYPE, callLog.type);
            values.put(CallLog.Calls.DATE, callLog.date);
            values.put(CallLog.Calls.DURATION, callLog.duration);
            values.put(CallLog.Calls.NEW, "0");// 0已看1未看 ,由于没有获取默认全为已读
            ops.add(ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI).withValues(values)
                    .withYieldAllowed(true).build());

//            String tmp = callLog.name + "|" + callLog.phone
//                    + " = " + callLog.type + " = " + callLog.date;
//            LogUtil.d(TAG, "--PIM-addCallLogs , " + tmp);
        }
        // 真正添加
        getContentResolver().applyBatch(CallLog.AUTHORITY, ops);
    }

    private void updateSync(int state) {
        final int N = mCallbacks.beginBroadcast();
        for (int i = 0; i < N; i++) {
            try {
                switch (state) {
                    case 0:
                        mCallbacks.getBroadcastItem(i).startSync();
                        break;
                    case 1:
                        mCallbacks.getBroadcastItem(i).endSync();
                        break;
                    case 2:
                        mCallbacks.getBroadcastItem(i).failSync();
                        break;
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallbacks.finishBroadcast();
    }
}
