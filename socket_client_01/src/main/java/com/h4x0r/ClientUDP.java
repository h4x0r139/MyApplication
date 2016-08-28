package com.h4x0r;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by FirstQu on 2015/12/13.
 */
public class ClientUDP {
    public static void main(String[] args) {
        try {
            //首先创建一个DatagramSocket对象
            DatagramSocket socket = new DatagramSocket(5555);
            //创建一个InetAddree
            InetAddress serverAddress = InetAddress.getByName("172.20.20.10");
            String str = "hello, this is udp";
            byte data [] = str.getBytes();
            //创建一个DatagramPacket对象，并指定要讲这个数据包发送到网络当中的哪个地址，以及端口号
            DatagramPacket packet = new DatagramPacket(data,data.length,serverAddress,4567);
            //调用socket对象的send方法，发送数据
            socket.send(packet);
            System.out.println("客户端发送数据完毕");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
    }
}
