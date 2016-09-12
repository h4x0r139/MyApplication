package com.example.yinxm.bc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity extends Activity {
    /** Called when the activity is first created. */
	private Button sendButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testreceiver);
        sendButton = (Button)findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new BroadcastListener());
    }
    class BroadcastListener implements OnClickListener{

		@Override
		public void onClick(View v) {
            System.out.println("onclick");
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_EDIT);

			TestActivity.this.sendBroadcast(intent);
            System.out.println("sendBroadcast");
        }
    	
    }
}