package com.yinxm.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {

    EditText et_weburl;
    TextView tv_text;
    Button bt_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        otherThread.start();

        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = et_weburl.getText().toString();
                if (!(urlStr.startsWith("http://") || urlStr.startsWith("https://"))) {
                    urlStr = "http://" + urlStr;
                }
                readUrl(urlStr);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    Thread otherThread = new Thread(){
       @Override
       public void run() {
           String text = tv_text.getText().toString();//在非UI线程中访问UI是不对的
           System.out.println("otherThread, text="+text);
           tv_text.setText("尹徐明");
       }
   };

    private void readUrl(final String urlStr) {//异步任务执行耗时操作，避免阻塞UI主线程
        AsyncTask<String, Double, String> asyncTask = new AsyncTask<String, Double, String>() {// AsyncTask<Params, Progress, Result>
            @Override
            protected String doInBackground(String... params) {
                System.out.println("doInBackground");
//                String str = null;
//                tv_text.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        str = tv_text.getText().toString();
//                    }
//                });
                StringBuilder stringBuilder = new StringBuilder();

                //打开连接，获取输入流
                try {
                    URL url = new URL(urlStr);

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
                System.out.println("onPreExecute");
                Toast.makeText(MainActivity.this,"开始访问",Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {//doInBackground执行完毕后调用
                System.out.println("onPostExecute "+s);
                tv_text.setText(s);
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(Double... progress) {
                System.out.println("onProgressUpdate "+progress[0]);
                super.onProgressUpdate(progress);
            }

            @Override
            protected void onCancelled(String s) {
                System.out.println("onCancelled(String s)");
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                System.out.println("onCancelled");
                super.onCancelled();
            }
        };
        asyncTask.execute(urlStr);
    }

    private void initView() {
        et_weburl = (EditText) findViewById(R.id.et_weburl);
        tv_text = (TextView) findViewById(R.id.tv_text);
        bt_go = (Button) findViewById(R.id.bt_go);
    }
}
