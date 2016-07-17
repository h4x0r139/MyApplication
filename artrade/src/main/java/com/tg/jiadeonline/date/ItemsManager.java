package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.ItemsBean;
import java.util.ArrayList;
import java.util.List;

public class ItemsManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "items";
  private static final String TAG = "items";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public ItemsManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("items", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("items", null, null) > 0;
  }

  public List<ItemsBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("items", null, null, null, null, null, null);
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
      int m = localCursor.getColumnIndex("priceBid");
      int n = localCursor.getColumnIndex("dateBid");
      int i1 = localCursor.getColumnIndex("commision");
      int i2 = localCursor.getColumnIndex("certFee");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      String str6 = localCursor.getString(i1);
      String str7 = localCursor.getString(i2);
      ItemsBean localItemsBean = new ItemsBean();
      localItemsBean.setItemnum(str1);
      localItemsBean.setTitle(str2);
      localItemsBean.setPicPath(str3);
      localItemsBean.setPriceBid(str4);
      localItemsBean.setDateBid(str5);
      localItemsBean.setCommision(str6);
      localItemsBean.setCertFee(str7);
      ((List)localObject).add(localItemsBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(ItemsBean paramItemsBean)
  {
    String str1 = paramItemsBean.getItemnum();
    String str2 = paramItemsBean.getTitle();
    String str3 = paramItemsBean.getPicPath();
    String str4 = paramItemsBean.getPriceBid();
    String str5 = paramItemsBean.getDateBid();
    String str6 = paramItemsBean.getCommision();
    String str7 = paramItemsBean.getCertFee();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("itemnum", str1);
    localContentValues.put("title", str2);
    localContentValues.put("picPath", str3);
    localContentValues.put("priceBid", str4);
    localContentValues.put("dateBid", str5);
    localContentValues.put("commision", str6);
    localContentValues.put("certFee", str7);
    return this.mSQLiteDatabase.insert("items", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.ItemsManager
 * JD-Core Version:    0.6.2
 */