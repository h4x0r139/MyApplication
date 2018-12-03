package com.example.yinxm.s17_contentprovider.cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.yinxm.s17_contentprovider.sqlite3.db.DatabaseHelper;
import com.example.yinxm.s17_contentprovider.cp.FirstProviderMetaData.*;
import java.util.HashMap;

public class FirstContentProvider extends ContentProvider {

	public static final UriMatcher uriMatcher;//检查URI是否符合标准
	public static final int INCOMING_USER_COLLECTION = 1;
	public static final int INCOMING_USER_SINGLE = 2;
	private DatabaseHelper dh;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "/users",
				INCOMING_USER_COLLECTION);
		uriMatcher.addURI(FirstProviderMetaData.AUTHORIY, "/users/#",
				INCOMING_USER_SINGLE);
	}

	public static HashMap<String,String> userProjectionMap;
	static
	{
		userProjectionMap = new HashMap<String,String>();
		userProjectionMap.put(FirstProviderMetaData.UserTableMetaData._ID,UserTableMetaData._ID);
		userProjectionMap.put(UserTableMetaData.USER_NAME, UserTableMetaData.USER_NAME);
	}
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		System.out.println("delete");
		return 0;
	}

	//根据传入的URI，返回该URI所表示的数据类型
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		System.out.println("getType");
		switch(uriMatcher.match(uri)){
			case INCOMING_USER_COLLECTION:
				return UserTableMetaData.CONTENT_TYPE;
			case INCOMING_USER_SINGLE:
				return UserTableMetaData.CONTENT_TYPE_ITEM;
			default:
				throw new IllegalArgumentException("Unknown URI" + uri);
		}
	}

	/**
	 * 该函数的返回值是一个Uri，这个Uri表示的是刚刚使用这个函数所插入的数据
	 * content://mars.cp.FirstContentProvider/users/1
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		System.out.println("insert");
		SQLiteDatabase db = dh.getWritableDatabase();
		long rowId = db.insert(UserTableMetaData.TABLE_NAME, null, values);
		if(rowId > 0){
			Uri insertedUserUri = ContentUris.withAppendedId(UserTableMetaData.CONTENT_URI, rowId);
			//通知监听器，数据已经改变
			getContext().getContentResolver().notifyChange(insertedUserUri, null);
			return insertedUserUri;
		}
		throw new SQLException("Failed to insert row into" + uri);
	}

	//是一个回调方法，所以说在ContentProvider创建的时候执行
	@Override
	public boolean onCreate() {
		//打开数据库
		dh = new DatabaseHelper(getContext(),FirstProviderMetaData.DATABASE_NAME);
		System.out.println("onCreate");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
						String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch(uriMatcher.match(uri)){//查询结果集类型
			case INCOMING_USER_COLLECTION:
				qb.setTables(UserTableMetaData.TABLE_NAME);
				qb.setProjectionMap(userProjectionMap);
				break;
			case INCOMING_USER_SINGLE:
				qb.setTables(UserTableMetaData.TABLE_NAME);
				qb.setProjectionMap(userProjectionMap);
				qb.appendWhere(UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));
				break;
		}
		String orderBy;
		if(TextUtils.isEmpty(sortOrder)){
			orderBy = UserTableMetaData.DEFAULT_SORT_ORDER;
		}
		else{
			orderBy = sortOrder;
		}
		SQLiteDatabase db = dh.getWritableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		System.out.println("query");
		return c;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		System.out.println("update");
		return 0;
	}

}

