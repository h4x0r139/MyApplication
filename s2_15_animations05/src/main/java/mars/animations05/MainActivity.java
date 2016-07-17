package mars.animations05;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Button button = null;
	private ImageView imageView = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView)findViewById(R.id.imageViewId);
        button = (Button)findViewById(R.id.buttonId);
        button.setOnClickListener(new ButtonListener());
    }
    
    private class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			imageView.setBackgroundResource(R.drawable.anim_nv);
			AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
			animationDrawable.start();
		}
    	
    }
}