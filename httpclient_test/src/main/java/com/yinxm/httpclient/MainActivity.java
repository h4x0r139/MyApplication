package com.yinxm.httpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et_weburl;
    Button bt_get, bt_post;
    TextView tv_text;

    HttpClient httpClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        httpClient = new DefaultHttpClient();

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
                Map<String,String> map = new HashMap<String,String>();
                map.put("userName", "test");
                map.put("pwd", "test");
                postUrl(urlStr, map, "UTF-8");
            }
        });
    }

    private void getUrl(String urlStr) {

    }

    private void postUrl(String urlStr, Map map, String encode) {

    }

    private void initView() {
        et_weburl = (EditText) findViewById(R.id.et_weburl);
        tv_text = (TextView) findViewById(R.id.tv_text);
        bt_get = (Button) findViewById(R.id.bt_get);
        bt_post = (Button) findViewById(R.id.bt_post);
    }
}
