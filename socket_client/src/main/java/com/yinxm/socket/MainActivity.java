package com.yinxm.socket;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends Activity {
    EditText et_ip, et_message;
    TextView tv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        System.out.println("UI初始化完成");

        findViewById(R.id.bt_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(et_ip.getText().toString());
            }
        });
        findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    private void initView() {
        et_ip = (EditText) findViewById(R.id.et_ip);
        et_message = (EditText) findViewById(R.id.et_message);
        tv_text = (TextView) findViewById(R.id.tv_text);
    }

    Socket socket = null;
    BufferedReader bfreader = null;
    BufferedWriter bfwriter = null;
    public void connect(String ip) {

            //使用线程控制数据读写
            AsyncTask<String, String, Void> read = new AsyncTask<String, String, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected Void doInBackground(String... params) {
                    try {
                        //建立连接
//                        socket = new Socket(et_ip.getText().toString(),14445);//不能在后台线程操作UI
                        System.out.println("ip="+params[0]);
                        socket = new Socket(params[0],14445);
                        bfreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bfwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        publishProgress("@success");
                    }  catch (UnknownHostException e) {
                        Toast.makeText(MainActivity.this,"无法建立连接", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this,"无法建立连接", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    String line;
                    try {
                        while ((line = bfreader.readLine()) != null) {//读取数据，显示到界面中
                            System.out.println("client read data="+line);
                            publishProgress(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    if ("@success".equals(values[0])) {
                        Toast.makeText(MainActivity.this,"连接成功！", Toast.LENGTH_SHORT).show();
                    }
                    tv_text.append("other："+values[0]+"\n");
                    super.onProgressUpdate(values);
                }
            };
            read.execute(ip);
    }
    public void send() {
        System.out.println("[send start]，"+et_message.getText().toString());
        try {
            tv_text.append("Me："+et_message.getText().toString()+"\n");
            bfwriter.write(et_message.getText().toString()+"\n");
            bfwriter.flush();
            et_message.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[send end]");
    }
}
