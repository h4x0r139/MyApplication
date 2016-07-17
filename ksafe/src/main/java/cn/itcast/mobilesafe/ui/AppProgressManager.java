package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class AppProgressManager extends Activity {
    private SharedPreferences sp = null;
    private CheckBox cb_progress_manager = null;
    private CheckBox cb_progress_kill = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_manager);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean showsystem = sp.getBoolean("showsystem", false);
	
		cb_progress_manager = (CheckBox) findViewById(R.id.cb_progress_manager);
		cb_progress_kill = (CheckBox) findViewById(R.id.cb_progress_kill);
		if(showsystem){
			cb_progress_manager.setChecked(true);
		}else{
			cb_progress_manager.setChecked(false);
		}
		boolean killprocess = sp.getBoolean("killprocess", false);
		if(killprocess){
			cb_progress_kill.setChecked(true);
		}else{
			cb_progress_kill.setChecked(false);
		}
		cb_progress_manager.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					Editor editor = sp.edit();
					editor.putBoolean("showsystem", true);
					editor.commit();
					setResult(200);
				}else{
					Editor editor = sp.edit();
					editor.putBoolean("showsystem", false);
					editor.commit();
					setResult(200);
				}
				
			}
			
		});
		cb_progress_kill.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					Editor editor = sp.edit();
					editor.putBoolean("killprocess", true);
					editor.commit();
				}else{
					Editor editor = sp.edit();
					editor.putBoolean("killprocess", false);
					editor.commit();
				}
				
			}
			
		});
	}

}












