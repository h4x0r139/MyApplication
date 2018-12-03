package com.example.yinxm.s03_activitytask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
	/** Called when the activity is first created. */
	private Button myButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("FirstActivity ---> onCreate	 ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myButton = (Button) findViewById(R.id.myButton);
		myButton.setOnClickListener(new ButtonListener());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onDestory");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("FirstAcvity --->onStop");
		super.onStop();
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(FirstActivity.this, SecondActivity.class);
			FirstActivity.this.startActivity(intent);
		}

	}

}