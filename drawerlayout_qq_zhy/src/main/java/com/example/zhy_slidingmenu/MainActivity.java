package com.example.zhy_slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity
{
	private SlidingMenu mMenu;

	Button btn_showWindow, btn_dismissWindow;
	PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		btn_showWindow = (Button) findViewById(R.id.btn_showWindow);
		btn_dismissWindow = (Button) findViewById(R.id.btn_dismissWindow);

		btn_showWindow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                popupWindow.showAsDropDown(v);//显示在控件下方
			}
		});

		btn_dismissWindow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
				}
			}
		});

		View content = LayoutInflater.from(this).inflate(R.layout.dialog_speech_input, null);
		popupWindow = new PopupWindow(content, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			popupWindow.showAtLocation(btn_showWindow, Gravity.BOTTOM, 0, 0);
		}
	}

	public void toggleMenu(View view)
	{
		mMenu.toggle();
	}
}
