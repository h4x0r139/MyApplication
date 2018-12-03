package cn.itcast.mobilesafe.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.itcast.mobilesafe.R;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 清理SD卡缓存
 */

public class CleanSDCardActivity extends Activity {
	private SQLiteDatabase db;
	private ProgressBar progressBar1 = null;
	private TextView tv_cache = null;
	private Button bt = null;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			String text = (String) msg.obj;
//			if(text.equals("恭喜没有垃圾文件")){
//				progressBar1.setVisibility(View.INVISIBLE);
//				tv_cache.setText(text);
//				bt.setVisibility(View.INVISIBLE);
//			}else{
//				bt.setVisibility(View.INVISIBLE);
//				progressBar1.setVisibility(View.INVISIBLE);
//				tv_cache.setVisibility(View.INVISIBLE);
//			}
			
			tv_cache.setText(text);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clean_sdcard);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_cache = (TextView) findViewById(R.id.tv_cache);
		bt = (Button) findViewById(R.id.bt);
		//首先判断有没有数据库存在，没有就下载
		// 文件默认会写到data/data/包名/files/name
		File file = new File("/data/data/cn.itcast.mobilesafe/files/clearpath.db");
		if(!file.exists()){
			copyfile();
		}
	}
	/**
	 * 开始清理缓存文件
	 * @param v
	 */
      public void start(View v){
    	  db = SQLiteDatabase.openDatabase("/data/data/cn.itcast.mobilesafe/files/clearpath.db", null, SQLiteDatabase.OPEN_READONLY);
    	  //获取所有的安装包信息
    	  new Thread(){

			@Override
			public void run() {
				 List<PackageInfo> packinfos = getPackageManager().getInstalledPackages(0);
				 progressBar1.setMax(packinfos.size());
				 int total = 0;
		    	  for(PackageInfo info : packinfos){
		    		  String packname = info.packageName;
		    		  Cursor cursor = db.rawQuery("select filepath from softdetail where apkname=?", new String[]{packname});
		    		  while(cursor.moveToNext()){
		    			  String path = cursor.getString(0);
		    			  File file = new File(Environment.getExternalStorageDirectory(),path);
		    			  deleteDatabase(file);
		    			  try {
								sleep(15000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
		    		  }
		    		  total++;
		    		  progressBar1.setProgress(total);
		    		  cursor.close();
		    		  Message msg = Message.obtain();
		    		  msg.obj = "清除  " + packname;
		    		  handler.sendMessage(msg);
		    	  }
		    	  Message msg = Message.obtain();
					msg.obj = "恭喜没有垃圾文件";
					handler.sendMessage(msg);
					db.close();
			}

		
    		  
    	  }.start();
    	 
      }
	/**
	 * 把src下面的数据表写入到内存中
	 */
    private void copyfile() {
    	try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("clearpath.db");
		   OutputStream fos =	this.openFileOutput("clearpath.db", MODE_PRIVATE);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 删除文件清理缓存
     * @param file
     */
	private void deleteDatabase(File file) {
		if(file.isDirectory()){
			File [] files = file.listFiles();
			for(int i=0;i<files.length;i++){
				deleteDatabase(file);
			}
		}else{
			file.delete();
		}
		
	}
	
}















