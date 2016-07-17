package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tg.jiadeonline.bean.AuctiondetailsBean;

import java.util.ArrayList;
import java.util.List;

public class AuctiondetailsManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "auctiondetails";
  private static final String TAG = "auctiondetails";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public AuctiondetailsManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("auctiondetails", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("auctiondetails", null, null) > 0;
  }

  public List<AuctiondetailsBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("auctiondetails", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("title");
      int j = localCursor.getColumnIndex("picPath");
      int k = localCursor.getColumnIndex("timeStart");
      int m = localCursor.getColumnIndex("timeEnd");
      int n = localCursor.getColumnIndex("sysTime");
      int i1 = localCursor.getColumnIndex("status");
      int i2 = localCursor.getColumnIndex("sPicPath");
      int i3 = localCursor.getColumnIndex("isDelay");
      int i4 = localCursor.getColumnIndex("minimumPrice");
      int i5 = localCursor.getColumnIndex("curBid");
      int i6 = localCursor.getColumnIndex("bidTimes");
      int i7 = localCursor.getColumnIndex("incrementPrice");
      int i8 = localCursor.getColumnIndex("description");
      int i9 = localCursor.getColumnIndex("isCared");
      int i10 = localCursor.getColumnIndex("aType");
      String str1 = localCursor.getString(localCursor.getColumnIndex("reservePrice"));
      String str2 = localCursor.getString(i);
      String str3 = localCursor.getString(j);
      String str4 = localCursor.getString(k);
      String str5 = localCursor.getString(m);
      String str6 = localCursor.getString(n);
      String str7 = localCursor.getString(i1);
      String str8 = localCursor.getString(i2);
      String str9 = localCursor.getString(i3);
      String str10 = localCursor.getString(i4);
      String str11 = localCursor.getString(i5);
      String str12 = localCursor.getString(i6);
      String str13 = localCursor.getString(i7);
      String str14 = localCursor.getString(i8);
      String str15 = localCursor.getString(i9);
      String str16 = localCursor.getString(i10);
      AuctiondetailsBean localAuctiondetailsBean = new AuctiondetailsBean();
      localAuctiondetailsBean.setTitle(str2);
      localAuctiondetailsBean.setPicPath(str3);
      localAuctiondetailsBean.setTimeStart(str4);
      localAuctiondetailsBean.setTimeEnd(str5);
      localAuctiondetailsBean.setSysTime(str6);
      localAuctiondetailsBean.setStatus(str7);
      localAuctiondetailsBean.setsPicPath(str8);
      localAuctiondetailsBean.setIsDelay(str9);
      localAuctiondetailsBean.setMinimumPrice(str10);
      localAuctiondetailsBean.setCurBid(str11);
      localAuctiondetailsBean.setBidTimes(str12);
      localAuctiondetailsBean.setIncrementPrice(str13);
      localAuctiondetailsBean.setDescription(str14);
      localAuctiondetailsBean.setIsCared(str15);
      localAuctiondetailsBean.setaType(str16);
      localAuctiondetailsBean.setReservePrice(str1);
      ((List)localObject).add(localAuctiondetailsBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(AuctiondetailsBean paramAuctiondetailsBean)
  {
    String str1 = paramAuctiondetailsBean.getTitle();
    String str2 = paramAuctiondetailsBean.getPicPath();
    String str3 = paramAuctiondetailsBean.getTimeStart();
    String str4 = paramAuctiondetailsBean.getTimeEnd();
    String str5 = paramAuctiondetailsBean.getSysTime();
    String str6 = paramAuctiondetailsBean.getStatus();
    String str7 = paramAuctiondetailsBean.getsPicPath();
    String str8 = paramAuctiondetailsBean.getIsDelay();
    String str9 = paramAuctiondetailsBean.getMinimumPrice();
    String str10 = paramAuctiondetailsBean.getCurBid();
    String str11 = paramAuctiondetailsBean.getBidTimes();
    String str12 = paramAuctiondetailsBean.getIncrementPrice();
    String str13 = paramAuctiondetailsBean.getDescription();
    String str14 = paramAuctiondetailsBean.getIsCared();
    String str15 = paramAuctiondetailsBean.getaType();
    String str16 = paramAuctiondetailsBean.getReservePrice();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("reservePrice", str16);
    localContentValues.put("title", str1);
    localContentValues.put("picPath", str2);
    localContentValues.put("timeStart", str3);
    localContentValues.put("timeEnd", str4);
    localContentValues.put("sysTime", str5);
    localContentValues.put("status", str6);
    localContentValues.put("sPicPath", str7);
    localContentValues.put("isDelay", str8);
    localContentValues.put("minimumPrice", str9);
    localContentValues.put("curBid", str10);
    localContentValues.put("bidTimes", str11);
    localContentValues.put("incrementPrice", str12);
    localContentValues.put("description", str13);
    localContentValues.put("isCared", str14);
    localContentValues.put("aType", str15);
    return this.mSQLiteDatabase.insert("auctiondetails", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.AuctiondetailsManager
 * JD-Core Version:    0.6.2
 */