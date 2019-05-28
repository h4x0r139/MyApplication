package cn.yinxm.list.rv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void simpleRecyclerView(View view) {}

    public void waterfallsFlow(View view) {
        startActivity(new Intent(HomeActivity.this, WaterfallsFlowActivity.class));
    }

    public void multiLayout(View view) {}
}
