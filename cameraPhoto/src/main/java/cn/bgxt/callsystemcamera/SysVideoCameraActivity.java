package cn.bgxt.callsystemcamera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class SysVideoCameraActivity extends Activity {
	private Button btn_StartVideoCamera;
	private static final String FILE_PATH = "/sdcard/sysvideocamera.3gp";
	private static final String TAG="main";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sysvideocamera);

		btn_StartVideoCamera = (Button) findViewById(R.id.btn_StartVideoCamera);
		btn_StartVideoCamera.setOnClickListener(click);
	}

	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction("android.media.action.VIDEO_CAPTURE");
			intent.addCategory("android.intent.category.DEFAULT");
			File file = new File(FILE_PATH);
			if(file.exists()){
				file.delete();
			}
			Uri uri = Uri.fromFile(file);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(intent, 0);
		}
	};
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "拍摄完成，resultCode="+requestCode);
	}

}
