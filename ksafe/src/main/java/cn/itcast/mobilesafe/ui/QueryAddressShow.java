package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.R.layout;
import cn.itcast.mobilesafe.service.AddressService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class QueryAddressShow extends Activity {
    private TextView tv_query_address_show1 = null;
    private CheckBox cb_query_address_show1 = null;
    private Intent intent = null;
    private LinearLayout ll = null;
    private LinearLayout ll3 = null;
    private RadioButton rb_single_choice_dialog1 = null;
    private RadioButton rb_single_choice_dialog2 = null;
    private RadioButton rb_single_choice_dialog3 = null;
    private RadioButton rb_single_choice_dialog4 = null;
    private RadioButton rb_single_choice_dialog5 = null;
    private SharedPreferences sp = null;
    private Button bt = null;
    private TextView tv_query_address_show_colour = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layout.query_address_show);
		tv_query_address_show1 = (TextView) findViewById(R.id.tv_query_address_show1);
		cb_query_address_show1 = (CheckBox) findViewById(R.id.cb_query_address_show1);
		tv_query_address_show_colour = (TextView) findViewById(R.id.tv_query_address_show_colour);
		ll = (LinearLayout) findViewById(R.id.ll);
		ll3 = (LinearLayout) findViewById(R.id.ll3);
		/**
		 * ��������ʾ��λ��
		 */
		ll3.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent intent = new Intent(QueryAddressShow.this,DragViewActivity.class);
				startActivity(intent);
				
			}
			
		});
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		intent = new Intent(this,AddressService.class);
		/**
		 * ��ѡ��ı�ļ����¼�
		 */
		cb_query_address_show1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					startService(intent);
					tv_query_address_show1.setText("��ѯ��ַ�����ѿ���");
				}else{
					stopService(intent);
					tv_query_address_show1.setText("��ѯ��ַ����δ����");
				}
				
			}
			
		});
		/**
		 * �Զ���Ի���
		 */
		ll.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				final Dialog dialog = new Dialog(QueryAddressShow.this,R.style.MyDialog);
				
				dialog.setCancelable(false);
				View view = View.inflate(QueryAddressShow.this, layout.single_choice_dialog, null);
				bt = (Button) view.findViewById(R.id.bt);
				rb_single_choice_dialog1 = (RadioButton) view.findViewById(R.id.rb_single_choice_dialog1);
				rb_single_choice_dialog2 = (RadioButton) view.findViewById(R.id.rb_single_choice_dialog2);
				rb_single_choice_dialog3 = (RadioButton) view.findViewById(R.id.rb_single_choice_dialog3);
				rb_single_choice_dialog4 = (RadioButton) view.findViewById(R.id.rb_single_choice_dialog4);
				rb_single_choice_dialog5 = (RadioButton) view.findViewById(R.id.rb_single_choice_dialog5);
				/**
				 * �����������ʾ���������ж�SharedPreferences�����Ƿ񱣴�����ɫ�������˾�ȡ����
				 */
				int colour = sp.getInt("colour", 0);
				if(colour==1){
					rb_single_choice_dialog1.setChecked(true);
				}else if(colour==2){
					rb_single_choice_dialog2.setChecked(true);
				}else if(colour==3){
					rb_single_choice_dialog3.setChecked(true);
				}else if(colour==4){
					rb_single_choice_dialog4.setChecked(true);
				}else if(colour==5){
					rb_single_choice_dialog5.setChecked(true);
				}
				bt.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						dialog.dismiss();
						/**
						 * ���ٶԻ�������textview������
						 */
						int colour = sp.getInt("colour", 0);
						if(colour==1){
							rb_single_choice_dialog1.setChecked(true);
							tv_query_address_show_colour.setText("��͸��");
							return;
						}else if(colour==2){
							rb_single_choice_dialog2.setChecked(true);
							tv_query_address_show_colour.setText("������");
							return;
						}else if(colour==3){
							rb_single_choice_dialog3.setChecked(true);
							tv_query_address_show_colour.setText("��ʿ��");
							return;
						}else if(colour==4){
							rb_single_choice_dialog4.setChecked(true);
							tv_query_address_show_colour.setText("������");
							return;
						}else if(colour==5){
							rb_single_choice_dialog5.setChecked(true);
							tv_query_address_show_colour.setText("ƻ����");
							return;
						}
						
						
					}
					
				});
				rb_single_choice_dialog1.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						if(rb_single_choice_dialog1.isChecked()){
							rb_single_choice_dialog2.setChecked(false);
							rb_single_choice_dialog3.setChecked(false);
							rb_single_choice_dialog4.setChecked(false);
							rb_single_choice_dialog5.setChecked(false);
						}
						Editor editor = sp.edit();
						editor.putInt("colour", 1);
						editor.commit();
						
						return;
					}
					
				});
				rb_single_choice_dialog2.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						if(rb_single_choice_dialog2.isChecked()){
							rb_single_choice_dialog1.setChecked(false);
							rb_single_choice_dialog3.setChecked(false);
							rb_single_choice_dialog4.setChecked(false);
							rb_single_choice_dialog5.setChecked(false);
						}
						Editor editor = sp.edit();
						editor.putInt("colour", 2);
						editor.commit();
						return;
					}
					
				});
				rb_single_choice_dialog3.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						if(rb_single_choice_dialog3.isChecked()){
							rb_single_choice_dialog2.setChecked(false);
							rb_single_choice_dialog1.setChecked(false);
							rb_single_choice_dialog4.setChecked(false);
							rb_single_choice_dialog5.setChecked(false);
						}
						Editor editor = sp.edit();
						editor.putInt("colour", 3);
						editor.commit();
						return;
					}
					
				});
				rb_single_choice_dialog4.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						if(rb_single_choice_dialog4.isChecked()){
							rb_single_choice_dialog2.setChecked(false);
							rb_single_choice_dialog3.setChecked(false);
							rb_single_choice_dialog1.setChecked(false);
							rb_single_choice_dialog5.setChecked(false);
						}
						Editor editor = sp.edit();
						editor.putInt("colour", 4);
						editor.commit();
						return;
					}
					
				});
				rb_single_choice_dialog5.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						if(rb_single_choice_dialog5.isChecked()){
							rb_single_choice_dialog2.setChecked(false);
							rb_single_choice_dialog3.setChecked(false);
							rb_single_choice_dialog4.setChecked(false);
							rb_single_choice_dialog1.setChecked(false);
						}
						Editor editor = sp.edit();
						editor.putInt("colour", 5);
						editor.commit();
						return;
					}
					
				});
				dialog.setContentView(view);
				dialog.show();
			}
			
		});
		
	}

}
