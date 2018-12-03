package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.receiver.MyAdmin;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Pervention3 extends Activity {
    private SharedPreferences sp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pervention4);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
	}
    public void complete(View v){
    	Editor editor = sp.edit();
    	editor.putBoolean("isProtected", true);
    	editor.commit();
    	DevicePolicyManager manager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);

		ComponentName mAdminName = new ComponentName(this, MyAdmin.class);

		if (!manager.isAdminActive(mAdminName)) {
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
			startActivity(intent);
		}
    	Toast.makeText(this, "�����������", 1).show();
    	
    }
    /**
     * ������˵��õķ���
     */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean isProtected = sp.getBoolean("isProtected", false);
		if(!isProtected){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				Builder builder = new Builder(this);
				builder.setTitle("����");
				builder.setMessage("ǿ�ҽ��鿪���ֻ��������Ƿ�����");
				builder.setCancelable(false);
				builder.setPositiveButton("ȷ��", new OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						Editor editor = sp.edit();
				    	editor.putBoolean("isProtected", true);
				    	editor.commit();
				    	Toast.makeText(Pervention3.this, "�����������", 1).show();
						
					}
					
				});
				builder.setNegativeButton("ȡ��", new OnClickListener(){

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
					
				});
				builder.create().show();
			}
		}
		
		return super.onKeyDown(keyCode, event);
		
	}
    
}














