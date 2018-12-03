package mars.animations02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
			Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
			imageView.startAnimation(animation);
		}
	}

	private class ScaleButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.sacle);
			imageView.startAnimation(animation);
		}

	}

	private class AlphaButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			//使用AnimationUtils装载动画设置文件
			Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
			imageView.startAnimation(animation);
		}

	}

	private class TranslateButtonListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			Animation animation = (Animation) AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate);
			imageView.startAnimation(animation);
		}

	}
}