package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pervention1 extends Activity {
    private Button bt_pervention_next = null;
    private EditText et_pervention_pwd = null;
    private EditText et_pervention_password = null;
    private SharedPreferences sp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pervention2);
		bt_pervention_next = (Button) findViewById(R.id.bt_pervention_next);
		et_pervention_pwd = (EditText) findViewById(R.id.et_pervention_pwd);
		et_pervention_password = (EditText) findViewById(R.id.et_pervention_password);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
	}
	public void next(View v){
    	String pwd = et_pervention_pwd.getText().toString().trim();
    	String password = et_pervention_password.getText().toString().trim();
    	if("".equals(pwd)||"".equals(password)){
    		Toast.makeText(this, "密码或者确认密码不能为空", 1).show();
    		return;
    	}else{
    		if(pwd.equals(password)){
    			Editor editor = sp.edit();
    			editor.putString("pervention_pwd", pwd);
    			editor.commit();
    		}else{
    			Toast.makeText(this, "密码或者确认密码需要一样", 1).show();
    			return;
    		}
    	}
    	Intent intent = new Intent(this,Pervention2.class);
    	startActivity(intent);
    	finish();
    }
   
}
