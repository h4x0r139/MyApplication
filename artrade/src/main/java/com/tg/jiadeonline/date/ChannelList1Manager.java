package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import java.util.ArrayList;
import java.util.List;

public class ChannelList1Manager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "channellist1";
  private static final String TAG = " channellist1";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public ChannelList1Manager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i(" channellist1", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("channellist1", null, null) > 0;
  }

  public List<ChannelList1Bean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("channellist1", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("catId");
      int j = localCursor.getColumnIndex("catTit");
      int k = localCursor.getColumnIndex("catSubTit");
      int m = localCursor.getColumnIndex("timeStart");
      int n = localCursor.getColumnIndex("timeEnd");
      int i1 = localCursor.getColumnIndex("catPicpath");
      int i2 = localCursor.getColumnIndex("orderNum");
      int i3 = localCursor.getColumnIndex("ispar");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      String str6 = localCursor.getString(i1);
      String str7 = localCursor.getString(i2);
      ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
      localChannelList1Bean.setCatId(str1);
      localChannelList1Bean.setCatTit(str2);
      localChannelList1Bean.setCatSubTit(str3);
      localChannelList1Bean.setTimeStart(str4);
      localChannelList1Bean.setTimeEnd(str5);
      localChannelList1Bean.setCatPicpath(str6);
      localChannelList1Bean.setOrderNum(str7);
      localChannelList1Bean.setIspar(localCursor.getString(i3));
      ((List)localObject).add(localChannelList1Bean);
      localCursor.moveToNext();
    }
  }

  public long insertData(ChannelList1Bean paramChannelList1Bean)
  {
    String str1 = paramChannelList1Bean.getCatId();
    String str2 = paramChannelList1Bean.getCatTit();
    String str3 = paramChannelList1Bean.getCatSubTit();
    String str4 = paramChannelList1Bean.getTimeStart();
    String str5 = paramChannelList1Bean.getTimeEnd();
    String str6 = paramChannelList1Bean.getCatPicpath();
    String str7 = paramChannelList1Bean.getOrderNum();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("catId", str1);
    localContentValues.put("catTit", str2);
    localContentValues.put("catSubTit", str3);
    localContentValues.put("timeStart", str4);
    localContentValues.put("timeEnd", str5);
    localContentValues.put("catPicpath", str6);
    localContentValues.put("orderNum", str7);
    localContentValues.put("ispar", paramChannelList1Bean.getIspar());
    return this.mSQLiteDatabase.insert("channellist1", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.ChannelList1Manager
 * JD-Core Version:    0.6.2
 */