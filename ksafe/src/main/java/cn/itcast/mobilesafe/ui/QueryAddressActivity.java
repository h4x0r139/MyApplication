package cn.itcast.mobilesafe.ui;

import java.io.File;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.engine.DownLoadFileTask;
import cn.itcast.mobilesafe.engine.NumberAddressService;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class QueryAddressActivity extends Activity {
	private EditText show_address = null;
	private static final int SUCCESS = 10;
	private static final int ERROR = 20;
	private ProgressDialog pd = null;
	private EditText edit_query_address = null;
	//private EditText
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			case SUCCESS:
				Toast.makeText(getApplicationContext(), "下载数据库成功", 0).show();
				break;

			case ERROR:
				Toast.makeText(getApplicationContext(), "下载数据库失败", 0).show();
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.query_address);
		edit_query_address = (EditText) findViewById(R.id.edit_query_address);
		show_address = (EditText) findViewById(R.id.show_address);
		pd = new ProgressDialog(this);
		if (isDB()) {
			edit_query_address.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence s, int start, int before,
						int count) {
				//	System.out.println("onTextChanged=="+et.getText().toString());
				}

				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				//	System.out.println("beforeTextChanged=="+et.getText().toString());
				}

				public void afterTextChanged(Editable s) {
					System.out.println("afterTextChanged=="+edit_query_address.getText().toString());
					String phone = edit_query_address.getText().toString();
					if(phone.length()==7){
						String address = NumberAddressService.getAddress(phone);
						 
							show_address.setText(address);
							return;
						
							
						
					}else if(phone.length()==3){
						String address = NumberAddressService.getAddress(phone);
						String pattern1 = "^[1234567890]\\d{2}$";
						    //如果不是数字开头，就不做输出
							if (!address.matches(pattern1)) {
								show_address.setText(address);
								return;
							}
					}else if(phone.length()==4){
						String address = NumberAddressService.getAddress(phone);
						 String pattern2 = "^[1234567890]\\d{3}$";
						 if (!address.matches(pattern2)) {
								show_address.setText(address);
								return;
							}
					}
					
				}
			});
		} else {
			pd.setTitle("正在下载...");
			pd.setCancelable(false);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		    pd.show();
			
			
				new Thread(){

					@Override
					public void run() {
						String addressURL = getResources().getString(R.string.addressURL);
						DownLoadFileTask df = new DownLoadFileTask();
						try {
							df.getFile(addressURL, "/sdcard/address.db", pd);
						} catch (Exception e) {
							e.printStackTrace();
							pd.dismiss();
							Message msg = new Message();
							msg.what = ERROR;
							handler.sendMessage(msg);
						}
						pd.dismiss();
						Message msg = new Message();
						msg.what = SUCCESS;
						handler.sendMessage(msg);
					}
					
				}.start();
				
			
			
		}
	}

	/**
	 * 判断SD卡上面是否有数据库
	 */
	private boolean isDB() {
		File file = new File(Environment.getExternalStorageDirectory(),
				"address.db");
		return file.exists();
	}
}
