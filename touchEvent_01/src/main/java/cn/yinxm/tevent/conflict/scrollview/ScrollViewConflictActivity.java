package cn.yinxm.tevent.conflict.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.utils.log.LogUtil;
import cn.yinxm.tevent.R;

/**
 * ScrollView + ListView滑动冲突
 * 1、listView内容显示不全，只显示第一行，必须要重写listView measure 方法
 *
 */
public class ScrollViewConflictActivity extends AppCompatActivity {

    ScrollView scrollView;
    ListView listView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_conflict);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        listView = (ListView) findViewById(R.id.listView);

        initData();
    }

    private void initData() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                LogUtil.d("scrollView, w="+listView.getWidth()+", h="+listView.getHeight());
            }
        });
        listView.post(new Runnable() {
            @Override
            public void run() {

                LogUtil.d("listView, w="+listView.getWidth()+", h="+listView.getHeight());

            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("这是第"+(i+1)+"行数据");
        }

        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.item_list, list));

//        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//
//        listView.measure(widthMeasureSpec, heightMeasureSpec);//不起作用
//
//        LogUtil.d("listView1, w="+listView.getWidth()+", h="+listView.getHeight());



    }


}
