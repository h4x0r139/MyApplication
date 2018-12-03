package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.AddressBean;
import java.util.ArrayList;
import java.util.List;

public class ToAddrListManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "address";
  private static final String TAG = "address";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public ToAddrListManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("address", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("address", null, null) > 0;
  }

  public List<AddressBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("address", null, null, null, null, null, null);
    ArrayList localArrayList = new ArrayList();
    localCursor.moveToFirst();
    while (true)
    {
      if (localCursor.isAfterLast())
      {
        localCursor.close();
        return localArrayList;
      }
      if (localArrayList == null)
        localArrayList = new ArrayList();
      int i = localCursor.getColumnIndex("autoid");
      int j = localCursor.getColumnIndex("prov");
      int k = localCursor.getColumnIndex("city");
      int m = localCursor.getColumnIndex("county");
      int n = localCursor.getColumnIndex("address");
      int i1 = localCursor.getColumnIndex("zipcode");
      int i2 = localCursor.getColumnIndex("tel");
      int i3 = localCursor.getColumnIndex("email");
      int i4 = localCursor.getColumnIndex("isDefault");
      int i5 = localCursor.getColumnIndex("name");
      int i6 = localCursor.getColumnIndex("identityCard");
      int i7 = localCursor.getColumnIndex("icPicUp");
      int i8 = localCursor.getColumnIndex("icPicDown");
      int i9 = localCursor.getColumnIndex("qq");
      int i10 = localCursor.getColumnIndex("isuse");
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
      AddressBean localAddressBean = new AddressBean();
      if (str1 == null)
        str1 = "";
      localAddressBean.setAutoid(str1);
      if (str2 == null)
        str2 = "";
      localAddressBean.setProv(str2);
      if (str3 == null)
        str3 = "";
      localAddressBean.setCity(str3);
      if (str4 == null)
        str4 = "";
      localAddressBean.setCounty(str4);
      if (str5 == null)
        str5 = "";
      localAddressBean.setAddress(str5);
      if (str6 == null)
        str6 = "";
      localAddressBean.setZipcode(str6);
      if (str7 == null)
        str7 = "";
      localAddressBean.setTel(str7);
      if (str8 == null)
        str8 = "";
      localAddressBean.setEmail(str8);
      if (str9 == null)
        str9 = "";
      localAddressBean.setIsDefault(str9);
      if (str10 == null)
        str10 = "";
      localAddressBean.setName(str10);
      if (str11 == null)
        str11 = "";
      localAddressBean.setIdentityCard(str11);
      if (str12 == null)
        str12 = "";
      localAddressBean.setIcPicUp(str12);
      if (str13 == null)
        str13 = "";
      localAddressBean.setIcPicDown(str13);
      if (str14 == null)
        str14 = "";
      localAddressBean.setQq(str14);
      if (str15 == null)
        str15 = "";
      localAddressBean.setIsuse(str15);
      localArrayList.add(localAddressBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(AddressBean paramAddressBean)
  {
    String str1 = paramAddressBean.getAutoid();
    String str2 = paramAddressBean.getProv();
    String str3 = paramAddressBean.getCity();
    String str4 = paramAddressBean.getCounty();
    String str5 = paramAddressBean.getAddress();
    String str6 = paramAddressBean.getZipcode();
    String str7 = paramAddressBean.getEmail();
    String str8 = paramAddressBean.getTel();
    String str9 = paramAddressBean.getIsDefault();
    String str10 = paramAddressBean.getName();
    String str11 = paramAddressBean.getIdentityCard();
    String str12 = paramAddressBean.getIcPicUp();
    String str13 = paramAddressBean.getIcPicDown();
    String str14 = paramAddressBean.getQq();
    String str15 = paramAddressBean.getIsuse();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("autoid", str1);
    localContentValues.put("prov", str2);
    localContentValues.put("city", str3);
    localContentValues.put("county", str4);
    localContentValues.put("address", str5);
    localContentValues.put("zipcode", str6);
    localContentValues.put("tel", str8);
    localContentValues.put("email", str7);
    localContentValues.put("isDefault", str9);
    localContentValues.put("name", str10);
    localContentValues.put("identityCard", str11);
    localContentValues.put("icPicUp", str12);
    localContentValues.put("icPicDown", str13);
    localContentValues.put("qq", str14);
    localContentValues.put("isuse", str15);
    return this.mSQLiteDatabase.insert("address", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.ToAddrListManager
 * JD-Core Version:    0.6.2
 */