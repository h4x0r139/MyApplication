package com.yinxm.serversocket_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by H4X0R on 2016/3/6.
 * socket连接对象
 */
public class ChatSocket extends Thread {
    Socket socket;

    public ChatSocket(Socket s) {
        this.socket = s;
    }
    //测试
    public void run1() {//获取输出流
        System.out.println("id="+getId()+",启动一个Chat线程，获取Server输出数据");
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bfwriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            int count = 0;
            while (true) {
//                bfwriter.write("loop:"+count);
//                outputStream.write(new String("test").getBytes());
                bfwriter.write("test"+count++);
                bfwriter.flush();
                sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("id="+getId()+", Chat线程end");
    }
    @Override
    public void run() {//获取输出流
        System.out.println("id="+getId()+",启动一个Chat线程");
        try {
            //读取输入数据，发送个其余终端
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("server接收到数据："+line);
                ChatManager.getChatManager().publish(this,line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("id="+getId()+", Chat线程end");
    }


    public void write(String str) {//输出信息
        System.out.println("write str="+str);
        try {
            socket.getOutputStream().write(str.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
