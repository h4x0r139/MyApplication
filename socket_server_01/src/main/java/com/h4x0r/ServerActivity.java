package com.h4x0r;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerActivity extends Activity {
    private Button tcpBtn, udpBtn;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        tcpBtn = (Button) findViewById(R.id.startListenerTCP);
        udpBtn = (Button) findViewById(R.id.startListenerUDP);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        System.out.println("tcpBtn="+tcpBtn);
       tcpBtn.setOnClickListener(new ServerListener());
        udpBtn.setOnClickListener(new ServerListener());
//        tcpBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("匿名 onclick id="+v.getId());
//            }
//        });
    }

    class ServerListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(ServerActivity.this,"onclick",Toast.LENGTH_SHORT);
            System.out.println("onclick id="+v.getId());
           Button btn = (Button)v;
                if (v.getId() == R.id.startListenerTCP) {
                    System.out.println("TCP监听onclick");
                    Log.d("my", "TCP监听onclick");
                    ServerTcpThread thread = new ServerTcpThread();
                    thread.start();
                    Log.d("my", "启动TCP通信端口监听");
                } else if (v.getId() == R.id.startListenerUDP) {
                    ServerUdpThread serverUdpThread = new ServerUdpThread();
                    serverUdpThread.start();
                }
            Log.d("my", "click end");
        }
    }

    Handler updateUIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String str = bundle.getString("resultTextView");
            System.out.println("updateUIHandler, str="+str);
            resultTextView.setText(str);

        }
    };

    class ServerTcpThread extends Thread {
        @Override
        public void run() {
            System.out.println("TCP监听线程run");
//            resultTextView.setText("");//Only the original thread that created a view hierarchy can touch its views 子线程不能直接更新主UI
            Message message = updateUIHandler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("resultTextView", "");
            message.setData(bundle);
            updateUIHandler.sendMessage(message);

            //声明一个ServerSocket对象
            ServerSocket serverSocket = null;
            try {
                //创建一个ServerSocket对象，并让这个Socket在4567端口监听
                serverSocket = new ServerSocket(14444);
                System.out.println("server 端口14444 打开, ip="+ InetAddress.getLocalHost().getHostAddress());

                //阻塞,等待连接
//                while(true) {

                    //调用ServerSocket的accept()方法，接受客户端所发送的请求
                    Socket socket = serverSocket.accept();
                    System.out.println("tcp accept");
                    //从Socket当中得到InputStream对象
                    InputStream inputStream = socket.getInputStream();
                    byte buffer[] = new byte[1024 * 4];
                    int temp = 0;
                    String str = null;
                    //从InputStream当中读取客户端所发送的数据
                    while ((temp = inputStream.read(buffer)) != -1) {
                        str = new String(buffer, 0, temp);
                        resultTextView.append(str);
                        System.out.println(str);
                    }
//                }
            } catch (IOException e) {
                System.out.println("server 异常"+e.getMessage());
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
            System.out.println("ServerTcpThread run end");
        }
    }

    class ServerUdpThread extends  Thread {
        @Override
        public void run() {
            try {
                System.out.println("ServerUdpThread run");
                //创建一个DatagramSocket对象，并指定监听的端口号
                DatagramSocket socket = new DatagramSocket(5555);
                byte data [] = new byte[1024];
                //创建一个空的DatagramPacket对象
                DatagramPacket packet = new DatagramPacket(data,data.length);
                //使用receive方法接收客户端所发送的数据
                socket.receive(packet);
                System.out.println("ServerUdpThread 接受到数据");
                String result = new String(packet.getData(),packet.getOffset(),packet.getLength());
                System.out.println("result--->" + result);
                resultTextView.setText(result);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }




}
