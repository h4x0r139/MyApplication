package mars.animation07;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private Button removeButton = null;
	private Button addButton = null;
	private ImageView imageView = null;
	private ViewGroup viewGroup = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        removeButton = (Button)findViewById(R.id.removeButtonId);
        imageView = (ImageView)findViewById(R.id.imageViewId);
        removeButton.setOnClickListener(new RemoveButtonListener());
        viewGroup = (ViewGroup)findViewById(R.id.layoutId);
        addButton = (Button)findViewById(R.id.addButtonId);
        addButton.setOnClickListener(new AddButtonListener());
    }
    private class AddButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//创建了一个淡入效果的Animation对象
			AlphaAnimation animation = new AlphaAnimation(0.0f,1.0f);
			animation.setDuration(1000);
			animation.setStartOffset(500);
			//创建一个新的ImageView
			ImageView imageViewAdd = new ImageView(MainActivity.this);
			imageViewAdd.setImageResource(R.drawable.icon);
			//将新的ImageView添加到viewGroup当中
			viewGroup.addView(imageViewAdd, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			//启动动画
			imageViewAdd.startAnimation(animation);
		}
    	
    }
    
    private class RemoveButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//创建一个淡出效果的Animation对象
			AlphaAnimation animation = new AlphaAnimation(1.0f,0.0f);
			//为Animation对象设置属性
			animation.setDuration(1000);
			animation.setStartOffset(500);
			//为Animation对象设置监听器
			animation.setAnimationListener(new RemoveAnimationListener());
			imageView.startAnimation(animation);
		}
    }
    
    private class RemoveAnimationListener implements AnimationListener{
    	//该方法在淡出效果执行结束之后被调用
		@Override
		public void onAnimationEnd(Animation animation) {
			System.out.println("end");
			//从viewGroup当中删除掉imageView控件
			viewGroup.removeView(imageView);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			System.out.println("repeat");
		}

		@Override
		public void onAnimationStart(Animation animation) {
			System.out.println("start");
		}
    	
    }
}