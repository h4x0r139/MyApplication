package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.PaymentOrderBean;
import java.util.ArrayList;
import java.util.List;

public class PaymentOrderManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "paymentorder";
  private static final String TAG = "paymentorder";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public PaymentOrderManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("paymentorder", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public void createTable()
  {
    String str = "CREATE TABLE " + "paymentorder" + " (" + "_id" + " integer primary key," + "buyerId" + " varchar," + "datePay" + " varchar," + "description" + " varchar," + "freightCompName" + " varchar," + "freightOrderId" + " varchar," + "fsFee" + " varchar," + "fsId" + " varchar," + "isPaySucss" + " varchar," + "itemData" + " varchar," + "logisticsId" + " varchar," + "ofsFee" + " varchar," + "ofsId" + " varchar," + "order_status" + " varchar," + "orderId" + " varchar," + "payType" + " varchar," + "toatlCertifycateFee" + " varchar," + "totalAamount" + " varchar," + "totalCommission" + " varchar," + "invoice" + " varchar," + "transportFee" + " varchar);";
    this.mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "paymentorder" + ";");
    this.mSQLiteDatabase.execSQL(str);
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("paymentorder", null, null) > 0;
  }

  public List<PaymentOrderBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("paymentorder", null, null, null, null, null, null);
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
      int j = localCursor.getColumnIndex("payType");
      int k = localCursor.getColumnIndex("buyerId");
      int m = localCursor.getColumnIndex("totalAamount");
      int n = localCursor.getColumnIndex("totalCommission");
      int i1 = localCursor.getColumnIndex("toatlCertifycateFee");
      int i2 = localCursor.getColumnIndex("transportFee");
      int i3 = localCursor.getColumnIndex("logisticsId");
      int i4 = localCursor.getColumnIndex("freightOrderId");
      int i5 = localCursor.getColumnIndex("freightCompName");
      int i6 = localCursor.getColumnIndex("isPaySucss");
      int i7 = localCursor.getColumnIndex("order_status");
      int i8 = localCursor.getColumnIndex("description");
      int i9 = localCursor.getColumnIndex("fsFee");
      int i10 = localCursor.getColumnIndex("fsId");
      int i11 = localCursor.getColumnIndex("ofsId");
      int i12 = localCursor.getColumnIndex("ofsFee");
      int i13 = localCursor.getColumnIndex("datePay");
      int i14 = localCursor.getColumnIndex("itemData");
      int i15 = localCursor.getColumnIndex("invoice");
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
      String str12 = localCursor.getString(i7);
      String str13 = localCursor.getString(i8);
      String str14 = localCursor.getString(i9);
      String str15 = localCursor.getString(i10);
      String str16 = localCursor.getString(i11);
      String str17 = localCursor.getString(i12);
      String str18 = localCursor.getString(i13);
      String str19 = localCursor.getString(i14);
      String str20 = localCursor.getString(i15);
      PaymentOrderBean localPaymentOrderBean = new PaymentOrderBean();
      localPaymentOrderBean.setOrderId(str1);
      localPaymentOrderBean.setPayType(str2);
      localPaymentOrderBean.setBuyerId(str3);
      localPaymentOrderBean.setTotalAamount(str4);
      localPaymentOrderBean.setTotalCommission(str5);
      localPaymentOrderBean.setToatlCertifycateFee(str6);
      localPaymentOrderBean.setTransportFee(str7);
      localPaymentOrderBean.setLogisticsId(str8);
      localPaymentOrderBean.setFreightOrderId(str9);
      localPaymentOrderBean.setFreightCompName(str10);
      localPaymentOrderBean.setIsPaySucss(str11);
      localPaymentOrderBean.setOrder_status(str12);
      localPaymentOrderBean.setDescription(str13);
      localPaymentOrderBean.setFsFee(str14);
      localPaymentOrderBean.setFsId(str15);
      localPaymentOrderBean.setOfsId(str16);
      localPaymentOrderBean.setOfsFee(str17);
      localPaymentOrderBean.setDatePay(str18);
      localPaymentOrderBean.setItemData(str19);
      localPaymentOrderBean.setInvoice(str20);
      ((List)localObject).add(localPaymentOrderBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(PaymentOrderBean paramPaymentOrderBean)
  {
    String str1 = paramPaymentOrderBean.getOrderId();
    String str2 = paramPaymentOrderBean.getPayType();
    String str3 = paramPaymentOrderBean.getBuyerId();
    String str4 = paramPaymentOrderBean.getTotalAamount();
    String str5 = paramPaymentOrderBean.getTotalCommission();
    String str6 = paramPaymentOrderBean.getToatlCertifycateFee();
    String str7 = paramPaymentOrderBean.getTransportFee();
    String str8 = paramPaymentOrderBean.getLogisticsId();
    String str9 = paramPaymentOrderBean.getFreightOrderId();
    String str10 = paramPaymentOrderBean.getFreightCompName();
    String str11 = paramPaymentOrderBean.getIsPaySucss();
    String str12 = paramPaymentOrderBean.getOrder_status();
    String str13 = paramPaymentOrderBean.getDescription();
    String str14 = paramPaymentOrderBean.getFsFee();
    String str15 = paramPaymentOrderBean.getFsId();
    String str16 = paramPaymentOrderBean.getOfsId();
    String str17 = paramPaymentOrderBean.getOfsFee();
    String str18 = paramPaymentOrderBean.getDatePay();
    String str19 = paramPaymentOrderBean.getItemData();
    String str20 = paramPaymentOrderBean.getInvoice();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("orderId", str1);
    localContentValues.put("payType", str2);
    localContentValues.put("buyerId", str3);
    localContentValues.put("totalAamount", str4);
    localContentValues.put("totalCommission", str5);
    localContentValues.put("toatlCertifycateFee", str6);
    localContentValues.put("transportFee", str7);
    localContentValues.put("logisticsId", str8);
    localContentValues.put("freightOrderId", str9);
    localContentValues.put("freightCompName", str10);
    localContentValues.put("isPaySucss", str11);
    localContentValues.put("order_status", str12);
    localContentValues.put("description", str13);
    localContentValues.put("fsFee", str14);
    localContentValues.put("fsId", str15);
    localContentValues.put("ofsId", str16);
    localContentValues.put("ofsFee", str17);
    localContentValues.put("datePay", str18);
    localContentValues.put("itemData", str19);
    localContentValues.put("invoice", str20);
    return this.mSQLiteDatabase.insert("paymentorder", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.PaymentOrderManager
 * JD-Core Version:    0.6.2
 */