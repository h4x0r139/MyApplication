package cn.itcast.mobilesafe.db.dao;

import android.database.sqlite.SQLiteDatabase;
//��Դ����������� http://javaapk.com
public class AddressDao {
     public static SQLiteDatabase getAddressDB(String path){
    	 return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
     }
}
