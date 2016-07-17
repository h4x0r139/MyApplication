package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.UserFsBean;
import java.util.ArrayList;
import java.util.List;

public class ForSaleInfoManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "userfs";
  private static final String TAG = "userfs";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public ForSaleInfoManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("userfs", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("userfs", null, null) > 0;
  }

  public List<UserFsBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("userfs", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("forSaleId");
      int j = localCursor.getColumnIndex("userFSFee");
      int k = localCursor.getColumnIndex("userFSId");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      UserFsBean localUserFsBean = new UserFsBean();
      localUserFsBean.setForSaleId(str1);
      localUserFsBean.setUserFSFee(str2);
      localUserFsBean.setUserFSId(str3);
      ((List)localObject).add(localUserFsBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(UserFsBean paramUserFsBean)
  {
    String str1 = paramUserFsBean.getForSaleId();
    String str2 = paramUserFsBean.getUserFSFee();
    String str3 = paramUserFsBean.getUserFSId();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("forSaleId", str1);
    localContentValues.put("userFSFee", str2);
    localContentValues.put("userFSId", str3);
    return this.mSQLiteDatabase.insert("userfs", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.ForSaleInfoManager
 * JD-Core Version:    0.6.2
 */