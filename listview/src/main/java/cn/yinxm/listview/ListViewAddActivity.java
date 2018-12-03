package cn.yinxm.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
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


        final List<String> list = new ArrayList<>(50);
        for (int i=0; i<50; i++) {
            list.add("第"+(i+1)+"行测试");
        }
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

        listViewAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.d("点击item getLastVisiblePosition="+listViewAdd.getLastVisiblePosition());

                listViewAdd.setSelection(listViewAdd.getLastVisiblePosition());
                listViewAdd.smoothScrollBy(1,1);
            }
        });

        listViewAdd.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                LogUtil.d("setOnScrollListener scrollState=" + scrollState);
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    LogUtil.d("滚动停止");
                    int lastVisiblePosition = listViewAdd.getLastVisiblePosition();
                    if (lastVisiblePosition == list.size()) {
                        LogUtil.d("准备加载下一页");
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
