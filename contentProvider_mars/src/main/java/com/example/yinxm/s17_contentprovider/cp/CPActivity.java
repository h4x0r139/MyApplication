package com.example.yinxm.s17_contentprovider.cp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.yinxm.s17_contentprovider.R;
import com.example.yinxm.s17_contentprovider.cp.FirstProviderMetaData.UserTableMetaData;

public class CPActivity extends Activity {
	/** Called when the activity is first created. */
	private Button insertButton = null;
	private Button queryButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		queryButton = (Button) findViewById(R.id.query);
		queryButton.setOnClickListener(new QueryListener());
		insertButton = (Button) findViewById(R.id.insert);
		insertButton.setOnClickListener(new InsertListener());
		System.out.println("contentUri="+FirstProviderMetaData.UserTableMetaData.CONTENT_URI);
		System.out.println(getContentResolver().getType(FirstProviderMetaData.UserTableMetaData.CONTENT_URI));
	}

	class InsertListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			ContentValues values = new ContentValues();
			values.put(FirstProviderMetaData.UserTableMetaData.USER_NAME,
					"zhangsan");
			Uri uri = getContentResolver()
					.insert(
							FirstProviderMetaData.UserTableMetaData.CONTENT_URI,
							values);
			System.out.println("uri--->" + uri.toString());
		}

	}

	class QueryListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Cursor c = getContentResolver().query(
					FirstProviderMetaData.UserTableMetaData.CONTENT_URI, null,
					null, null, null);
			while(c.moveToNext()){
				System.out.println(c.getString(c.getColumnIndex(UserTableMetaData.USER_NAME)));
			}
		}

	}
}