package cn.yinxm.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAddActivity extends AppCompatActivity {
    ListView listViewAdd;
    BaseItemDeleteAdapter_Add adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_add);
        listViewAdd = (ListView) findViewById(R.id.listViewAdd);
        List<String> list = new ArrayList<>(5);
        list.add("第1行测试");
        list.add("第2行测试");
        list.add("第3行测试");
        list.add("第4行测试");
        list.add("第5行测试");
        list.addAll(list);
        adapter = new BaseItemDeleteAdapterIml(this, list) {
        };
        listViewAdd.setAdapter(adapter);
        listViewAdd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("yinxm", "长按删除");
                adapter.showDeleteLayout(view);
                return false;
            }
        });
    }
}
