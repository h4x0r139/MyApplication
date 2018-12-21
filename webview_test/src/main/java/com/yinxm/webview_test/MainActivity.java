package com.yinxm.webview_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openTestWeb(View view) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, "https://m.ximalaya.com/login?fromUri=https%3A%2F%2Fapi.ximalaya.com%2Foauth2%2Fv2%2Fauthorize%3Fclient_id%3Dbea8793e2698cfb6e243d18e9c5c1c46%26response_type%3Dcode%26redirect_uri%3Dhttps%3A%2F%2Fapi.ximalaya.com%2Fopenapi-collector-app%2Fget_access_token%26device_id%3D00000000-50f2-2bb1-ffff-ffffef05ac4a%26client_os_type%3D3%26state%3Dabc");
        startActivity(intent);
    }
}
