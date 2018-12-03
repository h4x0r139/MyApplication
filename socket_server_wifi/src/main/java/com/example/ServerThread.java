package com.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ServerThread implements Runnable {

    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("threadId="+Thread.currentThread().getId());
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;
            while (flag) {
                //接收从客户端发送过来的数据
                String str = buf.readLine();
                if (str == null || "".equals(str)) {
                    flag = false;
                } else {
                    if ("bye".equals(str)) {
                        flag = false;
                    } else {
                        System.out.println("接到指令:" + str);
                        try {
                            String getDeviceData = "{\n" +
                                    "    \"bindStatus\": \"0\",\n" +
                                    "    \"bluetoothConnectStatus\": \"0\",\n" +
                                    "    \"bluetoothStatus\": \"0\",\n" +
                                    "    \"deviceIp\": \"192.168.1.172\",\n" +
                                    "    \"fm\": \"173.3\",\n" +
                                    "    \"gesture\": \"0\",\n" +
                                    "    \"imei\": \"1237568726826198431324\",\n" +
                                    "    \"panelLight\": \"0\",\n" +
                                    "    \"playMode\": \"0\",\n" +
                                    "    \"whiteList\": \"0\",\n" +
                                    "    \"wifiName\": \"workEc点烟器\",\n" +
                                    "    \"wifiPwd\": \"12345678\",\n" +
                                    "    \"wifiStatus\": \"0\"\n" +
                                    "}";


                            Gson gson = new Gson();

                            str = str.substring("<WORK_EC>".length(), str.indexOf("</WORK_EC>"));

                            DefaultWifiRequest defaultWifiRequest = gson.fromJson(str, DefaultWifiRequest.class);
                            DefaultWifiResponse response = new DefaultWifiResponse();
                            response.action = defaultWifiRequest.action;
                            response.code = "0";
                            response.requestCode = defaultWifiRequest.requestCode;
                            response.requestFrom = "4";
                            response.requestTo = "0";
                            response.data = getDeviceData;

                            str = gson.toJson(response);
                            str = "<WORK_EC>" + str + "</WORK_EC>";
                            System.out.println("返回结果:" + str);
                            out.println(str);
                        } catch (Exception e) {

                        }
                    }


                }
            }
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}  