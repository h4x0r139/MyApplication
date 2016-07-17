package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.HomePageBean;
import java.util.ArrayList;
import java.util.List;

public class HomePageManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "homepage";
  private static final String TAG = "homepage";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public HomePageManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("homepage", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("homepage", null, null) > 0;
  }

  public List<HomePageBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("homepage", null, null, null, null, null, "cat1+0");
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
      int i = localCursor.getColumnIndex("success");
      int j = localCursor.getColumnIndex("errorInfo");
      int k = localCursor.getColumnIndex("sysTime");
      int m = localCursor.getColumnIndex("bann");
      int n = localCursor.getColumnIndex("bannnum");
      int i1 = localCursor.getColumnIndex("info");
      int i2 = localCursor.getColumnIndex("infonum");
      int i3 = localCursor.getColumnIndex("fCol");
      int i4 = localCursor.getColumnIndex("fColnum");
      int i5 = localCursor.getColumnIndex("cat1");
      int i6 = localCursor.getColumnIndex("cat1num");
      int i7 = localCursor.getColumnIndex("cat2");
      int i8 = localCursor.getColumnIndex("cat2num");
      int i9 = localCursor.getColumnIndex("cat4");
      int i10 = localCursor.getColumnIndex("cat4num");
      int i11 = localCursor.getColumnIndex("cat5");
      int i12 = localCursor.getColumnIndex("cat5num");
      int i13 = localCursor.getColumnIndex("cat6");
      int i14 = localCursor.getColumnIndex("cat6num");
      int i15 = localCursor.getColumnIndex("prim");
      int i16 = localCursor.getColumnIndex("primnum");
      int i17 = localCursor.getColumnIndex("recm");
      int i18 = localCursor.getColumnIndex("recmnum");
      int i19 = localCursor.getColumnIndex("lots");
      int i20 = localCursor.getColumnIndex("lotsnum");
      int i21 = localCursor.getColumnIndex("vwnd");
      int i22 = localCursor.getColumnIndex("vwndnum");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(k);
      String str3 = localCursor.getString(j);
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
      String str21 = localCursor.getString(i16);
      String str22 = localCursor.getString(i17);
      String str23 = localCursor.getString(i18);
      String str24 = localCursor.getString(i19);
      String str25 = localCursor.getString(i20);
      String str26 = localCursor.getString(i21);
      String str27 = localCursor.getString(i22);
      HomePageBean localHomePageBean = new HomePageBean();
      localHomePageBean.setSuccess(str1);
      localHomePageBean.setSysTime(str2);
      localHomePageBean.setErrorInfo(str3);
      localHomePageBean.setBann(str4);
      localHomePageBean.setBannnum(str5);
      localHomePageBean.setInfo(str6);
      localHomePageBean.setInfonum(str7);
      localHomePageBean.setfCol(str8);
      localHomePageBean.setfColnum(str9);
      localHomePageBean.setCat1(str10);
      localHomePageBean.setCat1num(str11);
      localHomePageBean.setCat2(str12);
      localHomePageBean.setCat2num(str13);
      localHomePageBean.setCat4(str14);
      localHomePageBean.setCat4num(str15);
      localHomePageBean.setCat5(str16);
      localHomePageBean.setCat5num(str17);
      localHomePageBean.setCat6(str18);
      localHomePageBean.setCat6num(str19);
      localHomePageBean.setPrim(str20);
      localHomePageBean.setPrimnum(str21);
      localHomePageBean.setRecm(str22);
      localHomePageBean.setRecmnum(str23);
      localHomePageBean.setLots(str24);
      localHomePageBean.setLotsnum(str25);
      localHomePageBean.setVwnd(str26);
      localHomePageBean.setVwndnum(str27);
      ((List)localObject).add(localHomePageBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(HomePageBean paramHomePageBean)
  {
    String str1 = paramHomePageBean.getSuccess();
    String str2 = paramHomePageBean.getSysTime();
    String str3 = paramHomePageBean.getErrorInfo();
    String str4 = paramHomePageBean.getBann();
    String str5 = paramHomePageBean.getBannnum();
    String str6 = paramHomePageBean.getInfo();
    String str7 = paramHomePageBean.getInfonum();
    String str8 = paramHomePageBean.getfCol();
    String str9 = paramHomePageBean.getfColnum();
    String str10 = paramHomePageBean.getCat1();
    String str11 = paramHomePageBean.getCat1num();
    String str12 = paramHomePageBean.getCat2();
    String str13 = paramHomePageBean.getCat2num();
    String str14 = paramHomePageBean.getCat4();
    String str15 = paramHomePageBean.getCat4num();
    String str16 = paramHomePageBean.getCat5();
    String str17 = paramHomePageBean.getCat5num();
    String str18 = paramHomePageBean.getCat6();
    String str19 = paramHomePageBean.getCat6num();
    String str20 = paramHomePageBean.getPrim();
    String str21 = paramHomePageBean.getPrimnum();
    String str22 = paramHomePageBean.getRecm();
    String str23 = paramHomePageBean.getRecmnum();
    String str24 = paramHomePageBean.getLots();
    String str25 = paramHomePageBean.getLotsnum();
    String str26 = paramHomePageBean.getVwnd();
    String str27 = paramHomePageBean.getVwndnum();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("success", str1);
    localContentValues.put("sysTime", str2);
    localContentValues.put("errorInfo", str3);
    localContentValues.put("bann", str4);
    localContentValues.put("bannnum", str5);
    localContentValues.put("info", str6);
    localContentValues.put("infonum", str7);
    localContentValues.put("fCol", str8);
    localContentValues.put("fColnum", str9);
    localContentValues.put("cat1", str10);
    localContentValues.put("cat1num", str11);
    localContentValues.put("cat2", str12);
    localContentValues.put("cat2num", str13);
    localContentValues.put("cat4", str14);
    localContentValues.put("cat4num", str15);
    localContentValues.put("cat5", str16);
    localContentValues.put("cat5num", str17);
    localContentValues.put("cat6", str18);
    localContentValues.put("cat6num", str19);
    localContentValues.put("prim", str20);
    localContentValues.put("primnum", str21);
    localContentValues.put("recm", str22);
    localContentValues.put("recmnum", str23);
    localContentValues.put("lots", str24);
    localContentValues.put("lotsnum", str25);
    localContentValues.put("vwnd", str26);
    localContentValues.put("vwndnum", str27);
    return this.mSQLiteDatabase.insert("homepage", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.HomePageManager
 * JD-Core Version:    0.6.2
 */