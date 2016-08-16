package com.example.yinxm.s17_contentprovider.cp;

import android.net.Uri;
import android.provider.BaseColumns;

public class FirstProviderMetaData {
	public static final String AUTHORIY = "com.example.yinxm.s17_contentprovider.cp.FirstContentProvider";
	//数据库名称
	public static final String DATABASE_NAME = "FirstProvider.db";
	//数据库的版本
	public static final int DATABASE_VERSION = 1;
	//表名
	public static final String USERS_TABLE_NAME = "users";

	public static final class UserTableMetaData implements BaseColumns{
		//表名
		public static final String TABLE_NAME = "users";
		//访问该ContentProvider的URI
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORIY + "/users");
		//该ContentProvider所返回的数据类型的定义
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.firstprovider.user";
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd.firstprovider.user";//取一条数据
		//列名
		public static final String USER_NAME = "name";
		//默认的排序方法
		public static final String DEFAULT_SORT_ORDER = "_id desc";
	}
}

