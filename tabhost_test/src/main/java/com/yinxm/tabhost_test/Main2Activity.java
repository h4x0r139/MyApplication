package com.yinxm.tabhost_test;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class Main2Activity extends TabActivity {
    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    private void bindView() {
        tabHost = getTabHost();
    }

}
