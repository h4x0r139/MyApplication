package com.h4x0r;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketActivity extends Activity {
    /** Called when the activity is first created. */
	private Button startButton = null;
	private TextView resultText = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startButton = (Button)findViewById(R.id.startListener);
		resultText = (TextView) findViewById(R.id.resultText);
        startButton.setOnClickListener(new StartSocketListener());
    }
    
    class StartSocketListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			new ServerThread().start();
		}
    	
    }
    
    class ServerThread extends Thread{
    	public void run(){
    		//声明一个ServerSocket对象
    		ServerSocket serverSocket = null;
    		try {
    			//创建一个ServerSocket对象，并让这个Socket在4567端口监听
				serverSocket = new ServerSocket(4567);
				//调用ServerSocket的accept()方法，接受客户端所发送的请求
				Socket socket = serverSocket.accept();
				//从Socket当中得到InputStream对象
				InputStream inputStream = socket.getInputStream();
				byte buffer [] = new byte[1024*4];
				int temp = 0;
				//从InputStream当中读取客户端所发送的数据
				String str = null;
				while((temp = inputStream.read(buffer)) != -1){
					str = new String(buffer,0,temp);
					System.out.println(str);
					resultText.setText(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
    	}
    	/*public void run(){
    		try {
    			//创建一个DatagramSocket对象，并指定监听的端口号
				DatagramSocket socket = new DatagramSocket(4567);
				byte data [] = new byte[1024];
				//创建一个空的DatagramPacket对象
				DatagramPacket packet = new DatagramPacket(data,data.length);
				//使用receive方法接收客户端所发送的数据
				socket.receive(packet);
				String result = new String(packet.getData(),packet.getOffset(),packet.getLength());
				System.out.println("result--->" + result);
				resultText.setText(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}*/
    }
}