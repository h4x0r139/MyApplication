package mars.animations01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private ImageView imageView = null;
	private Button rotateButton = null;
	private Button scaleButton = null;
	private Button alphaButton = null;
	private Button translateButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageView = (ImageView) findViewById(R.id.imageViewId);

		rotateButton = (Button) findViewById(R.id.rotateButtonId);
		rotateButton.setOnClickListener(new RotateButtonListener());

		scaleButton = (Button) findViewById(R.id.scaleButtonId);
		scaleButton.setOnClickListener(new ScaleButtonListener());

		alphaButton = (Button) findViewById(R.id.alphaButtonId);
		alphaButton.setOnClickListener(new AlphaButtonListener());

		translateButton = (Button) findViewById(R.id.translateButtonId);
		translateButton.setOnClickListener(new TranslateButtonListener());
	}

	private class RotateButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			AnimationSet animationSet = new AnimationSet(true);
			RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
					Animation.RELATIVE_TO_PARENT, 0f,
					Animation.RELATIVE_TO_PARENT, 0.5f);
			rotateAnimation.setDuration(5000);
			animationSet.addAnimation(rotateAnimation);
			imageView.startAnimation(animationSet);
		}
	}

	private class ScaleButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1, 0.1f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setStartOffset(1000);
			animationSet.setFillAfter(true);
			animationSet.setFillBefore(false);//是否回到原来的状态
			animationSet.setDuration(2000);
			imageView.startAnimation(animationSet);
		}

	}

	private class AlphaButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			//创建一个AnimationSet对象
			AnimationSet animationSet = new AnimationSet(true);
			//创建一个AlphaAnimation对象
			AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
			//设置动画执行的时间（单位：毫秒）
			alphaAnimation.setDuration(3000);
			//将AlphaAnimation对象添加到AnimationSet当中
			animationSet.addAnimation(alphaAnimation);
			//使用ImageView的startAnimation方法开始执行动画
			imageView.startAnimation(animationSet);
		}

	}

	private class TranslateButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			AnimationSet animationSet = new AnimationSet(true);
			TranslateAnimation translateAnimation = new TranslateAnimation(
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0f,
					Animation.RELATIVE_TO_SELF, 1.0f);
			translateAnimation.setDuration(1000);
			animationSet.addAnimation(translateAnimation);
			imageView.startAnimation(animationSet);
		}
	}
}