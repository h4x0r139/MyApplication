package cn.yinxm.view.leavemsg;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.view.R;

/**
 * 我的留言
 */
public class MyLeaveMsgActivity extends FragmentActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private CommonAdapter<String> mAdapter;
    private List<String> msgList = new ArrayList<>();
    private View base_left_back;
    //动画
    private AnimationDrawable anim_voice;
    View tv_out;

    //test
    MsgPlayProgressView progressView, progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewResource());
        initView();
        initData();
    }

//    @Override
    protected int getViewResource() {
        return R.layout.activity_my_leave_msg;
    }

    public <T extends View> T findView(@IdRes int viewId) {
       return (T)findViewById(viewId);
    }

//    @Override
    protected void initView() {
        recyclerView = findView(R.id.recyclerView);
//        base_left_back = findView(R.id.base_left_back);
        progressView = findView(R.id.progressView);
    }

//    @Override
    protected void initData() {
//        base_left_back.setOnClickListener(this);

        msgList.clear();
        // TODO: 2017/5/16 test data
        for (int i=0; i<22; i++) {
            msgList.add("03:2"+i);
        }
        mAdapter = new CommonAdapter<String>(getApplicationContext(), R.layout.item_leave_msg, msgList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_msg_content, s);
                holder.setText(R.id.tv_msg_content_out, s);
            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                LogUtil.d("onItemClick position="+position+", view="+view+", holder="+holder);

                startPlay(view);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        progressView.setMaxProgress(100);
        progressView.setCurrentProgress(50);
        progressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.setCurrentProgress(progressView.getCurrentProgress()+2);
            }
        });

    }

    public void stopPlay() {
        if (anim_voice != null) {
            anim_voice.selectDrawable(0);
            anim_voice.stop();
        }
        if (tv_out != null) {
            tv_out.setVisibility(View.INVISIBLE);
        }

    }

    public void startPlay(View itemView) {
        if (itemView == null) {
            return;
        }
        //开始之前先停止别的动画
        stopPlay();

        tv_out =  itemView.findViewById(R.id.tv_msg_content_out);
        ImageView img_voice = (ImageView) itemView.findViewById(R.id.img_voice);
        LogUtil.d("img_voice="+img_voice);
        if (img_voice != null) {
//                    anim_voice = (AnimationDrawable) img_voice.getDrawable();
            anim_voice = (AnimationDrawable) img_voice.getBackground();
            LogUtil.d("anim_voice="+anim_voice);
        }

        if (tv_out != null) {
            tv_out.setVisibility(View.VISIBLE);
        }
        itemView.findViewById(R.id.view_lv_msg_item).setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_item_lv_msg_blank));
        progress = (MsgPlayProgressView) itemView.findViewById(R.id.progress);
        progress.setMaxProgress(100);
        progress.setCurrentProgress(0);
        handler.postDelayed(runnable, 300);
        if (anim_voice != null) {
            anim_voice.start();
        }

    }

    private Handler handler = new Handler();


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progress.setCurrentProgress(progress.getCurrentProgress()+1);
//            if (progress.getCurrentProgress() < progress.getMaxProgress()) {
            if (progress.getCurrentProgress() < 30) {
                handler.postDelayed(runnable, 300);
            }
        }
    };

    @Override
    public void onClick(View v) {
//        super.onClick(v);
//        switch (v.getId()) {
//            case R.id.base_left_back:
//                finish();
//                break;
//        }
    }
}
