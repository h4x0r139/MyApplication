package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.MunionpayBean;
import java.util.ArrayList;
import java.util.List;

public class MunionpayManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "munionpay";
  private static final String TAG = "munionpay";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public MunionpayManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("munionpay", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("munionpay", null, null) > 0;
  }

  public List<MunionpayBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("munionpay", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("orderId");
      int j = localCursor.getColumnIndex("billNo");
      int k = localCursor.getColumnIndex("payAmount");
      int m = localCursor.getColumnIndex("payTN");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      MunionpayBean localMunionpayBean = new MunionpayBean();
      localMunionpayBean.setBillNo(str2);
      localMunionpayBean.setOrderId(str1);
      localMunionpayBean.setPayAmount(str3);
      localMunionpayBean.setPayTN(str4);
      ((List)localObject).add(localMunionpayBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(MunionpayBean paramMunionpayBean)
  {
    String str1 = paramMunionpayBean.getOrderId();
    String str2 = paramMunionpayBean.getBillNo();
    String str3 = paramMunionpayBean.getPayAmount();
    String str4 = paramMunionpayBean.getPayTN();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("orderId", str1);
    localContentValues.put("billNo", str2);
    localContentValues.put("payAmount", str3);
    localContentValues.put("payTN", str4);
    return this.mSQLiteDatabase.insert("munionpay", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.MunionpayManager
 * JD-Core Version:    0.6.2
 */