package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.SpecialFieldlistBean;
import java.util.ArrayList;
import java.util.List;

public class SpecialFieldlistManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "specialfieldlist";
  private static final String TAG = "specialfieldlist";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public SpecialFieldlistManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("specialfieldlist", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("specialfieldlist", null, null) > 0;
  }

  public List<SpecialFieldlistBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("specialfieldlist", null, null, null, null, null, null);
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
      int i6 = localCursor.getColumnIndex("catPic");
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
      String str11 = localCursor.getString(i6);
      SpecialFieldlistBean localSpecialFieldlistBean = new SpecialFieldlistBean();
      localSpecialFieldlistBean.setItemnum(str1);
      localSpecialFieldlistBean.setTitle(str2);
      localSpecialFieldlistBean.setPicPath(str3);
      localSpecialFieldlistBean.setTimeStart(str4);
      localSpecialFieldlistBean.setTimeEnd(str5);
      localSpecialFieldlistBean.setSysTime(str6);
      localSpecialFieldlistBean.setCarePers(str7);
      localSpecialFieldlistBean.setMinimumBid(str8);
      localSpecialFieldlistBean.setCurrentBid(str9);
      localSpecialFieldlistBean.setBstatus(str10);
      localSpecialFieldlistBean.setCatPic(str11);
      ((List)localObject).add(localSpecialFieldlistBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(SpecialFieldlistBean paramSpecialFieldlistBean)
  {
    String str1 = paramSpecialFieldlistBean.getItemnum();
    String str2 = paramSpecialFieldlistBean.getTitle();
    String str3 = paramSpecialFieldlistBean.getPicPath();
    String str4 = paramSpecialFieldlistBean.getTimeStart();
    String str5 = paramSpecialFieldlistBean.getTimeEnd();
    String str6 = paramSpecialFieldlistBean.getSysTime();
    String str7 = paramSpecialFieldlistBean.getCarePers();
    String str8 = paramSpecialFieldlistBean.getMinimumBid();
    String str9 = paramSpecialFieldlistBean.getCurrentBid();
    String str10 = paramSpecialFieldlistBean.getBstatus();
    String str11 = paramSpecialFieldlistBean.getCatPic();
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
    localContentValues.put("catPic", str11);
    return this.mSQLiteDatabase.insert("specialfieldlist", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.SpecialFieldlistManager
 * JD-Core Version:    0.6.2
 */