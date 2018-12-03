package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.PreventionItem;
import cn.itcast.mobilesafe.domain.Prevention;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class ProtectedSuccessActivity extends Activity{
	
    private ListView lv_prevention1_item = null;
    private Button bt_pervention = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	
		setContentView(R.layout.prevention_success);
		List<Prevention> list= getProtectedItem();
		PreventionItem preadapter = new PreventionItem(list,this);
		lv_prevention1_item = (ListView) findViewById(R.id.lv_prevention1_item);
		lv_prevention1_item.setAdapter(preadapter);
		
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
	 * 重新设置防盗界面
	 * @param 
	 */
	public void set_Pervention(View v){
		Intent intent = new Intent(this,Pervention1.class);
		startActivity(intent);
	}
}

