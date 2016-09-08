package cn.yinxm.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * listView 自动滚动测试
 */
public class AutoScrollActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scroll);
        initList();
    }

    public void initList() {
        listView = (ListView) findViewById(R.id.listView);

        final List<String> list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("第" + (i + 1) + "项");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int firstVisiblePosition = listView.getFirstVisiblePosition();
//                int lastVisiblePosition = listView.getLastVisiblePosition();
//                int pagePerNum = lastVisiblePosition - firstVisiblePosition;
//                LogUtil.d("firstVisiblePosition=" + listView.getFirstVisiblePosition() + ", lastVisiblePosition=" + lastVisiblePosition);
//                listView.setSelection(lastVisiblePosition + 1);

                listView.setSelection(listView.getLastVisiblePosition() + 1);//下一页

//                listView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.smoothScrollToPosition(listView.getLastVisiblePosition() + 1);//有问题
//                    }
//                });
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                LogUtil.d("setOnScrollListener scrollState=" + scrollState);
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    LogUtil.d("滚动停止");
                    int lastVisiblePosition = listView.getLastVisiblePosition();
                    if (lastVisiblePosition == list.size()) {
                        LogUtil.d("准备加载下一页");
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.d("onItemSelected position=" + position);
                int lastVisiblePosition = listView.getLastVisiblePosition();
                if (lastVisiblePosition == list.size()) {
                    LogUtil.d("准备加载下一页");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
