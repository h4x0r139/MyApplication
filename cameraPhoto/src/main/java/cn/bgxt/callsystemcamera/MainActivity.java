package cn.bgxt.callsystemcamera;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn_bySysCamera, btn_bySysVideoCamera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_bySysCamera = (Button) findViewById(R.id.btn_bySysCamera);
		btn_bySysVideoCamera = (Button) findViewById(R.id.btn_bySysVideoCamera);

		btn_bySysCamera.setOnClickListener(click);
		btn_bySysVideoCamera.setOnClickListener(click);
	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent=null;
			switch (v.getId()) {
			case R.id.btn_bySysCamera:
				intent = new Intent(MainActivity.this, SysCameraActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_bySysVideoCamera:
				intent = new Intent(MainActivity.this, SysVideoCameraActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}

		}
	};

}
