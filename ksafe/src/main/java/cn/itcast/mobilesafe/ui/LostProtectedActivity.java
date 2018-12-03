package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.PreventionItem;
import cn.itcast.mobilesafe.domain.MainItem;
import cn.itcast.mobilesafe.domain.Prevention;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LostProtectedActivity extends Activity implements OnClickListener {
	private SharedPreferences sp = null;
	private Dialog dialog = null;
	private Button ok = null;
	private Button cancel = null;
	private EditText first_password = null;
	private EditText first_affirm_password = null;
	private String affirm_password = null;
	private String affirm_pwd = null;
    private Button normal_ok = null;
    private Button normal_cancel = null;
    private EditText normal_password = null;
    private String normal_pwd = null;
    private ListView lv_prevention1_item = null;
    private Button bt_pervention = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean isProtected = sp.getBoolean("isProtected", false);
		if(isProtected){
			// 设置过了，登陆密码
			dialog = new Dialog(this, R.style.MyDialog);
			dialog.setCancelable(false);
			// dialog.setContentView(R.layout.lost_protected);
			View view = View.inflate(this, R.layout.normal_protected, null);
			normal_cancel = (Button) view.findViewById(R.id.normal_cancel);
			normal_ok = (Button) view.findViewById(R.id.normal_ok);
			
			normal_password = (EditText) view.findViewById(R.id.normal_password);
			
			normal_ok.setOnClickListener(this);
			normal_cancel.setOnClickListener(this);
			dialog.setContentView(view);
			dialog.show();
			
		}else{
			if (isPWDStep()) {
				// 设置过了，登陆密码
				dialog = new Dialog(this, R.style.MyDialog);
				dialog.setCancelable(false);
				// dialog.setContentView(R.layout.lost_protected);
				View view = View.inflate(this, R.layout.normal_protected, null);
				normal_cancel = (Button) view.findViewById(R.id.normal_cancel);
				normal_ok = (Button) view.findViewById(R.id.normal_ok);
				
				normal_password = (EditText) view.findViewById(R.id.normal_password);
				
				normal_ok.setOnClickListener(this);
				normal_cancel.setOnClickListener(this);
				dialog.setContentView(view);
				dialog.show();
			} else {

				// 没有设置密码，让用户设置密码
				dialog = new Dialog(this, R.style.MyDialog);
				// dialog.setContentView(R.layout.lost_protected);
				dialog.setCancelable(false);
				View view = View.inflate(this, R.layout.lost_protected, null);
				ok = (Button) view.findViewById(R.id.ok);
				cancel = (Button) view.findViewById(R.id.cancel);
				first_affirm_password = (EditText) view
						.findViewById(R.id.first_affirm_password);
				first_password = (EditText) view.findViewById(R.id.first_password);
				
				ok.setOnClickListener(this);
				cancel.setOnClickListener(this);
				dialog.setContentView(view);
				dialog.show();
			}
			setContentView(R.layout.prevention1);
			List<Prevention> list= getProtectedItem();
			PreventionItem preadapter = new PreventionItem(list,this);
			lv_prevention1_item = (ListView) findViewById(R.id.lv_prevention1_item);
			lv_prevention1_item.setAdapter(preadapter);
			bt_pervention = (Button) findViewById(R.id.bt_pervention);
			bt_pervention.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					Intent intent = new Intent(LostProtectedActivity.this,Pervention1.class);
					startActivity(intent);
				}
				
			});
		}
		}
		
/**
 * 给listview设置防盗条目
 */
	private List<Prevention> getProtectedItem(){
		List<Prevention> listItem = new ArrayList<Prevention>();
		Prevention pi1 = new Prevention(getResources().getDrawable(R.drawable.kn_protection_huanka),"换卡短信通知",getResources().getDrawable(R.drawable.jiantou1));
		listItem.add(pi1);
		Prevention pi2 = new Prevention(getResources().getDrawable(R.drawable.kn_protection_dingwei),"定位手机",getResources().getDrawable(R.drawable.jiantou1));
		listItem.add(pi2);
		Prevention pi3 = new Prevention(getResources().getDrawable(R.drawable.kn_protection_suoding),"锁定拍照",getResources().getDrawable(R.drawable.jiantou1));
		listItem.add(pi3);
		Prevention pi4 = new Prevention(getResources().getDrawable(R.drawable.kn_protection_xiaohui),"销毁数据",getResources().getDrawable(R.drawable.jiantou1));
		listItem.add(pi4);
		Prevention pi5 = new Prevention(getResources().getDrawable(R.drawable.kn_protection_baojing),"发警报音",getResources().getDrawable(R.drawable.jiantou1));
		listItem.add(pi5);
		return listItem;
	}
	/**
	 * 判断用户是否设置了密码
	 */
	private boolean isPWDStep() {
		String pwd = sp.getString("pwd", null);
		if (pwd == null) {
			return false;
		} else {
			if ("".equals(pwd)) {
				return false;
			} else {
				return true;
			}
		}

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ok:
			if ("".equals(affirm_password) || "".equals(affirm_pwd)) {
				Toast.makeText(this, "密码不能为空", 1).show();
				return;
			} else {
				affirm_password = first_affirm_password.getText().toString().trim();
				affirm_pwd = first_password.getText().toString().trim();
				System.out.println("affirm_pwd="+affirm_pwd);
				System.out.println("affirm_password="+affirm_password);
				if (affirm_pwd.equals(affirm_password)) {
					Editor editor = sp.edit();
					editor.putString("pwd", affirm_password);
					editor.commit();
				} else {
					Toast.makeText(this, "两次密码不一样", 1).show();
					return;
				}
			}
			dialog.dismiss();
			break;
		case R.id.cancel:
			dialog.dismiss();
			break;
		case R.id.normal_cancel:
			dialog.dismiss();
			break;
		case R.id.normal_ok:
			if("".equals(normal_password)){
				
				Toast.makeText(this, "密码不能为空", 1).show();
				return;
			}else{
				normal_pwd = normal_password.getText().toString().trim();
				String normal = sp.getString("pwd", null);
				if(normal.equals(normal_pwd)){
					dialog.dismiss();
					
					Intent intent = new Intent(this,ProtectedSuccessActivity.class);
					startActivity(intent);
					finish();
					return;
				}else{
					Toast.makeText(this, "密码错误", 1).show();
				}
			}
			break;
		}
		

	}

}
