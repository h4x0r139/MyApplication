package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.AuctionPriceBean;
import java.util.ArrayList;
import java.util.List;

public class AuctionpriceManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "auctionprice";
  private static final String TAG = "auctionprice";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public AuctionpriceManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("auctionprice", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("auctionprice", null, null) > 0;
  }

  public List<AuctionPriceBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("auctionprice", null, null, null, null, null, null);
    Object localObject = null;
    int i = 0;
    localCursor.moveToFirst();
    while (true)
    {
      if (localCursor.isAfterLast());
      do
      {
        localCursor.close();
        return localObject;
        if (localObject == null)
          localObject = new ArrayList();
        int j = localCursor.getColumnIndex("biddingId");
        int k = localCursor.getColumnIndex("biddingPrice");
        int m = localCursor.getColumnIndex("biddingTime");
        int n = localCursor.getColumnIndex("nickname");
        String str1 = localCursor.getString(j);
        String str2 = localCursor.getString(k);
        String str3 = localCursor.getString(m);
        String str4 = localCursor.getString(n);
        AuctionPriceBean localAuctionPriceBean = new AuctionPriceBean();
        localAuctionPriceBean.setBiddingId(str1);
        localAuctionPriceBean.setBiddingPrice(str2);
        localAuctionPriceBean.setBiddingTime(str3);
        localAuctionPriceBean.setNickname(str4);
        ((List)localObject).add(localAuctionPriceBean);
        i++;
      }
      while (i >= 10);
      localCursor.moveToNext();
    }
  }

  public long insertData(AuctionPriceBean paramAuctionPriceBean)
  {
    String str1 = paramAuctionPriceBean.getBiddingId();
    String str2 = paramAuctionPriceBean.getBiddingPrice();
    String str3 = paramAuctionPriceBean.getBiddingTime();
    String str4 = paramAuctionPriceBean.getNickname();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("biddingId", str1);
    localContentValues.put("biddingPrice", str2);
    localContentValues.put("biddingTime", str3);
    localContentValues.put("nickname", str4);
    return this.mSQLiteDatabase.insert("auctionprice", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.AuctionpriceManager
 * JD-Core Version:    0.6.2
 */