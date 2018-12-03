package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.UserAccBean;
import java.util.ArrayList;
import java.util.List;

public class UserAccManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "useracc";
  private static final String TAG = "useracc";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public UserAccManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("useracc", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("useracc", null, null) > 0;
  }

  public List<UserAccBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("useracc", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("id");
      int j = localCursor.getColumnIndex("amount");
      int k = localCursor.getColumnIndex("payTime");
      int m = localCursor.getColumnIndex("op_type");
      int n = localCursor.getColumnIndex("acctType");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      UserAccBean localUserAccBean = new UserAccBean();
      localUserAccBean.setId(str1);
      localUserAccBean.setAmount(str2);
      localUserAccBean.setPayTime(str3);
      localUserAccBean.setOp_type(str4);
      localUserAccBean.setAcctType(str5);
      ((List)localObject).add(localUserAccBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(UserAccBean paramUserAccBean)
  {
    String str1 = paramUserAccBean.getId();
    String str2 = paramUserAccBean.getAmount();
    String str3 = paramUserAccBean.getPayTime();
    String str4 = paramUserAccBean.getOp_type();
    String str5 = paramUserAccBean.getAcctType();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id", str1);
    localContentValues.put("amount", str2);
    localContentValues.put("payTime", str3);
    localContentValues.put("op_type", str4);
    localContentValues.put("acctType", str5);
    return this.mSQLiteDatabase.insert("useracc", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.UserAccManager
 * JD-Core Version:    0.6.2
 */