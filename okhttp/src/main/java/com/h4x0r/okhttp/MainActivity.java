package com.h4x0r.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {
//    Button btn_okhttp_post;
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_text = (TextView) findViewById(R.id.tv_text);
//        btn_okhttp_post = (Button) findViewById(R.id.btn_okhttp_post);
    }


/*    Runnable runnable_okhttp_post = new Runnable() {
        @Override
        public void run() {
            okhttp_post();
        }
    };*/

    public void okhttp_post_click(View view) {
        //同步请求需要借助子线程
        final String text = tv_text.getText().toString();
        System.out.println("okhttp_postClick "+text);
        new Thread(){
            @Override
            public void run() {
                okhttp_post();
            }
        }.start();
    }

    public void okhttp_post_async_click(View view) {
        //异步请求，直接使用回调方法解决UI主线程通信
        String url = "http://open.kaolafm.com/v1/app/active";
        String jsonParms = "{" +
                "appid:kfxmv5890," +
                "sign:19c858886e2080e0bf3e93c11eb9b7e5," +
                "deviceid:00000000-54b3-e7c7-0000-000046bffd97" +
                "}";
        try {
            post_asynchronous(url, jsonParms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void okhttp_download_click(View view) {

    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String text  =  msg.getData().get("text").toString();
            System.out.println("handleMessage="+text);
            tv_text.append(text+"\n");
        }
    };

    public static final MediaType JSON  = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();



    public void okhttp_post() {
        System.out.println("【okhttp_post do】");
        String url = "http://open.kaolafm.com/v1/app/active";
        String jsonParms = "{" +
                "appid:kfxmv5890," +
                "sign:19c858886e2080e0bf3e93c11eb9b7e5," +
                "deviceid:00000000-54b3-e7c7-0000-000046bffd97" +
                "}";

        String rtn = null;
        try {
//           rtn = post_json(url, jsonParms);
//              rtn = post_keyValue(url, jsonParms);
//            rtn = OkHttpUtil.postForm2();
            rtn = OkHttpUtil.postForm("http://www.baidu.com", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("rtn="+rtn);
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("text", rtn);
        message.setData(bundle);
        handler.sendMessage(message);
    }


    public void get_asynchronous() throws Exception {//get异步请求
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }

    String post_json(String url, String json) throws IOException {//post提交 json参数
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        return response.body().string();
    }

    String post_keyValue(String url, String json) throws IOException {//post提交  键值对

        RequestBody formBody = new FormBody.Builder()
                .add("appid", "kfxmv5890")
                .add("sign", "19c858886e2080e0bf3e93c11eb9b7e5")
                .add("deviceid", "00000000-54b3-e7c7-0000-000046bffd97")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            System.err.println("response="+response.message());
            throw new IOException("Unexpected code " + response);
        }
    }

    void post_asynchronous(String url, String json) throws IOException {//post异步请求
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful())  throw new IOException("Unexpected code " + response);
                String rtn = response.body().string();
//                tv_text.setText(rtn);//不能直接操作UI，还是得借助Handler
            }
        });

    }

    public void getUrl(View view) {
        String tempUrl = ((TextView) findViewById(R.id.et_url)).getText().toString();
        if (StringUtil.isBlank(tempUrl)) {
            tempUrl = "http://192.168.1.63:8080/workEc_web/interface/userServlet?method=register&SCREENSIZE=RKHXmbNe04OAmonbU2QGZw==&APPVERSION=KGKCwxPYERg=&SETTYPE=UjgJaKBeksE=&OSVERSION=n52yULvvOFc=&GUID=CsDYd0ZDGm0=&desKey=DZ6dPSUWGUA=&PLATFORM=DCJ1VsMZEZ0=&IP=33HnGQ4YM2NuE7pkq2QtMQ==&USERNAME=Y6jVMY/PF7hFMe/bSuGJgA==&APPID=6pLzHVyrQ5ud28ekaKRu/Q==&PASSWORD=N8eMkG6tfyM=";
        }
        final String url = tempUrl;
        new Thread(){
            @Override
            public void run() {
                String rtn = OkHttpUtil.getForm(url, null);
                System.out.println("rtn="+rtn);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("text", rtn);
                message.setData(bundle);
                handler.sendMessage(message);            }
        }.start();


    }
}
