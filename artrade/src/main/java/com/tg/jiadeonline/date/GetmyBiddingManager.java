package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.myBiddingBean;
import java.util.ArrayList;
import java.util.List;

public class GetmyBiddingManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "myBidding";
  private static final String TAG = "myBidding";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public GetmyBiddingManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("myBidding", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("myBidding", null, null) > 0;
  }

  public List<myBiddingBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("myBidding", null, null, null, null, null, null);
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
      String str1 = localCursor.getString(localCursor.getColumnIndex("carePers"));
      String str2 = localCursor.getString(localCursor.getColumnIndex("currentBid"));
      String str3 = localCursor.getString(localCursor.getColumnIndex("id"));
      String str4 = localCursor.getString(localCursor.getColumnIndex("itemnum"));
      String str5 = localCursor.getString(localCursor.getColumnIndex("picPath"));
      String str6 = localCursor.getString(localCursor.getColumnIndex("timeEnd"));
      String str7 = localCursor.getString(localCursor.getColumnIndex("title"));
      String str8 = localCursor.getString(localCursor.getColumnIndex("totPgs"));
      String str9 = localCursor.getString(localCursor.getColumnIndex("totRecs"));
      myBiddingBean localmyBiddingBean = new myBiddingBean();
      localmyBiddingBean.setId(str3);
      localmyBiddingBean.setCarePers(str1);
      localmyBiddingBean.setCurrentBid(str2);
      localmyBiddingBean.setItemnum(str4);
      localmyBiddingBean.setPicPath(str5);
      localmyBiddingBean.setTimeEnd(str6);
      localmyBiddingBean.setTitle(str7);
      localmyBiddingBean.setTotPgs(str8);
      localmyBiddingBean.setTotRecs(str9);
      ((List)localObject).add(localmyBiddingBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(myBiddingBean parammyBiddingBean)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("carePers", parammyBiddingBean.getCarePers());
    localContentValues.put("currentBid", parammyBiddingBean.getCurrentBid());
    localContentValues.put("id", parammyBiddingBean.getId());
    localContentValues.put("itemnum", parammyBiddingBean.getItemnum());
    localContentValues.put("picPath", parammyBiddingBean.getPicPath());
    localContentValues.put("timeEnd", parammyBiddingBean.getTimeEnd());
    localContentValues.put("title", parammyBiddingBean.getTitle());
    localContentValues.put("totPgs", parammyBiddingBean.getTotPgs());
    localContentValues.put("totRecs", parammyBiddingBean.getTotRecs());
    return this.mSQLiteDatabase.insert("myBidding", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.GetmyBiddingManager
 * JD-Core Version:    0.6.2
 */