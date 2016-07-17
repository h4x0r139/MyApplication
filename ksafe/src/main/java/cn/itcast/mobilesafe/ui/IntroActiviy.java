package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.itcast.mobilesafe.R;



import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActiviy extends Activity {
	/** Called when the activity is first created. */

	

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intro);
	
	}

}
