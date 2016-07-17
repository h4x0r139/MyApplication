package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.ChannelListBean;
import java.util.ArrayList;
import java.util.List;

public class ChannelListManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "channellist";
  private static final String TAG = "channellist";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public ChannelListManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("channellist", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("channellist", null, null) > 0;
  }

  public List<ChannelListBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("channellist", null, null, null, null, null, null);
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
      int j = localCursor.getColumnIndex("catName");
      int k = localCursor.getColumnIndex("catPicpath");
      int m = localCursor.getColumnIndex("orderNum");
      int n = localCursor.getColumnIndex("sysTime");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      ChannelListBean localChannelListBean = new ChannelListBean();
      localChannelListBean.setCatId(str1);
      localChannelListBean.setCatName(str2);
      localChannelListBean.setCatPicpath(str3);
      localChannelListBean.setOrderNum(str4);
      localChannelListBean.setSysTime(str5);
      ((List)localObject).add(localChannelListBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(ChannelListBean paramChannelListBean)
  {
    String str1 = paramChannelListBean.getCatId();
    String str2 = paramChannelListBean.getCatName();
    String str3 = paramChannelListBean.getCatPicpath();
    String str4 = paramChannelListBean.getOrderNum();
    String str5 = paramChannelListBean.getSysTime();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("catId", str1);
    localContentValues.put("catName", str2);
    localContentValues.put("catPicpath", str3);
    localContentValues.put("orderNum", str4);
    localContentValues.put("sysTime", str5);
    return this.mSQLiteDatabase.insert("channellist", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.ChannelListManager
 * JD-Core Version:    0.6.2
 */