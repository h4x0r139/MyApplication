package com.mshop.appwidget;

import android.app.Activity;
import android.os.Bundle;

public class TargetActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.target_layout);
		System.out.println("TargetActivity onCreate");
	}

	
}
