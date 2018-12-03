package cn.yinxm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yinxm on 2016/8/17.
 */
public class WifiDbHelper extends SQLiteOpenHelper {
    public WifiDbHelper(Context context) {
        super(context, WifiDbConstant.DB_NAME, null, WifiDbConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WifiDbConstant.CREATE_TABLE_DEVICE_CONFIG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
