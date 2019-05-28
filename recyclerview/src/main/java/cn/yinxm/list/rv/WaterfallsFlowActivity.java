package cn.yinxm.list.rv;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 瀑布流效果
 * <p>
 * 1. 设置LayoutManager，new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
 * 2. RecyclerView.Adapter#onBindViewHolder中设置item的动态宽高，setLayoutParams
 *
 * @author yinxuming
 * @date 2019/5/27
 */
public class WaterfallsFlowActivity extends Activity {
    private static final String TAG = "WaterfallsFlowActivity";

    private static final int SPAN_COUNT = 3;



    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfalls_flow);

        mRecyclerView = findViewById(R.id.rv);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new RecyclerViewAdapter(this, createList());
        mRecyclerView.setAdapter(mAdapter);
    }


    private List<Integer> createList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int num = i % SPAN_COUNT;
            if (num == 1) {
                list.add(R.drawable.iv_01);
            } else if (num == 2) {
                list.add(R.drawable.iv_02);
            } else {
                list.add(R.drawable.iv_03);
            }
        }
        return list;
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        Context mContext;
        List<Integer> mList;

        public RecyclerViewAdapter(Context context, @NonNull List<Integer> list) {
            mContext = context.getApplicationContext();
            // 指向同一个对象，又外部保证对象不为空
            mList = list;
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            Log.d(TAG, "onCreateViewHolder viewType=" + viewType);
            CustomViewHolder viewHolder = new CustomViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_staggrid, viewGroup, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int pos) {
            Log.d(TAG, "onBindViewHolder pos=" + pos + ", " + mList.get(pos));

            // 重新显示复用布局
            customViewHolder.mTextView.setText("" + pos);
            customViewHolder.mImageView.setImageResource(mList.get(pos));

            // 瀑布流，重新设置布局宽高
            ViewGroup.LayoutParams layoutParams = customViewHolder.itemView.getLayoutParams();
            if (pos % SPAN_COUNT == 1) {
                layoutParams.height = 400;
            } else {
                layoutParams.height = 300;
            }
            customViewHolder.itemView.setLayoutParams(layoutParams);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
            mImageView = itemView.findViewById(R.id.iv);
        }
    }
}
