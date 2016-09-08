package cn.com.ecarx.xiaoka.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.example.yinxm.listview.LogUtil;
import com.example.yinxm.listview.R;


/**
 * Created by yinxm on 2016/9/1.
 * ListView 中item长按删除事件处理封装Adapter
 */
public abstract class BaseItemDeleteAdapter_Hide extends BaseAdapter{
    private ViewGroup layout_delete_view;
//    private ViewGroup delete_cancel, delete_ok;

    //刷新deletLayou布局界面
    public  void getView() {
        hideDeleteLayout();
    }

    private void initDeleteView(final int position, ViewGroup layout_delete_view) {
        LogUtil.d("[initDeleteView] position="+position);
        ViewGroup delete_cancel = (ViewGroup) layout_delete_view.findViewById(R.id.rl_item_cancel);
        ViewGroup delete_ok = (ViewGroup) layout_delete_view.findViewById(R.id.rl_item_delete);

        delete_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemDeleteCancel(position);
            }
        });

        delete_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemDeleteOk(position);
            }
        });

    }

    /**
     * 取消删除item事件
     * @param position
     */
    public  void onItemDeleteCancel(int position) {
        hideDeleteLayout();
    }

    /**
     * 确认删除item事件
     * @param position
     */
    public  void onItemDeleteOk(int position){
        hideDeleteLayout();
    }




    public void hideDeleteLayout() {
        if (layout_delete_view != null) {
            layout_delete_view.setVisibility(View.GONE);
        }
    }

    /**
     *
     * @param viewItem 当前点击item
     * @param position 当前点击position
     * @param deleteLayout item中deleteLayout的布局
     */
    public void showDeleteLayout(View viewItem, int position, ViewGroup deleteLayout) {
        LogUtil.d("[showDeleteLayout]  position="+position+", viewItem="+viewItem+", deleteLayout="+deleteLayout);
        if (layout_delete_view != null) {
            hideDeleteLayout();
        }
        if (deleteLayout != null) {
            layout_delete_view = deleteLayout;
        } else {
            layout_delete_view=(RelativeLayout) viewItem.findViewById(R.id.layout_item_delete);
        }
        initDeleteView(position, layout_delete_view);
        layout_delete_view.setVisibility(View.VISIBLE);
    }

    public void showDeleteLayout(View viewItem, int position) {
        showDeleteLayout(viewItem, position, null);
    }

}
