package com.yinxm.fragment_test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.webview_content,container,false);
        WebView mwebview = (WebView) root.findViewById(R.id.webview);
        mwebview.loadUrl("http://m.pahaoche.com/");
//        return super.onCreateView(inflater, container, savedInstanceState);
        return root;
    }
}
