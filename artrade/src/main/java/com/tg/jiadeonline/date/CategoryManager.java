package com.tg.jiadeonline.date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.tg.jiadeonline.bean.CategoryBean;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager
{
  private static final String DB_NAME = "JiaDe";
  private static final int DB_VERSION = 1;
  public static final String ID = "_id";
  private static final String TABLE_NAME = "category";
  private static final String TAG = "category";
  private Context mContext = null;
  private SQLiteDatabase mSQLiteDatabase = null;
  private JiaDeSQLiteOpenHelper mSqLiteOpenHelper = null;

  public CategoryManager(Context paramContext)
  {
    this.mContext = paramContext;
    Log.i("category", "UserDataManager construction!");
  }

  public void closeDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper.close();
  }

  public boolean deleteAllDatas()
  {
    return this.mSQLiteDatabase.delete("category", null, null) > 0;
  }

  public List<CategoryBean> fetchAllDatas()
  {
    Cursor localCursor = this.mSQLiteDatabase.query("category", null, null, null, null, null, null);
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
      int i = localCursor.getColumnIndex("catId");
      int j = localCursor.getColumnIndex("catName");
      int k = localCursor.getColumnIndex("cdlETime");
      int m = localCursor.getColumnIndex("cdlSTime");
      int n = localCursor.getColumnIndex("cpicpath");
      int i1 = localCursor.getColumnIndex("isDisp");
      int i2 = localCursor.getColumnIndex("sysTime");
      String str1 = localCursor.getString(i);
      String str2 = localCursor.getString(j);
      String str3 = localCursor.getString(k);
      String str4 = localCursor.getString(m);
      String str5 = localCursor.getString(n);
      String str6 = localCursor.getString(i1);
      String str7 = localCursor.getString(i2);
      CategoryBean localCategoryBean = new CategoryBean();
      localCategoryBean.setCatId(str1);
      localCategoryBean.setCatName(str2);
      localCategoryBean.setCdlETime(str3);
      localCategoryBean.setCdlSTime(str4);
      localCategoryBean.setCpicpath(str5);
      localCategoryBean.setIsDisp(str6);
      localCategoryBean.setSysTime(str7);
      ((List)localObject).add(localCategoryBean);
      localCursor.moveToNext();
    }
  }

  public long insertData(CategoryBean paramCategoryBean)
  {
    String str1 = paramCategoryBean.getCatId();
    String str2 = paramCategoryBean.getCatName();
    String str3 = paramCategoryBean.getCpicpath();
    String str4 = paramCategoryBean.getCdlSTime();
    String str5 = paramCategoryBean.getCdlETime();
    String str6 = paramCategoryBean.getIsDisp();
    String str7 = paramCategoryBean.getSysTime();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("catId", str1);
    localContentValues.put("catName", str2);
    localContentValues.put("cpicpath", str3);
    localContentValues.put("cdlSTime", str4);
    localContentValues.put("cdlETime", str5);
    localContentValues.put("isDisp", str6);
    localContentValues.put("sysTime", str7);
    return this.mSQLiteDatabase.insert("category", "_id", localContentValues);
  }

  public void openDataBase()
    throws SQLException
  {
    this.mSqLiteOpenHelper = new JiaDeSQLiteOpenHelper(this.mContext, "JiaDe", null, 1);
    this.mSQLiteDatabase = this.mSqLiteOpenHelper.getWritableDatabase();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.date.CategoryManager
 * JD-Core Version:    0.6.2
 */