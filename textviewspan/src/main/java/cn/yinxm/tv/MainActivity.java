package cn.yinxm.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ListView listview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        list.add("简单图文混排SimpleTextImgActivity");

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.item_list_view, list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, SimpleTextImgActivity.class));
                        break;
                    case 1:
                        break;

                }
            }
        });
    }
}
