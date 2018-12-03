package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class DianServer {
    public static void main(String[] args) {
        System.out.println("点烟器服务器启动,准备接受指令");
        try {
            ServerSocket server = new ServerSocket(18567);
            Socket client = null;
            boolean f = true;
            while (f) {
                //等待客户端的连接，如果没有获取连接
                client = server.accept();
                System.out.println("与客户端连接成功！" + client.getInetAddress());
                //为每个客户端连接开启一个线程
                new Thread(new ServerThread(client)).start();
            }
            server.close();
            System.out.print("点烟器服务器停止,结束");
        } catch (Exception e) {
        }
    }
}