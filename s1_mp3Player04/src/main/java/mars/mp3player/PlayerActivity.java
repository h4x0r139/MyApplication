package mars.mp3player;

import java.io.File;

import mars.model.Mp3Info;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PlayerActivity extends Activity{
	ImageButton beginButton = null;
	ImageButton pauseButton = null;
	ImageButton stopButton = null;
	MediaPlayer mediaPlayer = null;
	
	private boolean isPlaying = false;
	private boolean isPause = false;
	private boolean isReleased = false;
	
	private Mp3Info mp3Info = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Intent intent = getIntent();
		mp3Info = (Mp3Info)intent.getSerializableExtra("mp3Info");
		beginButton = (ImageButton)findViewById(R.id.begin);
		pauseButton = (ImageButton)findViewById(R.id.pause);
		stopButton = (ImageButton)findViewById(R.id.stop);
		beginButton.setOnClickListener(new BeginButtonListener());
		pauseButton.setOnClickListener(new PauseButtonListener());
		stopButton.setOnClickListener(new StopButtonListener());
	}
	class BeginButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(!isPlaying){
				String path = getMp3Path(mp3Info);
				mediaPlayer = MediaPlayer.create(PlayerActivity.this, Uri.parse("file://" + path));
				mediaPlayer.setLooping(false);
				mediaPlayer.start();
				isPlaying = true;
				isReleased = false;
			}
		}

		
	}
	
	class PauseButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(mediaPlayer != null){
				if(!isReleased){
					if(!isPause){
						mediaPlayer.pause();
						isPause = true;
						isPlaying = true;
					}
					else{
						mediaPlayer.start();
						isPause = false;
					}
				}
			}
		}

		
	}
	
	class StopButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(mediaPlayer != null){
				if(isPlaying){
					if(!isReleased){
						mediaPlayer.stop();
						mediaPlayer.release();
						isReleased = true;
					}
					isPlaying = false;
				}
			}
		}
		
	}
	private String getMp3Path(Mp3Info mp3Info){
		String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
		String path = SDCardRoot + File.separator + "mp3" + File.separator + mp3Info.getMp3Name();
		return path;
	}
}
