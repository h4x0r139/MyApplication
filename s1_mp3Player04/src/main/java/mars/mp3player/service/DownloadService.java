package mars.mp3player.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import mars.download.HttpDownloader;
import mars.model.Mp3Info;

public class DownloadService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	//每次用户点击ListActivity当中的一个条目时，就会调用该方法
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		//从Intent对象当中将Mp3Info对象取出
		Mp3Info mp3Info = (Mp3Info)intent.getSerializableExtra("mp3Info");
		//生成一个下载线程，并将Mp3Info对象作为参数传递到线程对象当中
		DownloadThread downloadThread = new DownloadThread(mp3Info);
		//启动新线程
		Thread thread = new Thread(downloadThread);
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	class DownloadThread implements Runnable{
		private Mp3Info mp3Info = null;
		public DownloadThread(Mp3Info mp3Info){
			this.mp3Info = mp3Info;
		}
		@Override
		public void run() {
			//下载地址http://192.168.1.107:8081/mp3/a1.mp3
			//根据MP3文件的名字，生成下载地址
			String mp3Url = "http://localhost:8080/mp3/" + mp3Info.getMp3Name();
			//生成下载文件所用的对象
			HttpDownloader httpDownloader = new HttpDownloader();
			//将文件下载下来，并存储到SDCard当中
			int result = httpDownloader.downFile(mp3Url, "mp3/", mp3Info.getMp3Name());
			String resultMessage = null;
			if(result == -1){
				resultMessage = "下载失败";
			}
			else if(result == 0){
				resultMessage = "文件已经存在，不需要重复下载";
			}
			else if(result == 1){
				resultMessage = "文件下载成功";
			}
			//使用Notification提示客户下载结果
		}
		
	}

}
