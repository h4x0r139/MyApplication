package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.LoginBean;
import java.util.ArrayList;
import java.util.List;

public class RegisterOrLoginManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "register";
  private static final String TAG = "register";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public RegisterOrLoginManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("register", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("register", null, null) >= 0;
  }

  public List<LoginBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("register", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("ErrorInfo");
      int j = localCursor.getColumnIndex("nickname");
      int k = localCursor.getColumnIndex("Success");
      int m = localCursor.getColumnIndex("userId");
      int n = localCursor.getColumnIndex("password");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      LoginBean localLoginBean = new LoginBean();
      localLoginBean.setErrorInfo(str1);
      localLoginBean.setNickname(str2);
      localLoginBean.setSuccess(str3);
      localLoginBean.setUserId(str4);
      localLoginBean.setPassword(str5);
      ((List)localObject).add(localLoginBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(LoginBean paramLoginBean)
  {
    String str1 = paramLoginBean.getErrorInfo();
    String str2 = paramLoginBean.getNickname();
    String str3 = paramLoginBean.getSuccess();
    String str4 = paramLoginBean.getUserId();
    String str5 = paramLoginBean.getPassword();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("ErrorInfo", str1);
    localContentValues.put("nickname", str2);
    localContentValues.put("Success", str3);
    localContentValues.put("userId", str4);
    localContentValues.put("password", str5);
    return this.mSQLiteDatabase.insert("register", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.RegisterOrLoginManager
 * JD-Core Version:    0.6.2
 */