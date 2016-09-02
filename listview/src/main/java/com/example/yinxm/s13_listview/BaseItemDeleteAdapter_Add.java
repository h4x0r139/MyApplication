package com.example.yinxm.s13_listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by yinxm on 2016/9/1.
 */
public abstract class BaseItemDeleteAdapter_Add extends BaseAdapter{
    private Context context;

    public BaseItemDeleteAdapter_Add(Context context) {
        this.context = context;
    }



    private ViewGroup layout_delete_view;
    private ViewGroup delete_cancel, delete_ok;
    private ViewGroup attachLayout;//依附的布局

    private void initDeleteView() {
        LogUtil.d("[initDeleteView]");
        layout_delete_view = (ViewGroup) View.inflate(context, R.layout.item_delete, null);
        delete_cancel = (ViewGroup) layout_delete_view.findViewById(R.id.rl_item_cancel);
        delete_ok = (ViewGroup) layout_delete_view.findViewById(R.id.rl_item_delete);

        delete_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDeleteOnClick();
            }
        });

        delete_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okDeleteOnClick();
            }
        });

    }


    public  void cancelDeleteOnClick() {
        hideDeleteLayout();
    }

    public  void okDeleteOnClick(){
        hideDeleteLayout();
    }




    public void hideDeleteLayout() {
        if (attachLayout != null) {
            attachLayout.removeView(layout_delete_view);
        }
    }

    public void showDeleteLayout(View viewItem) {
        LogUtil.d("[showDeleteLayout]  viewItem="+viewItem);
        if (layout_delete_view == null) {
            initDeleteView();
        }

        if (viewItem instanceof ViewGroup) {
            LogUtil.d("instanceof ViewGroup");
            if (attachLayout != null) {
                attachLayout.removeView(layout_delete_view);
            }
            attachLayout = (ViewGroup) viewItem;
            attachLayout.addView(layout_delete_view);
//            itemLayout.addView(layout_delete_view,
//                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

}
