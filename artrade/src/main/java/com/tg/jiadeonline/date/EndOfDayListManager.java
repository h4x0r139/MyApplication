package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.EndOfDayListBean;
import java.util.ArrayList;
import java.util.List;

public class EndOfDayListManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "endofday";
  private static final String TAG = "endofday";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public EndOfDayListManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("endofday", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("endofday", null, null) > 0;
  }

  public List<EndOfDayListBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("endofday", null, null, null, null, null, null);
    Object localObject = null;
    localCursor.moveToFirst();
    while (true)
    {
      if (localCursor.isAfterLast())
      {
        localCursor.close();
        return localObject;
      }
      if (localObject == null)
        localObject = new ArrayList();
      int i = localCursor.getColumnIndex("itemnum");
      int j = localCursor.getColumnIndex("title");
      int k = localCursor.getColumnIndex("picPath");
      int m = localCursor.getColumnIndex("timeStart");
      int n = localCursor.getColumnIndex("timeEnd");
      int i1 = localCursor.getColumnIndex("sysTime");
      int i2 = localCursor.getColumnIndex("carePers");
      int i3 = localCursor.getColumnIndex("minimumBid");
      int i4 = localCursor.getColumnIndex("currentBid");
      int i5 = localCursor.getColumnIndex("bstatus");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      String str6 = localCursor.getString(i1);
      String str7 = localCursor.getString(i2);
      String str8 = localCursor.getString(i3);
      String str9 = localCursor.getString(i4);
      String str10 = localCursor.getString(i5);
      EndOfDayListBean localEndOfDayListBean = new EndOfDayListBean();
      localEndOfDayListBean.setItemnum(str1);
      localEndOfDayListBean.setTitle(str2);
      localEndOfDayListBean.setPicPath(str3);
      localEndOfDayListBean.setTimeStart(str4);
      localEndOfDayListBean.setTimeEnd(str5);
      localEndOfDayListBean.setSysTime(str6);
      localEndOfDayListBean.setCarePers(str7);
      localEndOfDayListBean.setMinimumBid(str8);
      localEndOfDayListBean.setCurrentBid(str9);
      localEndOfDayListBean.setBstatus(str10);
      ((List)localObject).add(localEndOfDayListBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(EndOfDayListBean paramEndOfDayListBean)
  {
    String str1 = paramEndOfDayListBean.getItemnum();
    String str2 = paramEndOfDayListBean.getTitle();
    String str3 = paramEndOfDayListBean.getPicPath();
    String str4 = paramEndOfDayListBean.getTimeStart();
    String str5 = paramEndOfDayListBean.getTimeEnd();
    String str6 = paramEndOfDayListBean.getSysTime();
    String str7 = paramEndOfDayListBean.getCarePers();
    String str8 = paramEndOfDayListBean.getMinimumBid();
    String str9 = paramEndOfDayListBean.getCurrentBid();
    String str10 = paramEndOfDayListBean.getBstatus();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("itemnum", str1);
    localContentValues.put("title", str2);
    localContentValues.put("picPath", str3);
    localContentValues.put("timeStart", str4);
    localContentValues.put("timeEnd", str5);
    localContentValues.put("sysTime", str6);
    localContentValues.put("carePers", str7);
    localContentValues.put("minimumBid", str8);
    localContentValues.put("currentBid", str9);
    localContentValues.put("bstatus", str10);
    return this.mSQLiteDatabase.insert("endofday", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.EndOfDayListManager
 * JD-Core Version:    0.6.2
 */