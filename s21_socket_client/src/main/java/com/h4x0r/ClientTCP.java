package com.h4x0r;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by FirstQu on 2015/12/13.
 */
public class ClientTCP {
    public static void main(String[] args) {
        try {
            //创建一个Socket对象，指定服务器端的IP地址和端口号
            Socket socket = new Socket("127.0.0.1",14444);
//            Socket socket = new Socket(InetAddress.getLocalHost(),14444);
            //使用InputStream读取硬盘上的文件
            InputStream inputStream = new FileInputStream("D:\\test.txt");
            //从Socket当中得到OutputStream
            OutputStream outputStream = socket.getOutputStream();
            byte buffer [] = new byte[4*1024];
            int temp = 0 ;
            //将InputStream当中的数据取出，并写入到OutputStream当中
            while((temp = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, temp);
            }
            outputStream.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
