package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.SearchItemsBean;
import java.util.ArrayList;
import java.util.List;

public class SearchItemsManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "searchitems";
  private static final String TAG = "searchitems";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public SearchItemsManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("searchitems", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("searchitems", null, null) > 0;
  }

  public List<SearchItemsBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("searchitems", null, null, null, null, null, null);
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
      int k = localCursor.getColumnIndex("picture");
      int m = localCursor.getColumnIndex("total");
      int n = localCursor.getColumnIndex("timeEnd");
      int i1 = localCursor.getColumnIndex("carePers");
      int i2 = localCursor.getColumnIndex("minimumBid");
      int i3 = localCursor.getColumnIndex("currentBid");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      String str6 = localCursor.getString(i1);
      String str7 = localCursor.getString(i2);
      String str8 = localCursor.getString(i3);
      SearchItemsBean localSearchItemsBean = new SearchItemsBean();
      localSearchItemsBean.setItemnum(str1);
      localSearchItemsBean.setTitle(str2);
      localSearchItemsBean.setPicture(str3);
      localSearchItemsBean.setTotal(str4);
      localSearchItemsBean.setTimeEnd(str5);
      localSearchItemsBean.setCarePers(str6);
      localSearchItemsBean.setMinimumBid(str7);
      localSearchItemsBean.setCurrentBid(str8);
      ((List)localObject).add(localSearchItemsBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(SearchItemsBean paramSearchItemsBean)
  {
    String str1 = paramSearchItemsBean.getItemnum();
    String str2 = paramSearchItemsBean.getTitle();
    String str3 = paramSearchItemsBean.getPicture();
    String str4 = paramSearchItemsBean.getTotal();
    String str5 = paramSearchItemsBean.getTimeEnd();
    String str6 = paramSearchItemsBean.getCarePers();
    String str7 = paramSearchItemsBean.getMinimumBid();
    String str8 = paramSearchItemsBean.getCurrentBid();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("itemnum", str1);
    localContentValues.put("title", str2);
    localContentValues.put("picture", str3);
    localContentValues.put("total", str4);
    localContentValues.put("timeEnd", str5);
    localContentValues.put("carePers", str6);
    localContentValues.put("minimumBid", str7);
    localContentValues.put("currentBid", str8);
    return this.mSQLiteDatabase.insert("searchitems", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.SearchItemsManager
 * JD-Core Version:    0.6.2
 */