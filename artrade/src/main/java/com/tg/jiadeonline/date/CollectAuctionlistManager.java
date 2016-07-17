package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.CollectAuctionlistBean;
import java.util.ArrayList;
import java.util.List;

public class CollectAuctionlistManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "collectauctionlist";
  private static final String TAG = "collectauctionlist";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public CollectAuctionlistManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("collectauctionlist", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("collectauctionlist", null, null) >= 0;
  }

  public List<CollectAuctionlistBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("collectauctionlist", null, null, null, null, null, null);
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
      CollectAuctionlistBean localCollectAuctionlistBean = new CollectAuctionlistBean();
      localCollectAuctionlistBean.setItemnum(str1);
      localCollectAuctionlistBean.setTitle(str2);
      localCollectAuctionlistBean.setPicPath(str3);
      localCollectAuctionlistBean.setTimeStart(str4);
      localCollectAuctionlistBean.setTimeEnd(str5);
      localCollectAuctionlistBean.setSysTime(str6);
      localCollectAuctionlistBean.setCarePers(str7);
      localCollectAuctionlistBean.setMinimumBid(str8);
      localCollectAuctionlistBean.setCurrentBid(str9);
      localCollectAuctionlistBean.setBstatus(str10);
      ((List)localObject).add(localCollectAuctionlistBean);
      localCursor.moveToNext();
    }
  }

  public List<CollectAuctionlistBean> fetchAllOrderDatas(String paramString)
  {
    Cursor localCursor = this.mSQLiteDatabase.query("collectauctionlist", null, null, null, null, null, paramString);
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
      CollectAuctionlistBean localCollectAuctionlistBean = new CollectAuctionlistBean();
      localCollectAuctionlistBean.setItemnum(str1);
      localCollectAuctionlistBean.setTitle(str2);
      localCollectAuctionlistBean.setPicPath(str3);
      localCollectAuctionlistBean.setTimeStart(str4);
      localCollectAuctionlistBean.setTimeEnd(str5);
      localCollectAuctionlistBean.setSysTime(str6);
      localCollectAuctionlistBean.setCarePers(str7);
      localCollectAuctionlistBean.setMinimumBid(str8);
      localCollectAuctionlistBean.setCurrentBid(str9);
      localCollectAuctionlistBean.setBstatus(str10);
      ((List)localObject).add(localCollectAuctionlistBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(CollectAuctionlistBean paramCollectAuctionlistBean)
  {
    String str1 = paramCollectAuctionlistBean.getItemnum();
    String str2 = paramCollectAuctionlistBean.getTitle();
    String str3 = paramCollectAuctionlistBean.getPicPath();
    String str4 = paramCollectAuctionlistBean.getTimeStart();
    String str5 = paramCollectAuctionlistBean.getTimeEnd();
    String str6 = paramCollectAuctionlistBean.getSysTime();
    String str7 = paramCollectAuctionlistBean.getCarePers();
    String str8 = paramCollectAuctionlistBean.getMinimumBid();
    String str9 = paramCollectAuctionlistBean.getCurrentBid();
    String str10 = paramCollectAuctionlistBean.getBstatus();
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
    return this.mSQLiteDatabase.insert("collectauctionlist", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.CollectAuctionlistManager
 * JD-Core Version:    0.6.2
 */