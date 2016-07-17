package cn.itcast.mobilesafe.ui;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
public class DragViewActivity extends Activity {
	private RelativeLayout iv_move_address_show1 = null;
	private int startx; // 记录下来第一次手指触摸屏幕的位置
	private int starty;
	private SharedPreferences sp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_address_show);
		iv_move_address_show1 = (RelativeLayout) findViewById(R.id.iv_move_address_show1);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		iv_move_address_show1.setOnTouchListener(new OnTouchListener() {
			/**
			 * 手指放到RelativeLayout上移动
			 */
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				/**
				 * 手指第一次触摸屏幕的位置
				 */
				case MotionEvent.ACTION_DOWN:
                    startx = (int) event.getRawX();
                    starty = (int) event.getRawY();
					break;

				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getRawX(); 
					int y = (int) event.getRawY();
					//获取手指移动的距离
					int dx = x-startx;
					int dy = y-starty;
					int l= iv_move_address_show1.getLeft();
					int t= iv_move_address_show1.getTop();
					int r = iv_move_address_show1.getRight();
					int b = iv_move_address_show1.getBottom();
					int left = l+dx;
					int top =  t+dy;
					int right = r+dx;
					int below = b+dy;
					//判断上下左右不能移动出屏幕
					if(l+dx<=0){
						left=0;
						right=180;
					}
					if(t+dy<=0){
						top=0;
						below=80;
					}
					if(r+dx>=getWindowManager().getDefaultDisplay().getWidth()){
						right=getWindowManager().getDefaultDisplay().getWidth();
						left=getWindowManager().getDefaultDisplay().getWidth()-180;
					} if(b+dy>= getWindowManager().getDefaultDisplay().getHeight()){
						below= getWindowManager().getDefaultDisplay().getHeight();
						top=getWindowManager().getDefaultDisplay().getHeight()-80;
					}
					iv_move_address_show1.layout(left, top, right, below);
					
					startx = (int) event.getRawX(); // 获取到移动后的位置
					starty = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_UP:
					int lasty = iv_move_address_show1.getTop();
					int lastx = iv_move_address_show1.getLeft();
					System.out.println(lasty);
					System.out.println(lastx);
					Editor editor = sp.edit();
					editor.putInt("lastx", lastx);
					editor.putInt("lasty", lasty);
					editor.commit();
					break;
				}
				return true; // 不会中断触摸事件的返回
			}

		});
	}
	/**
	 * 获取焦点的时候调用的方法
	 */
	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
		int x = sp.getInt("lastx", 0);
		int y = sp.getInt("lasty", 0);
		/**
		 * 获取窗体对象
		 */
		LayoutParams params = (LayoutParams) iv_move_address_show1.getLayoutParams();
		params.leftMargin = x;
		params.topMargin = y;
		iv_move_address_show1.setLayoutParams(params);
	}

}














