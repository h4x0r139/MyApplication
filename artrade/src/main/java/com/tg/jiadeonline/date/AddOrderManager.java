package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.AddOrderBean;
import java.util.ArrayList;
import java.util.List;

public class AddOrderManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "addorder";
  private static final String TAG = "addorder";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public AddOrderManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("addorder", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("addorder", null, null) > 0;
  }

  public List<AddOrderBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("addorder", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("netOrderId");
      int j = localCursor.getColumnIndex("payMoney");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      AddOrderBean localAddOrderBean = new AddOrderBean();
      localAddOrderBean.setNetOrderId(str1);
      localAddOrderBean.setPayMoney(str2);
      ((List)localObject).add(localAddOrderBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(AddOrderBean paramAddOrderBean)
  {
    String str1 = paramAddOrderBean.getNetOrderId();
    String str2 = paramAddOrderBean.getPayMoney();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("netOrderId", str1);
    localContentValues.put("payMoney", str2);
    return this.mSQLiteDatabase.insert("addorder", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.AddOrderManager
 * JD-Core Version:    0.6.2
 */