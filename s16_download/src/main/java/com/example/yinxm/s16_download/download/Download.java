package com.example.yinxm.s16_download.download;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.yinxm.s16_download.R;
import com.example.yinxm.s16_download.utils.HttpDownloader;

public class Download extends Activity {
    /** Called when the activity is first created. */
    private Button downloadTxtButton;
    private Button downloadMp3Button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        downloadTxtButton = (Button)findViewById(R.id.downloadTxt);
        downloadTxtButton.setOnClickListener(new DownloadTxtListener());
        downloadMp3Button = (Button)findViewById(R.id.downloadMp3);
        downloadMp3Button.setOnClickListener(new DownloadMp3Listener());
    }
    
    class DownloadTxtListener implements OnClickListener{

		@Override
		public void onClick(View v) {//Android4以后不能在UI主线程中下载文件

            Runnable downloadTextThread = new Runnable() {
                @Override
                public void run() {
                    HttpDownloader httpDownloader = new HttpDownloader();
                    System.out.println("DownloadText");
                    /*String lrc = httpDownloader.download("http://192.168.1.107:8080/voa1500/a1.lrc");*/
                   /* String str = httpDownloader.download("http://www.cnlyric.com/LrcDown/2807/231719.lrc");*/
                    String str = httpDownloader.download("http://www.sqlite.org/2015/sqlite-doc-3090200.zip");
                    System.out.println("str="+str);
                }
            };

            Thread thread1 = new Thread(downloadTextThread);
            thread1.start();
		}
    	
    }



    class DownloadMp3Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
            Runnable downzip = new Runnable() {
                @Override
                public void run() {
                    HttpDownloader httpDownloader = new HttpDownloader();
                    //int result = httpDownloader.downFile("http://192.168.1.107:8080/voa1500/a1.mp3", "voa/", "a1.mp3");
                    int result = httpDownloader.downFile("http://www.sqlite.org/2015/sqlite-doc-3090200.zip","","test.zip");
                    System.out.println(result);
                }
            };

            Thread thread2 = new Thread(downzip);
            thread2.start();

		}
    	
    }
    
}