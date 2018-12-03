package cn.yinxm.lib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.domain.CationList;

/**
 * 功能：
 * Created by yinxm on 2017/5/27.
 */

public class ReadContactList {
    private static String TAG = "yinxm";

    /**
     * 读取本地通讯录
     */
    public static List<CationList> getLocalContact(Context context) {
        List<CationList> lists = new ArrayList<>();
        try {
            Uri uri = Uri.parse("content://com.android.contacts/contacts");
            ContentResolver reslover = context.getContentResolver();
            Log.d(TAG,"reslover="+reslover);

            if(reslover == null) {
                return lists;
            }
            Cursor cursor = reslover.query(uri, null, null, null, null);
            Log.d(TAG,"cursor="+cursor);
            if (cursor != null) {
                Log.d(TAG,"cursor.getCount="+cursor.getCount());
            }
            while (cursor != null && cursor.moveToNext()) {
                Log.d(TAG,"cursor.getCount="+cursor.getCount());

                //实体类添加手机号 名称
                CationList cation = new CationList();
                //获得联系人ID
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //添加到实体类
                cation.setContactID(Long.valueOf(id));
                //获得联系人姓名
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //添加到实体类
                cation.setName(name);
                //获得联系人手机号码
                Cursor phone = reslover.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                cation.getList().clear();
                while (phone.moveToNext()) { //存多个手机号
                    int phoneFieldColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phoneNumber = phone.getString(phoneFieldColumnIndex);
                    if (phoneNumber != null) {
                        cation.getList().add(phoneNumber);
                        lists.add(cation);
                    }
                }
                Log.d(TAG,"list.size="+lists.size()+"\n "+lists);
            }

            List<CationList> cationLists = SimQuery(context);
            lists.addAll(cationLists);
        }catch (Exception e) {
            Log.e(TAG,"获取本地通讯录异常",e);
        }

        return lists;
    }

    /**
     * 获取SIM卡 联系人,不一定能读取到
     * @return
     */
    public static List<CationList> SimQuery(Context context) {
        List<CationList> lists = new ArrayList<>();
        try {
            Uri uri = Uri.parse("content://icc/adn");
            Cursor cursor = context.getContentResolver().query(uri, null, null,
                    null, null);
            Log.d(TAG,"sim card cursor="+cursor);
            if (cursor != null) {
                Log.d(TAG,"sim card cursor.getCount="+cursor.getCount());
            }

            while (cursor != null && cursor.moveToNext()) {
                Log.d(TAG,"sim card cursor.getCount="+cursor.getCount());
                CationList cation = new CationList();
                String id = cursor.getString(cursor.getColumnIndex(Contacts.People._ID));
                String name = cursor.getString(cursor.getColumnIndex(Contacts.People.NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(Contacts.People.NUMBER));
                cation.setName(name);
                cation.getList().add(phoneNumber);
                lists.add(cation);
            }
        } catch (Exception e) {
            Log.e(TAG,"获取sim卡通讯录异常",e);
        }

        return lists;

    }
}
