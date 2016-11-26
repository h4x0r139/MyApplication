package cn.bgxt.mediarecorderdemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_record, btn_video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn_record = (Button) findViewById(R.id.btn_record);
		btn_video = (Button) findViewById(R.id.btn_video);

		btn_record.setOnClickListener(this);
		btn_video.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.btn_record:
			intent = new Intent(MainActivity.this, RecordActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_video:
			intent = new Intent(MainActivity.this, VideoActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
