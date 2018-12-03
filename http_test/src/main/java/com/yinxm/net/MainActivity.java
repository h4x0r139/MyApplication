package com.yinxm.net;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et_weburl;
    Button bt_get, bt_post;
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = et_weburl.getText().toString();
                if (!(urlStr.startsWith("http://") || urlStr.startsWith("https://"))) {
                    urlStr = "http://" + urlStr;
                }
                getUrl(urlStr);
            }
        });
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = "http://10.15.26.55:8080/Login/login";
                et_weburl.setText(urlStr);
                Map<String,String> map = new HashMap<String,String> ();
                map.put("userName", "test");
                map.put("pwd", "test");
                postUrl(urlStr, map, "UTF-8");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.d("my", "UI thread id=" + Thread.currentThread().getId());
    }

    private void postUrl(String urlStr, Map<String, String> params, String encode) {
        String paramStr = HttpUtil.getRequestData(params, encode).toString();//map->string:userName=test&pwd=test
        postUrl(urlStr, paramStr, encode);
    }

    private void postUrl(String urlStr, String paramStr, String encode) {
//        打开链接，得到HttpURLConnection对象
//        设置Request Headers
//        connect
//        获取输出流getOutputStream，向服务端写入请求参数
//        获取输入流，得到服务端响应数据
        new AsyncTask<String,Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                System.out.println("doInBackground \nurl="+params[0]+"\n"+params[1]+"\nencode="+params[2]);
                Log.d("my", "AsyncTask.doInBackground thread id=" + Thread.currentThread().getId());

                StringBuilder stringBuilder = new StringBuilder();
                BufferedWriter bufferedWriter = null;
                BufferedReader bufferedReader = null;
                try {
                    //打开链接，得到HttpURLConnection对象
                    URL url = new URL(params[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();//连接对象必须用HttpURLConnection才能设置请求方式
                    //设置Request Headers
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
//                    connection.setDoInput(true);//默认开启，不需要设置
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(30000);
                    //connect
                    connection.connect();//getInputStream默认调用
                    //获取输出流getOutputStream，向服务端写入请求参数
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), params[2]));//Stream,Charset
                    //post请求参数
                    bufferedWriter.write(params[1]);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    System.out.println("post请求参数发出，准备接收数据");

                    //获取输入流，得到服务端响应数据
                    String line = null;
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),  params[2]));
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return stringBuilder.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("my", "AsyncTask.onPostExecute thread id=" + Thread.currentThread().getId()+"\n"+s);
                tv_text.setText(s);
            }
        }.execute(urlStr, paramStr, encode);
    }

    private void getUrl(String urlStr) {
        new AsyncTask<String, Double, String>() {
                @Override
                protected String doInBackground(String... params) {
                    Log.d("my", "getUrl AsyncTask.doInBackground thread id=" + Thread.currentThread().getId()+"\nurl="+params[0]);

                    StringBuilder stringBuilder = new StringBuilder();

                    //打开连接，获取输入流
                    try {
                        URL url = new URL(params[0]);

                        URLConnection urlConnection = url.openConnection();
                        urlConnection.setRequestProperty("Accept-Encoding", "identity");//结局getContentLength 返回-1的问题
                        urlConnection.connect();
                        int total = urlConnection.getContentLength();//获取数据总长度，-1
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String line = null;
                        int lineCount = 0;

                        System.out.println("total length="+total);
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println("lineCount="+lineCount++);
                            stringBuilder.append(line).append("\n");
                            //实时对外更新进度
                            publishProgress((double)stringBuilder.length()/total);
                        }
                        bufferedReader.close();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return stringBuilder.toString();
                }

                @Override
                protected void onPreExecute() {
                    System.out.println("onPreExecute thread id=" + Thread.currentThread().getId());
                    Toast.makeText(MainActivity.this, "开始访问", Toast.LENGTH_SHORT).show();
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(String s) {//doInBackground执行完毕后调用
                    System.out.println("onPostExecute thread id=" + Thread.currentThread().getId()+"\n"+s);
                    tv_text.setText(s);
                    super.onPostExecute(s);
                }

                @Override
                protected void onProgressUpdate(Double... progress) {
                    System.out.println("onProgressUpdate thread id=" + Thread.currentThread().getId()+"\n"+progress[0]);
                    super.onProgressUpdate(progress);
                }

            }.execute(urlStr);
    }

    private void initView() {
        et_weburl = (EditText) findViewById(R.id.et_weburl);
        tv_text = (TextView) findViewById(R.id.tv_text);
        bt_get = (Button) findViewById(R.id.bt_get);
        bt_post = (Button) findViewById(R.id.bt_post);
    }

}
