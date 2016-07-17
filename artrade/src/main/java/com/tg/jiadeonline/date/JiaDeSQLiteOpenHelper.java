package com.tg.jiadeonline.date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class JiaDeSQLiteOpenHelper extends SQLiteOpenHelper
{
  public static final String ID = "_id";
  private static final String TAG = "UserDataManager";

  public JiaDeSQLiteOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str1 = "CREATE TABLE " + "endofday" + " (" + "_id" + " integer primary key," + "itemnum" + " varchar," + "title" + " varchar," + "picPath" + " varchar," + "timeStart" + " varchar," + "timeEnd" + " varchar," + "sysTime" + " varchar," + "carePers" + " varchar," + "minimumBid" + " varchar," + "currentBid" + " varchar," + "bstatus" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "endofday" + ";");
    paramSQLiteDatabase.execSQL(str1);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str1);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str2 = "CREATE TABLE " + "collectauctionlist" + " (" + "_id" + " integer primary key," + "itemnum" + " varchar," + "title" + " varchar," + "picPath" + " varchar," + "timeStart" + " varchar," + "timeEnd" + " varchar," + "sysTime" + " varchar," + "carePers" + " varchar," + "minimumBid" + " varchar," + "currentBid" + " varchar," + "bstatus" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "collectauctionlist" + ";");
    paramSQLiteDatabase.execSQL(str2);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str2);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str3 = "CREATE TABLE " + "specialfieldlist" + " (" + "_id" + " integer primary key," + "itemnum" + " varchar," + "title" + " varchar," + "picPath" + " varchar," + "timeStart" + " varchar," + "timeEnd" + " varchar," + "sysTime" + " varchar," + "carePers" + " varchar," + "minimumBid" + " varchar," + "catPic" + " varchar," + "currentBid" + " varchar," + "bstatus" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "specialfieldlist" + ";");
    paramSQLiteDatabase.execSQL(str3);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str3);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str4 = "CREATE TABLE " + "auctiondetails" + " (" + "_id" + " integer primary key," + "title" + " varchar," + "picPath" + " varchar," + "timeStart" + " varchar," + "timeEnd" + " varchar," + "sysTime" + " varchar," + "status" + " varchar," + "sPicPath" + " varchar," + "isDelay" + " varchar," + "minimumPrice" + " varchar," + "curBid" + " varchar," + "bidTimes" + " varchar," + "incrementPrice" + " varchar," + "description" + " varchar," + "isCared" + " varchar, " + "aType" + " varchar," + "reservePrice" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "auctiondetails" + ";");
    paramSQLiteDatabase.execSQL(str4);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str4);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str5 = "CREATE TABLE " + "auctionprice" + " (" + "_id" + " integer primary key," + "biddingId" + " varchar," + "biddingPrice" + " varchar," + "biddingTime" + " varchar," + "nickname" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "auctionprice" + ";");
    paramSQLiteDatabase.execSQL(str5);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str5);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str6 = "CREATE TABLE " + "searchitems" + " (" + "_id" + " integer primary key," + "itemnum" + " varchar," + "title" + " varchar," + "picture" + " varchar," + "timeEnd" + " varchar," + "carePers" + " varchar," + "minimumBid" + " varchar," + "currentBid" + " varchar," + "total" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "searchitems" + ";");
    paramSQLiteDatabase.execSQL(str6);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str6);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str7 = "CREATE TABLE " + "items" + " (" + "_id" + " integer primary key," + "itemnum" + " varchar," + "title" + " varchar," + "picPath" + " varchar," + "priceBid" + " varchar," + "commision" + " varchar," + "certFee" + " varchar," + "dateBid" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "items" + ";");
    paramSQLiteDatabase.execSQL(str7);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str7);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str8 = "CREATE TABLE " + "userfs" + " (" + "_id" + " integer primary key," + "forSaleId" + " varchar," + "userFSFee" + " varchar," + "userFSId" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "userfs" + ";");
    paramSQLiteDatabase.execSQL(str8);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str8);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str9 = "CREATE TABLE " + "munionpay" + " (" + "_id" + " integer primary key," + "billNo" + " varchar," + "orderId" + " varchar," + "payAmount" + " varchar," + "payTN" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "munionpay" + ";");
    paramSQLiteDatabase.execSQL(str9);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str9);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str10 = "CREATE TABLE " + "addorder" + " (" + "_id" + " integer primary key," + "netOrderId" + " varchar," + "payMoney" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "addorder" + ";");
    paramSQLiteDatabase.execSQL(str10);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str10);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str11 = "CREATE TABLE " + "paymentorder" + " (" + "_id" + " integer primary key," + "buyerId" + " varchar," + "datePay" + " varchar," + "description" + " varchar," + "freightCompName" + " varchar," + "freightOrderId" + " varchar," + "fsFee" + " varchar," + "fsId" + " varchar," + "isPaySucss" + " varchar," + "itemData" + " varchar," + "logisticsId" + " varchar," + "ofsFee" + " varchar," + "ofsId" + " varchar," + "order_status" + " varchar," + "orderId" + " varchar," + "payType" + " varchar," + "toatlCertifycateFee" + " varchar," + "totalAamount" + " varchar," + "totalCommission" + " varchar," + "invoice" + " varchar," + "transportFee" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "paymentorder" + ";");
    paramSQLiteDatabase.execSQL(str11);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str11);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str12 = "CREATE TABLE " + "category" + " (" + "_id" + " integer primary key," + "catId" + " varchar," + "catName" + " varchar," + "cdlETime" + " varchar," + "cdlSTime" + " varchar," + "cpicpath" + " varchar," + "sysTime" + " varchar," + "isDisp" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "category" + ";");
    paramSQLiteDatabase.execSQL(str12);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str12);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str13 = "CREATE TABLE " + "address" + " (" + "_id" + " integer primary key," + "address" + " varchar," + "autoid" + " varchar," + "city" + " varchar," + "county" + " varchar," + "icPicDown" + " varchar," + "icPicUp" + " varchar," + "identityCard" + " varchar," + "isDefault" + " varchar," + "name" + " varchar," + "prov" + " varchar," + "qq" + " varchar," + "isuse" + " varchar," + "tel" + " varchar," + "email" + " varchar," + "zipcode" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "address" + ";");
    paramSQLiteDatabase.execSQL(str13);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str13);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str14 = "CREATE TABLE " + "channellist" + " (" + "_id" + " integer primary key," + "catId" + " varchar," + "catName" + " varchar," + "sysTime" + " varchar," + "catPicpath" + " varchar," + "orderNum" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "channellist" + ";");
    paramSQLiteDatabase.execSQL(str14);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str14);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str15 = "CREATE TABLE " + "channellist1" + " (" + "_id" + " integer primary key," + "catId" + " varchar," + "catTit" + " varchar," + "catSubTit" + " varchar," + "catPicpath" + " varchar," + "timeStart" + " varchar," + "timeEnd" + " varchar," + "ispar" + " varchar," + "orderNum" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "channellist1" + ";");
    paramSQLiteDatabase.execSQL(str15);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str15);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str16 = "CREATE TABLE " + "register" + " (" + "_id" + " integer primary key," + "ErrorInfo" + " varchar," + "nickname" + " varchar," + "Success" + " varchar," + "password" + " varchar," + "userId" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "register" + ";");
    paramSQLiteDatabase.execSQL(str16);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str16);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str17 = "CREATE TABLE " + "useracc" + " (" + "_id" + " integer primary key," + "id" + " varchar," + "acctType" + " varchar," + "amount" + " varchar," + "op_type" + " varchar," + "payTime" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "useracc" + ";");
    paramSQLiteDatabase.execSQL(str17);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str17);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str18 = "CREATE TABLE " + "homepage" + " (" + "_id" + " integer primary key," + "success" + " varchar," + "sysTime" + " varchar," + "bann" + " varchar," + "bannnum" + " varchar," + "cat1" + " varchar," + "cat1num" + " varchar," + "cat2" + " varchar," + "cat2num" + " varchar," + "cat4" + " varchar," + "cat4num" + " varchar," + "cat5" + " varchar," + "cat5num" + " varchar," + "cat6" + " varchar," + "cat6num" + " varchar," + "errorInfo" + " varchar," + "fCol" + " varchar," + "fColnum" + " varchar," + "info" + " varchar," + "infonum" + " varchar," + "lots" + " varchar," + "lotsnum" + " varchar," + "prim" + " varchar," + "primnum" + " varchar," + "recm" + " varchar," + "vwnd" + " varchar," + "vwndnum" + " varchar," + "recmnum" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "homepage" + ";");
    paramSQLiteDatabase.execSQL(str18);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str18);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str19 = "CREATE TABLE " + "myBidding" + " (" + "_id" + " integer primary key," + "carePers" + " varchar," + "currentBid" + " varchar," + "id" + " varchar," + "itemnum" + " varchar," + "picPath" + " varchar," + "timeEnd" + " varchar," + "title" + " varchar," + "totPgs" + " varchar," + "totRecs" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "myBidding" + ";");
    paramSQLiteDatabase.execSQL(str19);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str19);
    Log.i("UserDataManager", "db.getVersion()=" + paramSQLiteDatabase.getVersion());
    String str20 = "CREATE TABLE " + "zhucetable" + " (" + "_id" + " integer primary key," + "oldtime" + " varchar);";
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "zhucetable" + ";");
    paramSQLiteDatabase.execSQL(str20);
    Log.i("UserDataManager", "db.execSQL(DB_CREATE)");
    Log.e("UserDataManager", str20);
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    Log.i("UserDataManager", "UserSQLOpenHelper onUpgrade");
    onCreate(paramSQLiteDatabase);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.JiaDeSQLiteOpenHelper
 * JD-Core Version:    0.6.2
 */