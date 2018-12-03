package com.yinxm.serversocket_test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by H4X0R on 2016/3/6.
 */
public class ServerTCP_01 {
    public static void main(String[] args) {
        new ServerListener().start();
        System.out.println("ServerTCP_01 main end");
    }
}

class ServerListener extends  Thread {
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(14445);
            System.out.println("打开监听端口成功，ip="+ InetAddress.getLocalHost().getHostAddress());

            while (true) {
                //阻塞
                Socket socket = serverSocket.accept();//传入一个连接请求
                System.out.println("获取到一个连接请求");
                //每接收一个终端连接请求，将其socket传递给新线程
                //new ChatSocket(socket).start();
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                ChatManager.getChatManager().addChatSocket(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
