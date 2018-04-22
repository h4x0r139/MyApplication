package cn.yinxm.tevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cn.yinxm.tevent.conflict.vp.VpListSlidingConflictActivity;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        String[] items = {"测试Activity与ViewPager滑动事件冲突","事件传递","Main2Activity滑动拦截", "Main3Activity空白页面"
            ,"ViewPager+Fragment+RecyclerView滑动冲突"
        };

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ViewPagerEventActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, Main3Activity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, VpListSlidingConflictActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
