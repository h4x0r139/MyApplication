package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Pervention2 extends Activity {
    private SharedPreferences sp = null;
    private EditText et_pervention_pwd = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pervention3);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		et_pervention_pwd = (EditText) findViewById(R.id.et_pervention_pwd);
	}
    public void next(View v){
    	String et_pwd = et_pervention_pwd.getText().toString().trim();
    	
    	if("".equals(et_pwd)){
    		Toast.makeText(this, "�ֻ������ò���Ϊ��", 1).show();
    		return;
    	}else{
    		
    		Editor editor = sp.edit();
        	editor.putString("tel", et_pwd);
        	editor.commit();
        	/**
        	 * ����sim���İ󶨲���
        	 */
        	TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        	//��ȡsim���Ĵ���
    		String simserial = manager.getSimSerialNumber();
    		Editor edi = sp.edit();
    		editor.putString("sim", simserial);
    		editor.commit();
    	}
    	
    	Intent intent = new Intent(this,Pervention3.class);
    	startActivity(intent);
    	//����activity�л�ʱ��Ķ���Ч��
    	overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    	finish();
    }
    public void iv_contact(View v){
    	Intent intent = new Intent(this,SelectContactInfo.class);
    //	startActivity(intent);
    	startActivityForResult(intent, 0);
    }
    /**
     * ����ҳ����ɺ󣬵��õķ���
     */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
			String number = data.getStringExtra("number");
			System.out.println("xxxx"+number);
			et_pervention_pwd.setText(number);
		}
	}
    
}

















