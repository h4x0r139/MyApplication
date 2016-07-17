package cn.itcast.mobilesafe.ui;



import java.io.BufferedReader;
import java.io.FileReader;



import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UseActivity extends Activity {
	private PackageManager pm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.use);
		PackageManager localPackageManager = getPackageManager();
	    this.pm = localPackageManager;
	}
	public class ProcessItem {
		String appName;
		int cpu;
		Drawable icon;
		int importance;
		boolean isApplication;
		boolean isSelect;
		int ob_appMemory;
		String packageName;
		int pid;
		float powerInt;

		public ProcessItem(ActivityManager.RunningAppProcessInfo paramString, String arg3)
    {

      
        long l1 = 100L;
        try{
          StringBuilder localStringBuilder = new StringBuilder("/proc/");
          int m = paramString.pid;
          String str3 = m + "/stat";
          FileReader localFileReader = new FileReader(str3);
          String[] arrayOfString = new BufferedReader(localFileReader).readLine().trim().split("\\s+");
          long l2 = Long.parseLong(arrayOfString[13]);
          long l3 = Long.parseLong(arrayOfString[14]);
          long l4 = Long.parseLong(arrayOfString[15]);
          long l5 = Long.parseLong(arrayOfString[16]);
          double d1 = (l2 + l3 + l4 + l5) * l1 * l1;
//          double d2 = this$1.totalTime;
//          float f = (int)(d1 / d2);
//          this.powerInt = f;
          return;
        }catch (Exception e){
          e.printStackTrace();
          return;
        }
    
  
    }

		public View getView(Context paramContext) {
			View localView = View.inflate(UseActivity.this, R.layout.useitem,
					null);
			ImageView localImageView = (ImageView) localView
					.findViewById(2131296346);
			Drawable localDrawable = this.icon;
			localImageView.setImageDrawable(localDrawable);
			TextView localTextView1 = (TextView) localView.findViewById(2131296347);
			String str1 = this.appName;
			localTextView1.setText(str1);
			TextView localTextView2 = (TextView) localView.findViewById(2131296348);
			String str2 = String.valueOf(this.powerInt / 100.0F);
			String str3 = str2 + "%";
			localTextView2.setText(str3);
			return localView;
		}
	}
}